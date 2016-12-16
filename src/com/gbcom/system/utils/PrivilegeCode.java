//off checkstyle
package com.gbcom.system.utils;

import com.gbcom.system.domain.*;

/**
 * 权限编码定义 User: Intellj Date: 12-3-16
 */
public class PrivilegeCode {

	// *********** page privilege code*******************//
	/**
	 * 首页
	 */
	public static final String SYS_FIRSTPAGE = "firstPage";
	/**
	 * 区域视图
	 */
	public static final String SYS_AREA = "sysArea";
	/**
	 * 活跃告警视图
	 */
	public static final String  ACTIVE_ALARM = "ActiveAlarm";
	/**
	 * 设备列表视图
	 */
	public static final String  AP_DEVICE = "apDevice";
	
	
	
	/**
	 * ***************************************系统管理******************************
	 * ************************
	 */
	public static final String SYS_DEPT_EDIT = SysDept.class.getSimpleName()
			+ "_edit"; // 单位部门_编辑
	public static final String SYS_PERSON_EDIT = SysPerson.class
			.getSimpleName()
			+ "_edit"; // 人员_编辑
	public static final String SYS_USER_EDIT = SysUser.class.getSimpleName()
			+ "_edit"; // 用户_编辑
	public static final String SYS_ROLE_EDIT = SysRole.class.getSimpleName()
			+ "_edit"; // 角色_编辑
	public static final String SYS_PRIVILEGE_EDIT = SysPrivilege.class
			.getSimpleName()
			+ "_edit"; // 权限_编辑
	public static final String SYS_MENU_EDIT = SysMenu.class.getSimpleName()
			+ "_edit"; // 菜单_编辑
	public static final String SYS_LOG_EDIT = SysLog.class.getSimpleName()
			+ "_edit"; // 日志_编辑
	public static final String SYS_CODE_EDIT = SysCode.class.getSimpleName()
			+ "_edit";// 系统字典_编辑
	public static final String SYS_PARAMETER = SysParameter.class
			.getSimpleName()
			+ "_edit"; // 系统参数维护_编辑
	public static final String SYS_SAMPLE_EDIT = "_edit";// test
	
	// 以下到告警管理都是新增的
	public static final String SYS_ROLE_DESIGNATE = "SysRole_designate";// 角色_授权
	public static final String SYS_AREA_EDIT = "SysArea_edit"; // 系统参数维护_编辑
	/**
	 * *****************设备管理*******************
	 */
	public static final String AP_DEVICE_EDIT = "apDevice_edit"; // 上报设备_编辑
	public static final String AP_DEVICE_SITE = "apDevice_site"; // 上报设备_场所，网监项目特有
	public static final String AP_DEVICE_MORE = "apDevice_more"; // 上报设备_更多
	public static final String AP_DEVICE_AUDIT = "apDevice_audit"; // AP基本信息_编辑
	public static final String FIT_DEVICE_EDIT = "fitDevice_edit"; // fit基本信息_编辑

	public static final String HOTSPOT_EDIT = "hotspot_edit"; // 热点管理_编辑/

	public static final String DESTVERSION_EDIT = "destVersion_edit"; // 目标版本_编辑
	public static final String APOBJECT_EDIT = "aPObject_edit"; // TR069设备_按钮权限

	public static final String UPGPOLICY_EDIT = "upgPolicy_edit"; // 升级策略_编辑
	public static final String DRLRULE_EDIT = "drlRule_edit"; // 升级匹配策略_编辑
																// -2015-7-13

	public static final String SOFTWARE_EDIT = "SoftWare_edit"; // 软件版本_按钮权限

	public static final String CONFIGTPL_ADD = "configTpl_add"; // 配置模板_新增
	public static final String CONFIGTPL_DESIGNATE = "configTpl_designate"; // 配置模板_指派
	public static final String CONFIGTPL_EDIT = "configTpl_edit"; // 配置模板_编辑
	public static final String CONFIGTPL_DELETE = "configTpl_delete"; // 配置模板_删除

	public static final String GATHERMAC_EDIT = "gatherMac_edit"; // 网监 mac采集_编辑
	/**
	 * *******************告警管理*********************
	 */
	public static final String ALARMTPL_EDIT = "AlarmTpl_edit"; // 告警知识库_编辑

	/**
	 * *******************移动管理************************
	 */
	public static final String DEVICE_EDIT = "Device_edit";
	/**
	 * ***************************************业务模块******************************
	 * ************************
	 */
	public static final String BIZ_MODEL_EDIT = "model_edit";// 数字模型_编辑
	public static final String BIZ_MODEL_VIEWALL = "model_viewall";// 数字模型_查看全部

	/**
	 * **********审计管理********** qinyeju 2015/7/29
	 */
	public static final String SITE_BASIC_INFO_EDIT = "siteBasic_edit"; // 场所基本信息_编辑

	public static final String STAT_HISTORY_EDIT = "statHistory_edit"; // 终端上下线信息_编辑
	
	
	
	/**
	 * 网监----
	 */
	public static final String DW_SAFEVENDOR_EDIT = "dwsafeVendor_edit"; // 热点管理_编辑/
	public static final String DW_SITEINFO_EDIT = "dwsiteInfo_edit"; // 热点管理_编辑/

}