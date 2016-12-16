//off checkstyle
package com.gbcom.system.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SYS_USER_ROLE table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 角色与用户关联
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : 角色与用户关联
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * SubSystem : system
 * Projectable : false
 *
 * @hibernate.class table="SYS_USER_ROLE"
 */

public abstract class BaseSysUserRole implements Serializable {

    public static String REF = "SysUserRole";
    public static String PROP_USER = "user";
    public static String PROP_ROLE = "role";
    public static String PROP_ID = "id";


    // constructors
    public BaseSysUserRole() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseSysUserRole(Long id) {
        this.setId(id);
        initialize();
    }

    /**
     * Constructor for required fields
     */
    public BaseSysUserRole(
            Long id,
            com.gbcom.system.domain.SysRole role,
            com.gbcom.system.domain.SysUser user) {

        this.setId(id);
        this.setRole(role);
        this.setUser(user);
        initialize();
    }

    protected void initialize() {
    }


    private int hashCode = Integer.MIN_VALUE;

    // primary key
    private Long id;

    // many to one
    private com.gbcom.system.domain.SysRole role;
    private com.gbcom.system.domain.SysUser user;


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
        if (!(obj instanceof com.gbcom.system.domain.SysUserRole)) return false;
        else {
            com.gbcom.system.domain.SysUserRole sysUserRole = (com.gbcom.system.domain.SysUserRole) obj;
            if (null == this.getId() || null == sysUserRole.getId()) return false;
            else return (this.getId().equals(sysUserRole.getId()));
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