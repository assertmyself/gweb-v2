package com.gbcom.system.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gbcom.system.aop.UserLog;
import com.gbcom.system.common.hchart.HChartResult;
import com.gbcom.system.common.hchart.HChartTransfer;
import com.gbcom.system.domain.SysLog;
import com.gbcom.system.manager.OperLogStatManager;
import com.gbcom.system.manager.UserLogStatManager;
import com.gbcom.system.utils.JsonUtil;
import com.hc.core.controller.BaseCRUDActionController;

/**
 * 操作日志分析
 * @author syz
 * @date Nov 30, 2015 3:03:26 PM
 * @version 1.0.0
 * @since com.gbcom.ccsv3.controller.UserLogStatController
 */
@Controller
public class OperLogStatController extends BaseCRUDActionController<SysLog> {
	private static Logger logger = Logger.getLogger(OperLogStatController.class);

	@Autowired
	private UserLogStatManager userLogStatManager;
	
	@Autowired
	private OperLogStatManager operLogStatManager;

	/**
	 * rend to jsp view
	 * 
	 * @param model Model
	 * @param m
	 *            指标组名称。
	 * @param type
	 *            保留字段
	 * @param request HttpServletRequest
	 * @return String
	 */
	@RequestMapping
    @UserLog(eventType=UserLog.USERLOG_EVENTTYPE_QUERY)
	public String init(Model model, String m, String type,
			HttpServletRequest request) {
		// 判断是否有编辑权限
		model.addAttribute("type", type);
		return "view/system/operLogStat/chart";
	}
	/**
	 * 统计日志类型
	 * 
	 * 方法需要添加
	 * @param response HttpServletResponse
	 * @param gate String
	 * @param startDate String
	 * @param endDate String
	 * @param granularity String
	 * @param method String
	 */
	@RequestMapping
	@UserLog(eventType=UserLog.USERLOG_EVENTTYPE_QUERY)
	public void hcStatLogType(HttpServletResponse response,
			String gate, String startDate, String endDate, String granularity,
			String method) {
		try {
			
			List<Map<String, Object>> list = userLogStatManager.statLogType();
			
			HChartResult rst = new HChartTransfer().transferHchartData(list);

			sendJSON(response, JsonUtil.beanToJSON(rst));
		} catch (Exception e) {
			logger.error("hchartData"+e.getMessage(),e);
			HChartResult rst = new HChartResult();
			rst.setErrno(HChartResult.RST_FAILED);
			rst.setErrmsg(e.getMessage());
			sendJSON(response, JsonUtil.beanToJSON(rst));
		}
	}

	/**
	 * 统计菜单点击次数
	 * 
	 * @param response HttpServletResponse
	 */
	@RequestMapping
	public void statisticMenuClick(HttpServletResponse response){
		try {
			String data = operLogStatManager.statMenuClick();
			sendSuccessJSON(response, "", data);
		} catch (Exception e) {
			log.error("oper MenuClick Exception:",e);
		}
	}
	
	/**
	 * 统计操作类型次数统计
	 * @param response HttpServletResponse
	 */
	@RequestMapping
	public void statisticOperClick(HttpServletResponse response){
		try {
			String data = operLogStatManager.statOperClick();
			sendSuccessJSON(response, "", data);
		} catch (Exception e) {
			log.error("oper OperClick Exception:",e);
		}
	}
	
	/**
	 * 获取成功/失败次数
	 * @param response HttpServletResponse
	 */
	@RequestMapping
	public void statisticResult(HttpServletResponse response){
		try {
			String data = operLogStatManager.statResult();
			sendSuccessJSON(response, "", data);
		} catch (Exception e) {
			logger.error("oper Result Exception：",e);
		}
	}
	
	/**
	 *  用于折线图，按时间统计操作类型的次数: (OperLogStatController.statOperFoldLinePlot)
	 * @param response HttpServletResponse
	 * @param granularity String
	 */
	@RequestMapping
	public void statOperFoldLinePlot(HttpServletResponse response,String granularity){
		try {
			String resultData = operLogStatManager.statOperFoldLinePlot(granularity);
			sendSuccessJSON(response,"",resultData);
		} catch (Exception e) {
			logger.error("OperLogStat Exception：",e);
		}
	}
	
}
