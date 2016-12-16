//off checkstyle
package com.gbcom.system.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SYS_SEQUENCE table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 序列
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : 系统序列
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * SubSystem : system
 * Projectable : false
 *
 * @hibernate.class table="SYS_SEQUENCE"
 */

public abstract class BaseSysSequence implements Serializable {

    public static String REF = "SysSequence";
    public static String PROP_LASTID = "lastid";
    public static String PROP_ID = "id";


    // constructors
    public BaseSysSequence() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseSysSequence(java.lang.String id) {
        this.setId(id);
        initialize();
    }

    /**
     * Constructor for required fields
     */
    public BaseSysSequence(
            java.lang.String id,
            java.lang.Long lastid) {

        this.setId(id);
        this.setLastid(lastid);
        initialize();
    }

    protected void initialize() {
    }


    private int hashCode = Integer.MIN_VALUE;

    // primary key
    private java.lang.String id;

    // fields
    /*下一标识*/
    /*下一标识*/
    private java.lang.Long lastid;


    /**
     * Return the unique identifier of this class
     *
     * @hibernate.id generator-class="com.hc.core.orm.hibernate.LongIdGenerator"
     * column="CODE"
     */
    public java.lang.String getId() {
        return id;
    }

    /**
     * Set the unique identifier of this class
     *
     * @param id the new ID
     * @deprecated
     */
    public void setId(java.lang.String id) {
        this.id = id;
        this.hashCode = Integer.MIN_VALUE;
    }


    /**
     * Return the value associated with the column: LASTID
     */
    public java.lang.Long getLastid() {
        return lastid;
    }

    /**
     * Set the value related to the column: LASTID
     *
     * @param lastid the LASTID value
     */
    public void setLastid(java.lang.Long lastid) {
        this.lastid = lastid;
    }


    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (!(obj instanceof com.gbcom.system.domain.SysSequence)) return false;
        else {
            com.gbcom.system.domain.SysSequence sysSequence = (com.gbcom.system.domain.SysSequence) obj;
            if (null == this.getId() || null == sysSequence.getId()) return false;
            else return (this.getId().equals(sysSequence.getId()));
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
        builder.append(lastid);
        return builder.toString();
    }


}