package com.gbcom.system.exts;

import com.gbcom.system.common.SysSyncException;

/**
 * 扩展，系统用户同步,支持从第三方同步用户信息
 * 
 * @author SunYanzheng
 * @date 2014-12-19,下午02:51:24
 * @version v1.0.0
 * @see com.gbcom.system.exts.SysUserSyncItf
 */
public interface SysUserSyncItf {
	/**
	 * 用户同步接口
	 * @throws SysSyncException 同步异常
	 */
	public void syncUser()throws SysSyncException;
}
