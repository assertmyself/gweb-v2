package com.gbcom.system.menu;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.reflection.ObjectAccessException;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Iterator;

/**
 * 属性转化，基于xml(xs) Converter;基于xml菜单解析时需要
 * 
 * <p>
 * 
 * @author syz
 *         <p>
 * @date 2015-9-11,下午03:35:28
 *       <p>
 * @version v1.0.0
 *          <p>
 * @see com.gbcom.system.menu.AttributeValueConveter
 */
@SuppressWarnings("unchecked")
public class AttributeValueConveter implements Converter {
	private String textName = "text";
	private Mapper mapper;

	/**
	 * AttributeValueConveter
	 * 
	 * @param mapper
	 *            Mapper
	 */
	public AttributeValueConveter(Mapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * @param aClass
	 *            Class
	 * @return boolean
	 */
	@Override
	public boolean canConvert(Class aClass) {
		return aClass.equals(ATag.class);
	}

	/**
	 * @param obj
	 *            Object
	 * @param wt
	 *            HierarchicalStreamWriter
	 * @param marshallingContext
	 *            MarshallingContext
	 */
	@Override
	public void marshal(Object obj, HierarchicalStreamWriter wt,
			MarshallingContext marshallingContext) {
		Field[] values = obj.getClass().getDeclaredFields();
		for (Field value : values) {
			String name = value.getName();
			if (name.equals(textName)) {
				continue;
			}
			try {
				if (!value.isAccessible()) {
					value.setAccessible(true);
				}
				Object v = value.get(obj);
				if (v == null) {
					continue;
				}
				if (Number.class.isAssignableFrom(v.getClass())
						|| v.getClass().isPrimitive()) {
					wt.addAttribute(mapper.serializedMember(obj.getClass(),
							name), String.valueOf(v));
				} else {
					wt.addAttribute(mapper.serializedMember(obj.getClass(),
							name), v.toString());
				}
			} catch (Exception e) {
				throw new ObjectAccessException("Cannot set Field "
						+ value.getName() + obj.getClass(), e);
			}
		}
		try {
			Field field = obj.getClass().getDeclaredField(textName);
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			Object v = field.get(obj);
			if (null != v && !"".equals(v.toString())) {
				wt.setValue(v.toString());
			}
		} catch (Exception e) {
			throw new ObjectAccessException("Cannot set Field " + textName
					+ obj.getClass(), e);
		}
	}

	/**
	 * @param reader
	 *            HierarchicalStreamReader
	 * @param context
	 *            UnmarshallingContext
	 * @return Object
	 */
	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {

		Object obj = context.currentObject();
		if (obj == null) {
			try {
				obj = context.getRequiredType().newInstance();
			} catch (Exception e) {
				throw new ObjectAccessException("Cannot construct "
						+ context.getRequiredType().getName(), e);
			}
		}

		Iterator attNames = reader.getAttributeNames();
		while (attNames.hasNext()) {
			String attName = (String) attNames.next();
			if (attName.equals(textName)) {
				continue;
			}

			try {
				Field field = obj.getClass().getDeclaredField(
						mapper.realMember(obj.getClass(), attName));
				if (!field.isAccessible()) {
					field.setAccessible(true);
				}
				String v = reader.getAttribute(attName);
				if (null == v || "".equals(v)) {
					continue;
				}
				Class fieldType = field.getType();
				Constructor strnum = fieldType
						.getDeclaredConstructor(String.class);
				field.set(obj, strnum.newInstance(v));
			} catch (Exception e) {
				e.printStackTrace();
				throw new ObjectAccessException("Cannot construct "
						+ obj.getClass(), e);
			}
		}
		String value = reader.getValue();
		if (null != value && !"".equals(value)) {
			try {
				Field field = obj.getClass().getDeclaredField(
						mapper.realMember(obj.getClass(), textName));
				if (!field.isAccessible()) {
					field.setAccessible(true);
				}
				field.set(obj, value);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ObjectAccessException("Cannot construct "
						+ obj.getClass(), e);
			}
		}
		return obj;

	}
}
