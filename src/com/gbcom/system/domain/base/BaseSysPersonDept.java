//off checkstyle
package com.gbcom.system.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SYS_PERSON_DEPT table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 系统单位与人员的关联表
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : 系统单位与人员的关联表
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * SubSystem : system
 * Projectable : false
 *
 * @hibernate.class table="SYS_PERSON_DEPT"
 */

public abstract class BaseSysPersonDept implements Serializable {

    public static String REF = "SysPersonDept";
    public static String PROP_ORDER_NO = "orderNo";
    public static String PROP_DEPT = "dept";
    public static String PROP_IS_VALID = "isValid";
    public static String PROP_POSITION = "position";
    public static String PROP_ID = "id";
    public static String PROP_PERSON = "person";
    public static String PROP_IS_MANAGER = "isManager";


    // constructors
    public BaseSysPersonDept() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseSysPersonDept(java.lang.Long id) {
        this.setId(id);
        initialize();
    }

    /**
     * Constructor for required fields
     */
    public BaseSysPersonDept(
            java.lang.Long id,
            com.gbcom.system.domain.SysDept dept,
            com.gbcom.system.domain.SysPerson person) {

        this.setId(id);
        this.setDept(dept);
        this.setPerson(person);
        initialize();
    }

    protected void initialize() {
    }


    private int hashCode = Integer.MIN_VALUE;

    // primary key
    private java.lang.Long id;

    // fields
    /*职务*/
    /*职务*/
    private java.lang.String position;

    /*序号*/
    /*序号*/
    private java.lang.Long orderNo;

    /*是否有效*/
    /*是否有效*/
    private java.lang.Boolean isValid;

    /*是否单位负责人*/
    /*是否单位负责人*/
    private java.lang.Boolean isManager;


    // many to one
    private com.gbcom.system.domain.SysDept dept;
    private com.gbcom.system.domain.SysPerson person;


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
     * Return the value associated with the column: POSITION
     */
    public java.lang.String getPosition() {
        return position;
    }

    /**
     * Set the value related to the column: POSITION
     *
     * @param position the POSITION value
     */
    public void setPosition(java.lang.String position) {
        this.position = position;
    }


    /**
     * Return the value associated with the column: ORDER_NO
     */
    public java.lang.Long getOrderNo() {
        return orderNo;
    }

    /**
     * Set the value related to the column: ORDER_NO
     *
     * @param orderNo the ORDER_NO value
     */
    public void setOrderNo(java.lang.Long orderNo) {
        this.orderNo = orderNo;
    }


    /**
     * Return the value associated with the column: IS_VALID
     */
    public java.lang.Boolean getIsValid() {
        return isValid;
    }

    /**
     * Set the value related to the column: IS_VALID
     *
     * @param isValid the IS_VALID value
     */
    public void setIsValid(java.lang.Boolean isValid) {
        this.isValid = isValid;
    }


    /**
     * Return the value associated with the column: IS_MANAGER
     */
    public java.lang.Boolean getIsManager() {
        return isManager;
    }

    /**
     * Set the value related to the column: IS_MANAGER
     *
     * @param isManager the IS_MANAGER value
     */
    public void setIsManager(java.lang.Boolean isManager) {
        this.isManager = isManager;
    }


    /**
     * Return the value associated with the column: DEPT_ID
     */
    public com.gbcom.system.domain.SysDept getDept() {
        return dept;
    }

    /**
     * Set the value related to the column: DEPT_ID
     *
     * @param dept the DEPT_ID value
     */
    public void setDept(com.gbcom.system.domain.SysDept dept) {
        this.dept = dept;
    }


    /**
     * Return the value associated with the column: PERSON_ID
     */
    public com.gbcom.system.domain.SysPerson getPerson() {
        return person;
    }

    /**
     * Set the value related to the column: PERSON_ID
     *
     * @param person the PERSON_ID value
     */
    public void setPerson(com.gbcom.system.domain.SysPerson person) {
        this.person = person;
    }


    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (!(obj instanceof com.gbcom.system.domain.SysPersonDept)) return false;
        else {
            com.gbcom.system.domain.SysPersonDept sysPersonDept = (com.gbcom.system.domain.SysPersonDept) obj;
            if (null == this.getId() || null == sysPersonDept.getId()) return false;
            else return (this.getId().equals(sysPersonDept.getId()));
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
        builder.append(position);
        builder.append(orderNo);
        builder.append(isValid);
        builder.append(isManager);
        return builder.toString();
    }


}