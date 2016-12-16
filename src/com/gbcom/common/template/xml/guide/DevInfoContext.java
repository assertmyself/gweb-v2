package com.gbcom.common.template.xml.guide;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
/**
 * 上下文list
 * @author SunYanzheng
 * @date 2014-6-23
 * @version v1.0.0
 * @see com.gbcom.omc.ccsv2.common.bean.dynamic.DevInfoContext
 */
@XStreamAlias("DevInfoContext")
public class DevInfoContext {
	@XStreamAsAttribute
    private  List<DevInfoGuide>  list;

	/**
	 * @return the routeList
	 */
	public List<DevInfoGuide> getList() {
		return list;
	}

	/**
	 * @param routeList the routeList to set
	 */
	public void setList(List<DevInfoGuide> routeList) {
		this.list = routeList;
	}

}
