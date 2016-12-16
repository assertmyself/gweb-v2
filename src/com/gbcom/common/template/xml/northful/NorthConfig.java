package com.gbcom.common.template.xml.northful;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 北向模块JOB定时任务，mib加载等配置，根据需要只加载相应的北向
 * 
 * @author qinyeju
 * @date 2015-9-29
 */
@XmlRootElement(name = "north")
public class NorthConfig implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	//private List<String> list = new ArrayList<String>();//不应该是成员变量

	/**
	 * @return the name
	 */
	@XmlElement(name = "name")
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the list
	 */
	public List<String> getList() {
		List<String> list = new ArrayList<String>();
		String[] names = name.split(",");
		for (int i = 0; i < names.length; i++) {
			list.add(names[i]);
		}
		return list;
	}


	
}
