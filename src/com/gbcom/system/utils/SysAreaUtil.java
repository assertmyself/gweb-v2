package com.gbcom.system.utils;

import com.gbcom.system.domain.SysArea;
import com.gbcom.system.domain.SysAreaNes;
import com.gbcom.system.manager.SysAreaManager;
import com.hc.core.utils.SpringUtils;

/**
 * 区域工具类
 * 
 * <p>
 * 
 * @author syz
 *         <p>
 * @date 2015-5-27,下午03:59:18
 *       <p>
 * @version v1.0.0
 *          <p>
 * @see com.gbcom.system.utils.SysAreaUtil
 */
public class SysAreaUtil {

	/**
	 * 区域网元IDS,字符串。
	 * 
	 * @param area SysArea
	 * @return String
	 */
	public static String sysAreaNeIDs(SysArea area) {
		String keys = "";
		for (SysAreaNes ne : area.getNes()) {
			if (ne != null) {
			}
			keys += ne.getNeID() + ",";
		}
		return keys.contains(",") ? keys.substring(0, keys.lastIndexOf(","))
				: "";
	}

	/**
	 * 返回所有分组的设备id
	 * 
	 * @return String
	 */
	public static String allSysAreaNeIDs() {
		SysAreaManager manager = ((SysAreaManager) SpringUtils
				.getBean("sysAreaManager"));
		String keys = "";
		for (SysAreaNes ne : manager.getAllAreaNes()) {
			if (ne != null) {
			}
			keys += ne.getNeID() + ",";
		}
		return keys.contains(",") ? keys.substring(0, keys.lastIndexOf(","))
				: "";
	}

	/**
	 * 指定用户的网元字符串
	 * @param area SysArea
	 * @return String
	 */
	public static String sysAreaNeNames(SysArea area) {
		String keys = "";
		for (SysAreaNes ne : area.getNes()) {
			if (ne != null) {
			}
			keys += ne.getNeName() + ",";
		}

		return keys.contains(",") ? keys.substring(0, keys.lastIndexOf(","))
				: "";
	}

}
