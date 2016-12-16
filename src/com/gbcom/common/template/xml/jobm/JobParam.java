package com.gbcom.common.template.xml.jobm;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
/**
 * JOB参数，添加到JOB-map中，用户job任务获取相应参数
 * 配置文件<code>JobParam list</code>
 * 
 * @author SunYanzheng
 * @date 下午4:19:16
 * @version v1.0.0
 * @see JobParam
 */
@XStreamAlias("jobParam")
public class JobParam {
	@XStreamAsAttribute
	private String name;
	@XStreamAsAttribute
	private String value;
	/**
	 * 获取参数名称
	 * @return <code>paramName </code>
	 */
	public String getName() {
		return name;
	}
	/**
	 * 下发参数名称
	 * @param  name <code>paramName </code>
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取参数值
	 * @return <code>value </code>
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 获取参数值
	 * @param value <code>value </code>
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
