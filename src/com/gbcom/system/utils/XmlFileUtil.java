package com.gbcom.system.utils;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * @author xuyin
 * @version 1.0
 */
public class XmlFileUtil {
	/**
	 * 
	 * @param <T>
	 *            T
	 * @param type
	 *            Class<T>
	 * @param xml
	 *            File
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T unmarshallerObjectFromXml(Class<T> type, File xml) {
		T t = null;
		try {
			JAXBContext context = JAXBContext.newInstance(type);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			if (xml.exists()) {
				t = (T) unmarshaller.unmarshal(xml);
			} else {
				t = type.newInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 
	 * @param <T>
	 *            T
	 * @param obj
	 *            T
	 * @param type
	 *            type
	 * @param xml
	 *            xml
	 */
	public static <T> void marshallerObjectToXml(T obj, Class<T> type, File xml) {
		try {
			JAXBContext context = JAXBContext.newInstance(type);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(obj, xml);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param <T>
	 *            T
	 * @param obj
	 *            Object
	 * @param type
	 *            Class
	 * @param os
	 *            OutputStream
	 */
	public static <T> void marshallerObjectToXml(T obj, Class<T> type,
			OutputStream os) {
		try {
			JAXBContext context = JAXBContext.newInstance(type);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(obj, os);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param <T>
	 *            T
	 * @param type
	 *            Class<T>
	 * @param ins
	 *            InputStream
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T unmarshallerObjectFromXml(Class<T> type, InputStream ins) {
		try {
			JAXBContext context = JAXBContext.newInstance(type);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			T obj = (T) unmarshaller.unmarshal(ins);
			return obj;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * for test
	 * 
	 * @param <T>
	 *            T
	 * @param obj
	 *            T
	 * @param type
	 *            Class<T>
	 */
	public static <T> void marshallerObjectToXml(T obj, Class<T> type) {
		try {
			JAXBContext context = JAXBContext.newInstance(type);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(obj, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
