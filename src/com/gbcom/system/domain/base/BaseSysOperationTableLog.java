//off checkstyle
package com.gbcom.system.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SYS_OPERATION_TABLE_LOG table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * SubSystem : system
 * Projectable : false
 *
 * @hibernate.class table="SYS_OPERATION_TABLE_LOG"
 */

public abstract class BaseSysOperationTableLog implements Serializable {

    public static String REF = "SysOperationTableLog";
    public static String PROP_CREATE_USER = "createUser";
    public static String PROP_LOG_XML = "logXml";
    public static String PROP_CREATE_TIME = "createTime";
    public static String PROP_UPDATE_TIME = "updateTime";
    public static String PROP_ID = "id";
    public static String PROP_IP_ADDRESS = "ipAddress";
    public static String PROP_TABLE = "table";
    public static String PROP_UPDATE_USER = "updateUser";


    // constructors
    public BaseSysOperationTableLog() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseSysOperationTableLog(java.lang.Long id) {
        this.setId(id);
        initialize();
    }

    protected void initialize() {
    }


    private int hashCode = Integer.MIN_VALUE;

    // primary key
    private java.lang.Long id;

    // fields
    private java.lang.String logXml;

    private java.lang.String ipAddress;

    private java.sql.Timestamp createTime;

    private java.sql.Timestamp updateTime;

    private java.lang.String updateUser;

    private java.lang.String createUser;


    // many to one
    private com.gbcom.system.domain.ConfigTable table;


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
     * Return the value associated with the column: LOG_XML
     */
    public java.lang.String getLogXml() {
        return logXml;
    }

    /**
     * Set the value related to the column: LOG_XML
     *
     * @param logXml the LOG_XML value
     */
    public void setLogXml(java.lang.String logXml) {
        this.logXml = logXml;
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
     * Return the value associated with the column: CREATE_TIME
     */
    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * Set the value related to the column: CREATE_TIME
     *
     * @param createTime the CREATE_TIME value
     */
    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }


    /**
     * Return the value associated with the column: UPDATE_TIME
     */
    public java.sql.Timestamp getUpdateTime() {
        return updateTime;
    }

    /**
     * Set the value related to the column: UPDATE_TIME
     *
     * @param updateTime the UPDATE_TIME value
     */
    public void setUpdateTime(java.sql.Timestamp updateTime) {
        this.updateTime = updateTime;
    }


    /**
     * Return the value associated with the column: UPDATE_USER
     */
    public java.lang.String getUpdateUser() {
        return updateUser;
    }

    /**
     * Set the value related to the column: UPDATE_USER
     *
     * @param updateUser the UPDATE_USER value
     */
    public void setUpdateUser(java.lang.String updateUser) {
        this.updateUser = updateUser;
    }


    /**
     * Return the value associated with the column: CREATE_USER
     */
    public java.lang.String getCreateUser() {
        return createUser;
    }

    /**
     * Set the value related to the column: CREATE_USER
     *
     * @param createUser the CREATE_USER value
     */
    public void setCreateUser(java.lang.String createUser) {
        this.createUser = createUser;
    }


    /**
     * Return the value associated with the column: TABLE_ID
     */
    public com.gbcom.system.domain.ConfigTable getTable() {
        return table;
    }

    /**
     * Set the value related to the column: TABLE_ID
     *
     * @param table the TABLE_ID value
     */
    public void setTable(com.gbcom.system.domain.ConfigTable table) {
        this.table = table;
    }


    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (!(obj instanceof com.gbcom.system.domain.SysOperationTableLog)) return false;
        else {
            com.gbcom.system.domain.SysOperationTableLog sysOperationTableLog = (com.gbcom.system.domain.SysOperationTableLog) obj;
            if (null == this.getId() || null == sysOperationTableLog.getId()) return false;
            else return (this.getId().equals(sysOperationTableLog.getId()));
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
        builder.append(logXml);
        builder.append(ipAddress);
        builder.append(createTime);
        builder.append(updateTime);
        builder.append(updateUser);
        builder.append(createUser);
        return builder.toString();
    }


}