//off checkstyle
package com.gbcom.system.domain.base;

import com.hc.core.orm.log.TableLogConfig;

import java.io.Serializable;


/**
 * This is an object that contains data related to the CONFIG_TABLE table.
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
 * @hibernate.class table="CONFIG_TABLE"
 */

public abstract class BaseConfigTable implements Serializable, TableLogConfig {

    public static String REF = "ConfigTable";
    public static String PROP_IS_LOG = "isLog";
    public static String PROP_CREATE_USER = "createUser";
    public static String PROP_CREATE_TIME = "createTime";
    public static String PROP_UPDATE_TIME = "updateTime";
    public static String PROP_ID = "id";
    public static String PROP_TABLE_NAME = "tableName";
    public static String PROP_UPDATE_USER = "updateUser";
    public static String PROP_EXTEND_XML = "extendXml";


    // constructors
    public BaseConfigTable() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseConfigTable(java.lang.Long id) {
        this.setId(id);
        initialize();
    }

    protected void initialize() {
    }


    private int hashCode = Integer.MIN_VALUE;

    // primary key
    private java.lang.Long id;

    // fields
    private java.lang.String tableName;

    private java.lang.Boolean isLog;

    private java.lang.String extendXml;

    private java.sql.Timestamp createTime;

    private java.sql.Timestamp updateTime;

    private java.lang.String updateUser;

    private java.lang.String createUser;


    // collections
    private java.util.Set<com.gbcom.system.domain.SysOperationTableLog> sysOperationTableLogs;


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
     * Return the value associated with the column: TABLE_NAME
     */
    public java.lang.String getTableName() {
        return tableName;
    }

    /**
     * Set the value related to the column: TABLE_NAME
     *
     * @param tableName the TABLE_NAME value
     */
    public void setTableName(java.lang.String tableName) {
        this.tableName = tableName;
    }


    /**
     * Return the value associated with the column: IS_LOG
     */
    public java.lang.Boolean getIsLog() {
        return isLog;
    }

    /**
     * Set the value related to the column: IS_LOG
     *
     * @param isLog the IS_LOG value
     */
    public void setIsLog(java.lang.Boolean isLog) {
        this.isLog = isLog;
    }


    /**
     * Return the value associated with the column: EXTEND_XML
     */
    public java.lang.String getExtendXml() {
        return extendXml;
    }

    /**
     * Set the value related to the column: EXTEND_XML
     *
     * @param extendXml the EXTEND_XML value
     */
    public void setExtendXml(java.lang.String extendXml) {
        this.extendXml = extendXml;
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
     * Return the value associated with the column: sysOperationTableLogs
     */
    public java.util.Set<com.gbcom.system.domain.SysOperationTableLog> getSysOperationTableLogs() {
        if (sysOperationTableLogs == null) {
            sysOperationTableLogs = new java.util.LinkedHashSet<com.gbcom.system.domain.SysOperationTableLog>();
        }
        return sysOperationTableLogs;
    }

    /**
     * Set the value related to the column: sysOperationTableLogs
     *
     * @param sysOperationTableLogs the sysOperationTableLogs value
     */
    public void setSysOperationTableLogs(java.util.Set<com.gbcom.system.domain.SysOperationTableLog> sysOperationTableLogs) {
        this.sysOperationTableLogs = sysOperationTableLogs;
    }

    public void addTosysOperationTableLogs(com.gbcom.system.domain.SysOperationTableLog sysOperationTableLog) {
        if (null == getSysOperationTableLogs())
            setSysOperationTableLogs(new java.util.LinkedHashSet<com.gbcom.system.domain.SysOperationTableLog>());
        getSysOperationTableLogs().add(sysOperationTableLog);
    }


    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (!(obj instanceof com.gbcom.system.domain.ConfigTable)) return false;
        else {
            com.gbcom.system.domain.ConfigTable configTable = (com.gbcom.system.domain.ConfigTable) obj;
            if (null == this.getId() || null == configTable.getId()) return false;
            else return (this.getId().equals(configTable.getId()));
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
        builder.append(tableName);
        builder.append(isLog);
        builder.append(extendXml);
        builder.append(createTime);
        builder.append(updateTime);
        builder.append(updateUser);
        builder.append(createUser);
        return builder.toString();
    }


}