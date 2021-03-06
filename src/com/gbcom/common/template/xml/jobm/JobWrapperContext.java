package com.gbcom.common.template.xml.jobm;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * JobWrapper.xml 节点上下文
 * @author SunYanzheng
 * @date 下午4:28:05
 * @version v1.0.0
 * @see JobWrapperContext
 */
@XStreamAlias("JobWrapperContext")
public class JobWrapperContext {
	private List<JobWrapperInfo> list;
	/**
	 * 获取JOBWrapper 封装列表
	 * @return List<JobWrapperInfo> job封装列表 
	 */
	public List<JobWrapperInfo> getList() {
		return list;
	}

	/**
	 * 下发 JOBWrapper 封装列表
	 * @param list List<JobWrapperInfo>
	 */
	public void setList(List<JobWrapperInfo> list) {
		this.list = list;
	}
}
