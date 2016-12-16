package com.gbcom.system.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gbcom.system.daoservice.SysLogService;
import com.gbcom.system.utils.CollectionUtil;
import com.gbcom.system.utils.DateUtil;
import com.gbcom.system.utils.JsonUtil;
import com.gbcom.system.utils.StatisticsUtil;

/**
 * UserLogStatManager:日志统计业务类， 推荐使用jdbcTemplate从数据库查询的结果是
 * List<Map<String,Object>>
 * 
 * @author syz
 * @date Nov 30, 2015 3:05:08 PM
 * @version 1.0.0
 * @since com.gbcom.ccsv3.manager.UserLogStatManager
 */
@Service
public class UserLogStatManager {
	private static final Logger LOG = Logger
			.getLogger(UserLogStatManager.class);
	@Autowired
	private SysLogService sysLogService;
	/**
	 * JdbcTemplate封装，仅常用方法 如果需要 更多方法，，请直接使用JdbcTemplate
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * forexample
	 * 
	 * 结果使用 new HChartTransfer().transferHchartData进行转化
	 * 
	 * @param sql
	 *            String
	 * @param args
	 *            Object[]
	 * @return List<Map<String,Object>> 格式需要统一，，@doujun和 @qinyeju 一同商量着怎么弄。
	 */
	public List<Map<String, Object>> queryForList(String sql, Object[] args) {
		return jdbcTemplate.queryForList(sql, args);
	}

	/**
	 * : (UserLogStatManager.statLogType)
	 * @return null
	 */
	public List<Map<String, Object>> statLogType() {
		return null;
	}

	/**
	 * 用户终端统计
	 * 
	 * @return 数据
	 */
	public String terminalStat() {
		String result = "";
		String sql = "SELECT ie_version,COUNT(*) num FROM sys_log where log_type = '1' GROUP BY ie_version ORDER BY num DESC";
		LOG.info("terminalStat sql :" + sql);
		try {
			Object[] data = StatisticsUtil.singleStatistics(sql, sysLogService);
			Object[] tempData = new Object[data.length];
			for (int i = 0; i < data.length; i++) {
				Object[] row = (Object[]) data[i];
				if (row[0] == null) {
					continue;
				}
				String browser = CollectionUtil.browserTypeCheck(row[0]
						.toString());
				row[0] = browser;
				tempData[i] = row;
			}
			result = StatisticsUtil.parseSingleData(tempData, "使用次数");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 用户登录次数统计
	 * 
	 * @return 数据
	 */
	public String userLogStat() {
		String result = "";
		String sql = "SELECT u.login_name lname,COUNT(*) num FROM sys_log l,sys_user u WHERE "
				+ "l.user_id = u.id AND log_type = '1' GROUP BY l.user_id ORDER BY num DESC";
		LOG.info("userLogStat sql :" + sql);
		try {
			Object[] data = StatisticsUtil.singleStatistics(sql, sysLogService);
			result = StatisticsUtil.parseSingleData(data, "登录次数");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 角色登录次数统计
	 * 
	 * @return 数据
	 */
	@SuppressWarnings("unchecked")
	public String roleLogStat() {
		String result = "";
		String sql = "SELECT r.role_name rm, COUNT(*) num FROM sys_log l,sys_user_role sr, sys_role r "
				+ "WHERE l.user_id = sr.user_id AND r.id = sr.role_id AND l.log_type = '1' GROUP BY r.id";
		LOG.info("roleLogStat sql :" + sql);
		try {
			Map<String, Map<Object, Object>> data = StatisticsUtil
					.vectorStatistics(sql, sysLogService);
			List legends = new ArrayList();
			legends.add("登录次数");
			result = StatisticsUtil.parseVectorData(data, legends);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 用户登录峰值统计
	 * 
	 * @param granularity
	 *            粒度
	 * @return 数据
	 */
	@SuppressWarnings("unchecked")
	public String userPeakStat(String granularity) {
		String result = "";
		try {
			Date startDate = StatisticsUtil.statisticStartDate(new Date(), granularity);
			String startTime = StatisticsUtil.statisticTime(startDate, granularity);
			String endTime = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
			String sql = "SELECT " + StatisticsUtil.statisticSize(granularity)
					+ "COUNT(*) num" + " FROM sys_log WHERE enter_time > '"
					+ startTime + "' AND enter_time < '" + endTime
					+ "' AND log_type = '1' GROUP BY time ORDER BY time";
			LOG.info("userPeakStat sql :" + sql);
			List<Object[]> data = sysLogService.findBySql(sql);
			Date start = StatisticsUtil.statisticStartDate(new Date(), granularity);
			Date end = new Date();
			Map<String, Object> dataMap = StatisticsUtil.fillFullData(data,
					granularity, start, end);
			Map<String, Object> transMap = new HashMap<String, Object>();
			transMap.put("登录次数", dataMap);
			result = JsonUtil.mapToJSON(transMap);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 用户统计的SQL
	 * 
	 * @author syz
	 * @date Nov 30, 2015 3:44:58 PM
	 * @version 1.0.0
	 * @since com.gbcom.ccsv3.manager.UserLogStatConst
	 */
	@SuppressWarnings("unused")
	private static final class UserLogStatConst {

		public static final String SQL_QUERY_ALL = "select * from sys_log";
		public static final String SQL_STAT_LOG_TYPE = "select * from sys_log GROUP BY event_type";// 例如
		// 统计日志的配型
	}
}
