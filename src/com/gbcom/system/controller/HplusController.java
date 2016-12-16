package com.gbcom.system.controller;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gbcom.system.aop.UserLog;
import com.hc.core.controller.BaseCRUDActionController;

/**
 * HplusController： 方便加载 之前的例子
 * @author SYZ
 * @date 2016-11-9 下午05:19:51
 * @version 1.0.0
 * @see com.gbcom.system.controller.HplusController
 */
@Controller
public class HplusController  extends BaseCRUDActionController<Serializable> {
	private Logger logger = LoggerFactory.getLogger(SysInfoController.class);


	/**
	 * 返回视图
	 * @param m
	 *            String
	 * @param model
	 *            Model
	 * @return String
	 */
	@RequestMapping
	@UserLog(eventType=UserLog.USERLOG_EVENTTYPE_QUERY)
	public String init(String m, Model model) {
		return "html/"+m+".html";
	}

}
