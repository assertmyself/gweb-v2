package com.gbcom.common.util;

import org.apache.commons.jexl.Expression;
import org.apache.commons.jexl.ExpressionFactory;
import org.apache.commons.jexl.JexlContext;
import org.apache.commons.jexl.JexlHelper;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import javax.xml.bind.*;

import java.io.*;
import java.util.Map;


/**
 * Jaxb的工具类以及通过表达式进行验证的方法.
 * JAXB 提供xml 和bean的换  
 * hc.core 包提供类似的功能，也可以使用
 *  * <li> 说明：
 * 
    JAXBContext类，是应用的入口，用于管理XML/Java绑定信息。
    Marshaller接口，将Java对象序列化为XML数据。
    Unmarshaller接口，将XML数据反序列化为Java对象。

    @XmlType，将Java类或枚举类型映射到XML模式类型
    @XmlAccessorType(XmlAccessType.FIELD) ，控制字段或属性的序列化。FIELD表示JAXB将自动绑定Java类中的每个非静态的（static）、非瞬态的（由@XmlTransient标 注）字段到XML。其他值还有XmlAccessType.PROPERTY和XmlAccessType.NONE。
    @XmlAccessorOrder，控制JAXB 绑定类中属性和字段的排序。
    @XmlJavaTypeAdapter，使用定制的适配器（即扩展抽象类XmlAdapter并覆盖marshal()和unmarshal()方法），以序列化Java类为XML。
    @XmlElementWrapper ，对于数组或集合（即包含多个元素的成员变量），生成一个包装该数组或集合的XML元素（称为包装器）。
    @XmlRootElement，将Java类或枚举类型映射到XML元素。
    @XmlElement，将Java类的一个属性映射到与属性同名的一个XML元素。
    @XmlAttribute，将Java类的一个属性映射到与属性同名的一个XML属性。



 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-9-15,下午12:31:26
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.common.util.JaxbUtil
 */
public class JaxbUtil {

	

    /** 
     * JavaBean转换成xml 
     * 默认编码UTF-8 
     * @param obj  Object
     * @return  String
     */  
    public static String convertToXml(Object obj) {  
        return convertToXml(obj, "UTF-8");  
    }  
  
    /** 
     * JavaBean转换成xml 
     * @param obj  Object
     * @param encoding   String
     * @return   String
     */  
    public static String convertToXml(Object obj, String encoding) {  
        String result = null;  
        try {  
            JAXBContext context = JAXBContext.newInstance(obj.getClass());  
            Marshaller marshaller = context.createMarshaller(); 
            //Marshaller.JAXB_FORMATTED_OUTPUT 决定是否在转换成xml时同时进行格式化（即按标签自动换行，否则即是一行的xml）

            //Marshaller.JAXB_ENCODING xml的编码方式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);  
  
            StringWriter writer = new StringWriter();  
            marshaller.marshal(obj, writer);  
            result = writer.toString();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return result;  
    }  
  
    /** 
     * xml转换成JavaBean 
     * @param xml  String
     * @param <T> t 
     * @param c  class
     * @return  T
     */  
    public static <T> T converyToJavaBean(String xml, Class<T> c) {  
        T t = null;  
        try {  
            JAXBContext context = JAXBContext.newInstance(c);  
            Unmarshaller unmarshaller = context.createUnmarshaller();  
            t = (T) unmarshaller.unmarshal(new StringReader(xml));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return t;  
    }  
    
    
    //------------------ 提供类似 的解决思路------------------
	/**
	 * bean to xml 
	 * @param object Object
	 * @return String
	 * @throws JAXBException JAXBException
	 */
    public static String marshall(final Object object) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(object.getClass().getPackage().getName());
        Marshaller marshaller = jc.createMarshaller();
        Writer writer = new StringWriter();
        marshaller.marshal(object, writer);
        return writer.toString();
    }

    /**
     * bean to xml,将bean 转化成xml文件
     * @param object Object
     * @param file File
     * @throws JAXBException JAXBException
     * @throws IOException IOException
     */
    public static void marshall(final Object object, File file) throws JAXBException, IOException {
        JAXBContext jc = JAXBContext.newInstance(object.getClass().getPackage().getName());
        Marshaller marshaller = jc.createMarshaller();
        OutputStream outputStream = new FileOutputStream(file);
        marshaller.marshal(object, outputStream);
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 文件解析成bean 
     * @param <T> T
     * @param clazz Class<T>
     * @param xml String
     * @return T
     * @throws JAXBException JAXBException
     */
    public static <T> T unmarshall(final Class<T> clazz, final String xml) throws JAXBException {
        Reader reader = new StringReader(xml);
        JAXBContext jc = JAXBContext.newInstance(clazz.getPackage().getName());
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        return (T) unmarshaller.unmarshal(reader);
    }

    /**
     * 文件解析成bean 
     * @param <T>  <T> T
     * @param clazz  Class<T> 
     * @param file File
     * @return <T> T
     * @throws JAXBException JAXBException
     * @throws FileNotFoundException FileNotFoundException
     */
    public static <T> T unmarshall(final Class<T> clazz, final File file) throws JAXBException, FileNotFoundException {
        InputStream inputStream = new FileInputStream(file);
        JAXBContext jc = JAXBContext.newInstance(clazz.getPackage().getName());
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        return (T) unmarshaller.unmarshal(inputStream);
    }

    /**
     * 通过表达式验证
     *
     * @param map Map
     * @param expression 表达式
     * @return 验证结果(true, false)
     * @throws Exception Exception
     */
    public static boolean evaluate(Map map, String expression) throws Exception {
        Expression e = ExpressionFactory.createExpression(expression);
        JexlContext jc = JexlHelper.createContext();
        jc.setVars(map);
        Boolean message = (Boolean) e.evaluate(jc);

        return message;
    }

    /**
     * object 转换成 xml
     *
     * @param o 对象
     * @return String
     */
    public static String jaxbMarshal(Object o) {
        Writer writer = new StringWriter();
        JAXB.marshal(o, writer);
        return writer.toString();
    }

    /**
     * xml 转成 对象
     *
     * @param clazz  对象类型
     * @param xmlStr 字符串
     * @param <T>    .
     * @return 返回对象
     */
    public static <T> T jaxbUnmarshal(Class<T> clazz, String xmlStr) {
        if (xmlStr == null || xmlStr.length() == 0) {
            try {
                return clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        Reader reader = new StringReader(xmlStr);
        return JAXB.unmarshal(reader, clazz);
    }

    /**
     * getXpathXml
     * @param doc String
     * @param xpath String
     * @return String
     */
    public static String getXpathXml(String doc, String xpath) {
        SAXReader saxReader = new SAXReader();
        StringReader sr = new StringReader(doc);
        try {
            Document document = saxReader.read(sr);
            Node node = document.selectSingleNode(xpath);
            if (node != null) {
                return node.asXML();
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "";
    }
}
