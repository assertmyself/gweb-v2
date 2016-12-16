/**
 * @(#)AbstractConfiguration.java      　07/11
 *
 * Copyright (c) 2007 GBCom CommunicationTechnology Co.Ltd
 * All right reserved.
 */
package com.gbcom.common.im;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.gbcom.common.im.exception.IMInitialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * 解析信息模型、显示模型的.xml文件
 * 
 * @author fengjing
 * @version 2.0
 *
 *
 *
 */
public abstract class AbstractConfiguration {
	/**
	 * 记录日志
	 */
	private Logger logger = LoggerFactory
			.getLogger(AbstractConfiguration.class);

	/**
	 * 读入文件，转换成dom对象。通过此dom对象构建VM或者IM
	 * 
	 * @param file
	 *            模型文件
	 * @return Element xml解析后的节点对象
	 * 
	 * @throws com.gbcom.common.im.exception.IMInitialException
	 *             信息模型显示模型初始化异常
	 */
	protected Element configure(File file) throws IMInitialException {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setValidating(false);
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			Element root = doc.getDocumentElement();
			int whiteSpacesRemoved = this.removeWhitespace(root);

			logger.debug("White Spaces Removed: " + whiteSpacesRemoved);
			return root;
		} catch (Exception e) {
			throw new IMInitialException("exception while parsing "
					+ file.getName().toString());
		}
	}

	/**
	 * 删除文件中的空白处
	 * 
	 * @param e
	 *            根元素
	 * @return 删除的空白的个数
	 */
	protected int removeWhitespace(Element e) {
		NodeList children = e.getChildNodes();
		if (children.getLength() < 1) {
			return 0;
		}

		int count = 0;
		for (int i = children.getLength() - 1; i >= 0; i--) {
			Node child = children.item(i);
			if (child instanceof Text
					&& ((Text) child).getData().trim().length() == 0) {
				e.removeChild(child);
				count++;
			} else if (child instanceof Element) {
				count += removeWhitespace((Element) child);
			}
		}
		return count;
	}

}
