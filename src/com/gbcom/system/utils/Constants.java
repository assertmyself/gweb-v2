//off checkstyle
package com.gbcom.system.utils;

import com.gbcom.system.domain.SysPrivilege;

/**
 * 系统字典编码定义
 */
public class Constants {
    public static final String FLAG_TRUE = "1"; //true
    public static final String FLAG_FALSE = "0"; //false
    public static final String SYS_NOPICTURE_PIC = "no_picture.gif"; //无图片
    public static final String SYS_USER_ADMIN = "admin";  //系统管理员用户名

    /**
     * ********* 系统代码 begin *************
     */
    //系统管理
    public static final String SYS_PRIVILEGE_TYPE = SysPrivilege.class.getSimpleName(); //权限类型分类
    public static final String SYS_PRIVILEGE_TYPE_BUTTON = "button"; //权限类型---按钮权限

    //日志分类 sysLog.setLogType
    public static final String LOG_TYPE_CODE = "LOG_TYPE";   //日志分类-登录
    public static final String LOG_TYPE_LOGIN = "1";   //日志分类-登录
    public static final String LOG_TYPE_BUSSINESS = "2";  //日志分类-业务操作
    public static final String LOG_TYPE_ERROR = "3";   //日志分类-错误
    public static final String LOG_TYPE_LOGOUT = "4";  //日志分类-退出
    public static final String LOG_TYPE_WS = "5";   //日志分类-web service

    public static final String SYS_USER_TYPE = "SysUserType";   //用户类型
    public static final String SYS_PROVICE = "Provice";     //省份/直辖市
    public static final String SYS_CITY = "City";     //城市

    public static final String GRID_SQL_KEY = "gsk";  //excel导出时将sql放入session所用的key

    /****************************************** 系统代码 end *******************************************************/


    /**
     * *************************************** 业务模块 begin ******************************************************
     */
    public static final String BIZ_MONITOR_WEATHER = "MonitorWeather";     //天气

    public static final String NOTIFY_STATE_SENDING = "Sending"; //正在发送
    public static final String NOTIFY_STATE_SEND_SUCCESS = "Send_Success"; //发送成功
    public static final String NOTIFY_STATE_SEND_FAIL = "Send_Fail"; //发送失败

    /****************************************** 业务模块 end *******************************************************/


    /**
     * ****************************************** 客户端类型 start *******************************************************
     */
    public static final String OS_TYPE_ANDROID = "Android";
    public static final String OS_TYPE_IOS = "IOS";

    /**
     * ****************************************** 客户端类型 end *******************************************************
     */


    /**
     * ****************************************** AES密钥 start *******************************************************
     */
    public static final String TOKEN_AES = "GBCOM123";

    /**
     * ****************************************** AES密钥 end *******************************************************
     */

    /**
     * ****************************************** 通知类型 start *******************************************************
     */
    public static final int NOTIFY_TYPE_USER = 1;
    public static final int NOTIFY_TYPE_DEVICE = 2;

    /**
     * ****************************************** 通知类型 end *******************************************************
     */


}
