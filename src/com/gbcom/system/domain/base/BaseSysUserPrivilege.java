//off checkstyle
package com.gbcom.system.domain.base;

import com.gbcom.system.domain.SysUserPrivilege;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SYS_USER_PRIVILEGE table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 用户与权限关联
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : 用户与权限关联
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * SubSystem : system
 * Projectable : false
 *
 * @hibernate.class table="SYS_USER_PRIVILEGE"
 */

public abstract class BaseSysUserPrivilege implements Serializable {

    public static String REF = "SysUserPrivilege";
    public static String PROP_PRIVILEGE = "privilege";
    public static String PROP_USER = "user";
    public static String PROP_ID = "id";
    public static String PROP_IS_DENY = "isDeny";


    // constructors
    public BaseSysUserPrivilege() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseSysUserPrivilege(Long id) {
        this.setId(id);
        initialize();
    }

    /**
     * Constructor for required fields
     */
    public BaseSysUserPrivilege(
            Long id,
            com.gbcom.system.domain.SysUser user,
            com.gbcom.system.domain.SysPrivilege privilege) {

        this.setId(id);
        this.setUser(user);
        this.setPrivilege(privilege);
        initialize();
    }

    protected void initialize() {
    }


    private int hashCode = Integer.MIN_VALUE;

    // primary key
    private Long id;

    // fields
    /*是否禁止授权*/
    /*是否禁止授权*/
    private Boolean isDeny;


    // many to one
    private com.gbcom.system.domain.SysUser user;
    private com.gbcom.system.domain.SysPrivilege privilege;


    /**
     * Return the unique identifier of this class
     *
     * @hibernate.id generator-class="com.hc.core.orm.hibernate.LongIdGenerator"
     * column="ID"
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the unique identifier of this class
     *
     * @param id the new ID
     * @deprecated
     */
    public void setId(Long id) {
        this.id = id;
        this.hashCode = Integer.MIN_VALUE;
    }


    /**
     * Return the value associated with the column: IS_DENY
     */
    public Boolean getIsDeny() {
        return isDeny;
    }

    /**
     * Set the value related to the column: IS_DENY
     *
     * @param isDeny the IS_DENY value
     */
    public void setIsDeny(Boolean isDeny) {
        this.isDeny = isDeny;
    }


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


    /**
     * Return the value associated with the column: PRIVILEGE_ID
     */
    public com.gbcom.system.domain.SysPrivilege getPrivilege() {
        return privilege;
    }

    /**
     * Set the value related to the column: PRIVILEGE_ID
     *
     * @param privilege the PRIVILEGE_ID value
     */
    public void setPrivilege(com.gbcom.system.domain.SysPrivilege privilege) {
        this.privilege = privilege;
    }


    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (!(obj instanceof SysUserPrivilege)) return false;
        else {
            SysUserPrivilege sysUserPrivilege = (SysUserPrivilege) obj;
            if (null == this.getId() || null == sysUserPrivilege.getId()) return false;
            else return (this.getId().equals(sysUserPrivilege.getId()));
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
        builder.append(isDeny);
        return builder.toString();
    }


}