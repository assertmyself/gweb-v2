//off checkstyle
package com.gbcom.system.domain.base;

import com.hc.core.entity.Auditable;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SYS_ROLE table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 系统角色管理
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : 系统角色管理
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * SubSystem : system
 * Projectable : false
 *
 * @hibernate.class table="SYS_ROLE"
 */

public abstract class BaseSysRole implements Serializable, Auditable {

    public static String REF = "SysRole";
    public static String PROP_ROLE_NAME = "roleName";
    public static String PROP_CREATE_USER = "createUser";
    public static String PROP_DESCRIPTION = "description";
    public static String PROP_CREATE_TIME = "createTime";
    public static String PROP_UPDATE_TIME = "updateTime";
    public static String PROP_ID = "id";
    public static String PROP_CODE = "code";
    public static String PROP_UPDATE_USER = "updateUser";


    // constructors
    public BaseSysRole() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseSysRole(java.lang.Long id) {
        this.setId(id);
        initialize();
    }

    protected void initialize() {
    }


    private int hashCode = Integer.MIN_VALUE;

    // primary key
    private java.lang.Long id;

    
    
    
    //0:系统级  1：商业wifi级  2：区域级
    private Integer roleType;//fix org.hibernate.PropertyAccessException: Null value was assigned to a property of primitive type setter 
    //0: 分组 1：不分组
    private Integer viewType;
    //0:管理角色 1：域管理角色
    private Integer domainType;
    // fields
    /*角色编码*/
    /*角色编码*/
    private java.lang.String code;

    /*角色名称*/
    /*角色名称*/
    private java.lang.String roleName;

    /*描述*/
    /*描述*/
    private java.lang.String description;

    /*创建时间*/
    /*创建时间*/
    private java.sql.Timestamp createTime;

    /*更新时间*/
    /*更新时间*/
    private java.sql.Timestamp updateTime;

    /*创建人*/
    /*创建人*/
    private java.lang.String createUser;

    /*更新人*/
    /*更新人*/
    private java.lang.String updateUser;


    // collections
    private java.util.Set<com.gbcom.system.domain.SysRolePrivilege> sysRolePrivileges;
    private java.util.Set<com.gbcom.system.domain.SysUserRole> sysUserRoles;


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
     * Return the value associated with the column: CODE
     */
    public java.lang.String getCode() {
        return code;
    }

    /**
     * Set the value related to the column: CODE
     *
     * @param code the CODE value
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Return the value associated with the column: ROLE_NAME
     */
    public java.lang.String getRoleName() {
        return roleName;
    }

    /**
     * Set the value related to the column: ROLE_NAME
     *
     * @param roleName the ROLE_NAME value
     */
    public void setRoleName(java.lang.String roleName) {
        this.roleName = roleName;
    }


    /**
     * Return the value associated with the column: DESCRIPTION
     */
    public java.lang.String getDescription() {
        return description;
    }

    /**
     * Set the value related to the column: DESCRIPTION
     *
     * @param description the DESCRIPTION value
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
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
     * Return the value associated with the column: sysRolePrivileges
     */
    public java.util.Set<com.gbcom.system.domain.SysRolePrivilege> getSysRolePrivileges() {
        if (sysRolePrivileges == null) {
            sysRolePrivileges = new java.util.LinkedHashSet<com.gbcom.system.domain.SysRolePrivilege>();
        }
        return sysRolePrivileges;
    }

    /**
     * Set the value related to the column: sysRolePrivileges
     *
     * @param sysRolePrivileges the sysRolePrivileges value
     */
    public void setSysRolePrivileges(java.util.Set<com.gbcom.system.domain.SysRolePrivilege> sysRolePrivileges) {
        this.sysRolePrivileges = sysRolePrivileges;
    }

    public void addTosysRolePrivileges(com.gbcom.system.domain.SysRolePrivilege sysRolePrivilege) {
        if (null == getSysRolePrivileges())
            setSysRolePrivileges(new java.util.LinkedHashSet<com.gbcom.system.domain.SysRolePrivilege>());
        getSysRolePrivileges().add(sysRolePrivilege);
    }


    /**
     * Return the value associated with the column: sysUserRoles
     */
    public java.util.Set<com.gbcom.system.domain.SysUserRole> getSysUserRoles() {
        if (sysUserRoles == null) {
            sysUserRoles = new java.util.LinkedHashSet<com.gbcom.system.domain.SysUserRole>();
        }
        return sysUserRoles;
    }

    /**
     * Set the value related to the column: sysUserRoles
     *
     * @param sysUserRoles the sysUserRoles value
     */
    public void setSysUserRoles(java.util.Set<com.gbcom.system.domain.SysUserRole> sysUserRoles) {
        this.sysUserRoles = sysUserRoles;
    }

    public void addTosysUserRoles(com.gbcom.system.domain.SysUserRole sysUserRole) {
        if (null == getSysUserRoles())
            setSysUserRoles(new java.util.LinkedHashSet<com.gbcom.system.domain.SysUserRole>());
        getSysUserRoles().add(sysUserRole);
    }


    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (!(obj instanceof com.gbcom.system.domain.SysRole)) return false;
        else {
            com.gbcom.system.domain.SysRole sysRole = (com.gbcom.system.domain.SysRole) obj;
            if (null == this.getId() || null == sysRole.getId()) return false;
            else return (this.getId().equals(sysRole.getId()));
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
        builder.append(code);
        builder.append(roleName);
        builder.append(description);
        builder.append(createTime);
        builder.append(updateTime);
        builder.append(createUser);
        builder.append(updateUser);
        return builder.toString();
    }
	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}
	public Integer getRoleType() {
		return roleType;
	}

	public void setViewType(Integer viewType) {
		this.viewType = viewType;
	}

	public Integer getViewType() {
		return viewType;
	}

	public void setDomainType(Integer domainType) {
		this.domainType = domainType;
	}

	public Integer getDomainType() {
		return domainType;
	}



}