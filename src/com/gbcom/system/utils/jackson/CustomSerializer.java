package com.gbcom.system.utils.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.impl.UnknownSerializer;
import com.fasterxml.jackson.databind.ser.std.CollectionSerializer;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Hibernate;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 自定义json序列化
 * 
 * <p>
 * 
 * @author syz
 *         <p>
 * @date 2015-9-11,下午03:03:17
 *       <p>
 * @version v1.0.0
 *          <p>
 * @see com.gbcom.system.utils.jackson.CustomSerializer
 */
public class CustomSerializer extends JsonSerializer<Object> {

	@Override
	public void serialize(Object value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		// 获取值
		Map<Object, Boolean> serializedObjects = new HashMap<Object, Boolean>();
		try {
			wirte(value, jgen, provider, false, null, serializedObjects, null);
		} catch (IllegalAccessException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		} catch (NoSuchMethodException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		} catch (InvocationTargetException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
	}

	/**
	 * 写流
	 * 
	 * @param value
	 *            Object
	 * @param jgen
	 *            JsonGenerator
	 * @param provider
	 *            SerializerProvider
	 * @param isWriteObjectName
	 *            Boolean
	 * @param fieldName
	 *            String
	 * @param serializedObjects
	 *            Map<Object, Boolean>
	 * @param unLoadProperties
	 *            List
	 * @throws IOException
	 *             IOException
	 * @throws IllegalAccessException
	 *             IllegalAccessException
	 * @throws NoSuchMethodException
	 *             NoSuchMethodException
	 * @throws InvocationTargetException
	 *             InvocationTargetException
	 */
	public void wirte(Object value, JsonGenerator jgen,
			SerializerProvider provider, Boolean isWriteObjectName,
			String fieldName, Map<Object, Boolean> serializedObjects,
			List unLoadProperties) throws IOException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException {

		if (Hibernate.isInitialized(value)) {
			// 已经序列化的属性
			if (serializedObjects.get(value) == null) {
				if (value == null) {
					if (isWriteObjectName) {
						jgen.writeFieldName(fieldName);
					}
					jgen.writeNull();
				} else {
					serializedObjects.put(value, true);
					JsonSerializer<Object> valueSerializer = provider
							.findValueSerializer(value.getClass(), null);

					// 如果是数组
					if ((Object) valueSerializer instanceof CollectionSerializer) {
						if (isWriteObjectName) {
							jgen.writeFieldName(fieldName);
						}
						jgen.writeStartArray();
						Collection property = (Collection) value;
						for (Object o : property) {
							jgen.writeStartObject();
							List<String> curUnloadProperties = new ArrayList<String>();
							nextSerialize(o, jgen, provider, serializedObjects,
									curUnloadProperties);
							jgen.writeFieldName("_lazyProperties");
							jgen.writeObject(curUnloadProperties);
							jgen.writeEndObject();
						}
						jgen.writeEndArray();
					}

					// 如果是对象
					else if (valueSerializer instanceof CustomSerializer) {
						if (isWriteObjectName) {
							jgen.writeFieldName(fieldName);
						}
						jgen.writeStartObject();
						List<String> curUnloadProperties = new ArrayList<String>();
						nextSerialize(value, jgen, provider, serializedObjects,
								curUnloadProperties);
						jgen.writeFieldName("_lazyProperties");
						jgen.writeObject(curUnloadProperties);

						jgen.writeEndObject();
					}
					// 如果是不识别属性
					else if (valueSerializer instanceof UnknownSerializer) {

					}

					// 基本属性
					else {
						if (isWriteObjectName) {
							jgen.writeFieldName(fieldName);
						}
						valueSerializer.serialize(value, jgen, provider);
					}
				}
			} else {
				// deal with JsonIdentityInfo
				if (hasIdProperty(value.getClass())) {
					jgen.writeFieldName(fieldName);
					jgen.writeNumber((Long) PropertyUtils.getProperty(value,
							"id"));
				}

			}
		} else {
			// 未load
			if (unLoadProperties == null) {
				unLoadProperties = new ArrayList();
			}
			unLoadProperties.add(fieldName);
			if (!(value instanceof Collection)) {
				if (isWriteObjectName) {
					jgen.writeFieldName(fieldName);
				}
				jgen.writeStartObject();
				if (hasIdProperty(value.getClass())) {
					jgen.writeObjectField("id", PropertyUtils.getProperty(
							value, "id"));
				}
				jgen.writeObjectField("_initialized", false);
				jgen.writeEndObject();
			}
		}
	}

	/**
	 * nextSerialize
	 * 
	 * @param value
	 *            Object
	 * @param jgen
	 *            JsonGenerator
	 * @param provider
	 *            SerializerProvider
	 * @param serializedObjects
	 *            Map<Object, Boolean>
	 * @param curUnloadProperties
	 *            List<String>
	 * @throws IllegalAccessException
	 *             IllegalAccessException
	 * @throws NoSuchMethodException
	 *             NoSuchMethodException
	 * @throws InvocationTargetException
	 *             InvocationTargetException
	 * @throws IOException
	 *             IOException
	 */
	public void nextSerialize(Object value, JsonGenerator jgen,
			SerializerProvider provider,
			Map<Object, Boolean> serializedObjects,
			List<String> curUnloadProperties) throws IllegalAccessException,
			NoSuchMethodException, InvocationTargetException, IOException {
		PropertyDescriptor[] propertyDescriptors = PropertyUtils
				.getPropertyDescriptors(value);
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			if (propertyDescriptor.getPropertyType() == Class.class) {
				continue;
			}
			Object property = PropertyUtils.getProperty(value,
					propertyDescriptor.getName());
			wirte(property, jgen, provider, true, propertyDescriptor.getName(),
					serializedObjects, curUnloadProperties);
		}

	}

	/**
	 * hasIdProperty
	 * 
	 * @param cl
	 *            Class
	 * @return boolean
	 */
	public boolean hasIdProperty(Class cl) {
		PropertyDescriptor[] propertyDescriptors = PropertyUtils
				.getPropertyDescriptors(cl);
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			if (propertyDescriptor.getName().equalsIgnoreCase("id")) {
				return true;
			}
		}
		return false;
	}

}
