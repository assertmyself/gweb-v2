package com.gbcom.system.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gbcom.system.aop.UserLog;
import com.gbcom.system.common.hchart.HChartResult;
import com.gbcom.system.common.hchart.HChartTransfer;
import com.gbcom.system.domain.SysLog;
import com.gbcom.system.manager.UserLogStatManager;
import com.gbcom.system.utils.JsonUtil;
import com.hc.core.controller.BaseCRUDActionController;

/**
 * 用户日志分析
 * 
 * @author syz
 * @date Nov 30, 2015 3:03:26 PM
 * @version 1.0.0
 * @since com.gbcom.ccsv3.controller.UserLogStatController
 */
@Controller
public class UserLogStatController extends BaseCRUDActionController<SysLog> {
	/**
	 * LOG
	 */
	private static final Logger LOG = Logger.getLogger(UserLogStatController.class);

	@Autowired
	private UserLogStatManager userLogStatManager;

	/**
	 * 初始化页面
	 * 
	 * @return 页面地址
	 */
	@RequestMapping
	@UserLog(eventType=UserLog.USERLOG_EVENTTYPE_QUERY)
	public String init() {
		return "view/system/userLogStat/chart";
	}

	/**
	 * 统计日志类型
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param gate
	 *            String
	 * @param startDate
	 *            String
	 * @param endDate
	 *            String
	 * @param granularity
	 *            String
	 * @param method
	 *            String
	 */
	@RequestMapping
	@UserLog(eventType = UserLog.USERLOG_EVENTTYPE_QUERY)
	public void hcStatLogType(HttpServletResponse response, String gate,
			String startDate, String endDate, String granularity, String method) {
		try {

			List<Map<String, Object>> list = userLogStatManager.statLogType();

			HChartResult rst = new HChartTransfer().transferHchartData(list);

			sendJSON(response, JsonUtil.beanToJSON(rst));
		} catch (Exception e) {
			LOG.error("hchartData" + e.getMessage(), e);
			HChartResult rst = new HChartResult();
			rst.setErrno(HChartResult.RST_FAILED);
			rst.setErrmsg(e.getMessage());
			sendJSON(response, JsonUtil.beanToJSON(rst));
		}
	}

	/**
	 * 获取终端统计信息
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	@RequestMapping
	public void getTerminalStat(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String data = userLogStatManager.terminalStat();
			sendSuccessJSON(response, "", data);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	/**
	 * 获取用户登录次数统计信息
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	@RequestMapping
	public void getUserLogStat(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String data = userLogStatManager.userLogStat();
			sendSuccessJSON(response, "", data);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

	}

	/**
	 * 获取角色登录次数统计信息
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	@RequestMapping
	public void getRoleLogStat(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String data = userLogStatManager.roleLogStat();
			sendSuccessJSON(response, "", data);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	/**
	 * 获取用户峰值统计信息
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param granularity
	 *            粒度
	 */
	@RequestMapping
	public void getUserPeakStat(HttpServletRequest request,
			HttpServletResponse response, String granularity) {
		try {
			String data = userLogStatManager.userPeakStat(granularity);
			sendSuccessJSON(response, "", data);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

}
