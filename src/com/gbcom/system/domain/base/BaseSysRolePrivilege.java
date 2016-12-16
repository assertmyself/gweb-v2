//off checkstyle
package com.gbcom.system.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SYS_ROLE_PRIVILEGE table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 系统角色与权限关联
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : 系统角色与权限关联
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * SubSystem : system
 * Projectable : false
 *
 * @hibernate.class table="SYS_ROLE_PRIVILEGE"
 */

public abstract class BaseSysRolePrivilege implements Serializable {

    public static String REF = "SysRolePrivilege";
    public static String PROP_PRIVILEGE = "privilege";
    public static String PROP_ROLE = "role";
    public static String PROP_ID = "id";


    // constructors
    public BaseSysRolePrivilege() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseSysRolePrivilege(java.lang.Long id) {
        this.setId(id);
        initialize();
    }

    /**
     * Constructor for required fields
     */
    public BaseSysRolePrivilege(
            java.lang.Long id,
            com.gbcom.system.domain.SysPrivilege privilege,
            com.gbcom.system.domain.SysRole role) {

        this.setId(id);
        this.setPrivilege(privilege);
        this.setRole(role);
        initialize();
    }

    protected void initialize() {
    }


    private int hashCode = Integer.MIN_VALUE;

    // primary key
    private java.lang.Long id;

    // many to one
    private com.gbcom.system.domain.SysPrivilege privilege;
    private com.gbcom.system.domain.SysRole role;


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


    /**
     * Return the value associated with the column: ROLE_ID
     */
    public com.gbcom.system.domain.SysRole getRole() {
        return role;
    }

    /**
     * Set the value related to the column: ROLE_ID
     *
     * @param role the ROLE_ID value
     */
    public void setRole(com.gbcom.system.domain.SysRole role) {
        this.role = role;
    }


    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (!(obj instanceof com.gbcom.system.domain.SysRolePrivilege)) return false;
        else {
            com.gbcom.system.domain.SysRolePrivilege sysRolePrivilege = (com.gbcom.system.domain.SysRolePrivilege) obj;
            if (null == this.getId() || null == sysRolePrivilege.getId()) return false;
            else return (this.getId().equals(sysRolePrivilege.getId()));
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
        return builder.toString();
    }


}