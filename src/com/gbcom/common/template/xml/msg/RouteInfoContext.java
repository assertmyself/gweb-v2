package com.gbcom.common.template.xml.msg;


import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("routeContext")
public class RouteInfoContext {
	@XStreamAsAttribute
    private  List<RouteInfo>  list;

	/**
	 * @return the routeList
	 */
	public List<RouteInfo> getRouteList() {
		return list;
	}

	/**
	 * @param routeList the routeList to set
	 */
	public void setRouteList(List<RouteInfo> routeList) {
		this.list = routeList;
	}

}
