//off checkstyle
package com.gbcom.system.domain.base;

import java.io.Serializable;

import com.gbcom.system.aop.UserLog;


/**
 * This is an object that contains data related to the SYS_LOG table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 用户日志
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : 系统日志
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * SubSystem : system
 * Projectable : false
 *
 * @hibernate.class table="SYS_LOG"
 */

public abstract class BaseSysLog implements Serializable {

    public static String REF = "SysLog";
    public static String PROP_USER = "user";
    public static String PROP_IE_VERSION = "ieVersion";
    public static String PROP_PAGE_URL = "pageUrl";
    public static String PROP_OUT_TIME = "outTime";
    public static String PROP_SESSIONID = "sessionid";
    public static String PROP_ID = "id";
    public static String PROP_LOG_TYPE = "logType";
    public static String PROP_IP_ADDRESS = "ipAddress";
    public static String PROP_ENTER_TIME = "enterTime";

    
    

    
    // constructors
    public BaseSysLog() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseSysLog(java.lang.Long id) {
        this.setId(id);
        initialize();
    }

    protected void initialize() {
    }


    private int hashCode = Integer.MIN_VALUE;

    // primary key
    private java.lang.Long id;

    // fields
    /*IP地址*/
    /*IP地址*/
    private java.lang.String ipAddress;

    /*进入时间*/
    /*进入时间*/
    private java.sql.Timestamp enterTime;

    /*完成时间*/
    /*完成时间*/
    private java.sql.Timestamp outTime;

    /*访问页面*/
    /*访问页面*/
    private java.lang.String pageUrl;

    /*浏览器版本*/
    /*浏览器版本*/
    private java.lang.String ieVersion;

    /*SESSIONID*/
    /*SESSIONID*/
    private java.lang.String sessionid;


    
    
    private java.lang.String result = UserLog.USERLOG_RESULT_SUCCESS;
    private java.lang.String moudle;
    private java.lang.String eventType;
    private java.lang.String message;
    private java.lang.String logType;
    // many to one
//    private com.gbcom.system.domain.SysCodeDetail logType;
    private com.gbcom.system.domain.SysUser user;


    /**
     * Return the unique identifier of this class
     *
     * @hibernate.id generator-class="com.hc.core.orm.hibernate.LongIdGenerator"
     * column="ID"
     */
    public java.lang.Long getId() {
        return id;
    }

    /**
     * Set the unique identifier of this class
     *
     * @param id the new ID
     * @deprecated
     */
    public void setId(java.lang.Long id) {
        this.id = id;
        this.hashCode = Integer.MIN_VALUE;
    }


    /**
     * Return the value associated with the column: IP_ADDRESS
     */
    public java.lang.String getIpAddress() {
        return ipAddress;
    }

    /**
     * Set the value related to the column: IP_ADDRESS
     *
     * @param ipAddress the IP_ADDRESS value
     */
    public void setIpAddress(java.lang.String ipAddress) {
        this.ipAddress = ipAddress;
    }


    /**
     * Return the value associated with the column: ENTER_TIME
     */
    public java.sql.Timestamp getEnterTime() {
        return enterTime;
    }

    /**
     * Set the value related to the column: ENTER_TIME
     *
     * @param enterTime the ENTER_TIME value
     */
    public void setEnterTime(java.sql.Timestamp enterTime) {
        this.enterTime = enterTime;
    }


    /**
     * Return the value associated with the column: OUT_TIME
     */
    public java.sql.Timestamp getOutTime() {
        return outTime;
    }

    /**
     * Set the value related to the column: OUT_TIME
     *
     * @param outTime the OUT_TIME value
     */
    public void setOutTime(java.sql.Timestamp outTime) {
        this.outTime = outTime;
    }


    /**
     * Return the value associated with the column: PAGE_URL
     */
    public java.lang.String getPageUrl() {
        return pageUrl;
    }

    /**
     * Set the value related to the column: PAGE_URL
     *
     * @param pageUrl the PAGE_URL value
     */
    public void setPageUrl(java.lang.String pageUrl) {
        this.pageUrl = pageUrl;
    }


    /**
     * Return the value associated with the column: IE_VERSION
     */
    public java.lang.String getIeVersion() {
        return ieVersion;
    }

    /**
     * Set the value related to the column: IE_VERSION
     *
     * @param ieVersion the IE_VERSION value
     */
    public void setIeVersion(java.lang.String ieVersion) {
        this.ieVersion = ieVersion;
    }


    /**
     * Return the value associated with the column: SESSIONID
     */
    public java.lang.String getSessionid() {
        return sessionid;
    }

    /**
     * Set the value related to the column: SESSIONID
     *
     * @param sessionid the SESSIONID value
     */
    public void setSessionid(java.lang.String sessionid) {
        this.sessionid = sessionid;
    }

/*
    *//**
     * Return the value associated with the column: LOG_TYPE
     *//*
    public com.gbcom.system.domain.SysCodeDetail getLogType() {
        return logType;
    }

    *//**
     * Set the value related to the column: LOG_TYPE
     *
     * @param logType the LOG_TYPE value
     *//*
    public void setLogType(com.gbcom.system.domain.SysCodeDetail logType) {
        this.logType = logType;
    }
*/

    /**
     * Return the value associated with the column: USER_ID
     */
    public com.gbcom.system.domain.SysUser getUser() {
        return user;
    }

    /**
     * Set the value related to the column: USER_ID
     *
     * @param user the USER_ID value
     */
    public void setUser(com.gbcom.system.domain.SysUser user) {
        this.user = user;
    }


    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (!(obj instanceof com.gbcom.system.domain.SysLog)) return false;
        else {
            com.gbcom.system.domain.SysLog sysLog = (com.gbcom.system.domain.SysLog) obj;
            if (null == this.getId() || null == sysLog.getId()) return false;
            else return (this.getId().equals(sysLog.getId()));
        }
    }

    public int hashCode() {
        if (Integer.MIN_VALUE == this.hashCode) {
            if (null == this.getId()) return super.hashCode();
            else {
                String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
                this.hashCode = hashStr.hashCode();
            }
        }
        return this.hashCode;
    }


    public String toString() {
        org.apache.commons.lang.builder.ToStringBuilder builder = new org.apache.commons.lang.builder.ToStringBuilder(this);
        builder.append(id);
        builder.append(ipAddress);
        builder.append(enterTime);
        builder.append(outTime);
        builder.append(pageUrl);
        builder.append(ieVersion);
        builder.append(sessionid);
        return builder.toString();
    }

	public void setMoudle(java.lang.String moudle) {
		this.moudle = moudle;
	}

	public java.lang.String getMoudle() {
		return moudle;
	}

	public void setEventType(java.lang.String eventType) {
		this.eventType = eventType;
	}

	public java.lang.String getEventType() {
		return eventType;
	}

	public void setResult(java.lang.String result) {
		this.result = result;
	}

	public java.lang.String getResult() {
		return result;
	}

	public void setMessage(java.lang.String message) {
		this.message = message;
	}

	public java.lang.String getMessage() {
		return message;
	}

	public void setLogType(java.lang.String logType) {
		this.logType = logType;
	}

	public java.lang.String getLogType() {
		return logType;
	}


}