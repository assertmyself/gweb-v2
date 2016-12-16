/*******************************************************************************
 * Copyright (c) GBCOM
 * All rights reserved. This program and the accompanying materials are belong 
 * to Shanghai GBCOM Communication Technology Co.Ltd
 * http://www.gbcom.com.cn
/*******************************************************************************/
package com.gbcom.system.utils;
/**
 * 性能统计常量
 * @author xuyin
 *
 */
public class PmConst {
	/**
	 * 实时采集
	 */
	public static final int MONITOR_TYPE_REALTIME = 1;
	/**
	 * 计划采集
	 */
	public static final int MONITOR_TYPE_PLAN = 2;
	
	/**
	 * 统计粒度：小时
	 */
	public static final String UNIT_HOUR = "HOUR";
	/**
	 * 统计粒度：天
	 */
	public static final String UNIT_DAY = "DAY";
	/**
	 * 统计粒度：周
	 */
	public static final String UNIT_WEEK = "WEEK";
	/**
	 * 统计粒度：月
	 */
	public static final String UNIT_MONTH = "MONTH";
	/**
	 * 统计粒度：年
	 */
	public static final String UNIT_YEAR = "YEAR";
	/**
	 * 聚集函数：最大值
	 */
	public static final String STATISTIC_MAX = "MAX";
	/**
	 * 聚集函数：最小值
	 */
	public static final String STATISTIC_MIN = "MIN";
	/**
	 * 聚集函数：求和
	 */
	public static final String STATISTIC_SUM = "SUM";
	/**
	 * 聚集函数：求平均
	 */
	public static final String STATISTIC_AVG = "AVG";
	/**
	 * 统计方式：纵向比较
	 */
	public static final String STATISTIC_VER = "VERTICAL";
	/**
	 * 统计方式：横向统计
	 */
	public static final String STATISTIC_HOR = "HORIZONTAL";
	/**
	 * 计划类型 + 指标管理对象等
	 */
	public static final int CID_SYS = 11335577;
	/**
	 * 计划类型 + 指标管理对象等
	 */
	public static final int CID_CPE = 22446688;
	
	/**
	 * 计划类型 + 指标管理对象等
	 */
	public static final int PM_GROUPTYPE_SYS = 1;
	/**
	 * 计划类型 + 指标管理对象等
	 */
	public static final int PM_GROUPTYPE_DEV = 2;
	/**
	 * OBJECT_VALUE_ZERO
	 */
	public static final int OBJECT_VALUE_ZERO = 0;
	/**
	 * OBJECT_VALUE_ONE
	 */
	public static final int OBJECT_VALUE_ONE = 1;
	
	/**
	 * GROUPENUM_DEV_APRUN
	 */
	public static final int GROUPENUM_DEV_APRUN = 1;
	/**
	 * GROUPENUM_DEV_APFLUX_WIFI
	 */
	public static final int GROUPENUM_DEV_APFLUX_WIFI = 2;
	/**
	 * GROUPENUM_DEV_APCLIENT
	 */
	public static final int GROUPENUM_DEV_APCLIENT = 3;
	/**
	 * GROUPENUM_DEV_APFLUX_WAN
	 */
	public static final int GROUPENUM_DEV_APFLUX_WAN = 4;
	/**
	 * GROUPENUM_SYS_DEVACCESS
	 */
	public static final int GROUPENUM_SYS_DEVACCESS = 11;
	
}
