package com.gbcom.system.manager;

import com.hc.core.orm.log.ITableLogger;
import com.hc.core.orm.log.TableLog;
import org.springframework.stereotype.Service;

/**
 * description: User: Chenjp Date: 13-1-11 下午3:08
 */
@Service
public class SysOperationTableLogManager implements ITableLogger {

	/**
	 * @param tableName
	 *            String
	 * @return Boolean
	 */
	public Boolean isLog(String tableName) {
		// Map map = TableLogUtils.getTableConfigMap();
		//
		// if(map != null && map.containsKey(tableName)) {
		// return true;
		// }

		return false;
	}

	/**
	 * @param bean
	 *            TableLog
	 */
	public void log(TableLog bean) {

	}
}
