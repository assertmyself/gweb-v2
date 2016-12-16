//off checkstyle
package com.gbcom.system.domain.base;

import com.gbcom.system.domain.SysUserPrivilege;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SYS_USER table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 系统用户管理
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : 系统用户管理
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * SubSystem : system
 * Projectable : false
 *
 * @hibernate.class table="SYS_USER"
 */

public abstract class BaseSysUser implements Serializable {

    public static String REF = "SysUser";
    public static String PROP_USER_TYPE = "userType";
    public static String PROP_LOGIN_NAME = "loginName";
    public static String PROP_REASON_DESC = "reasonDesc";
    public static String PROP_PASSWORD = "password";
    public static String PROP_UPDATE_TIME = "updateTime";
    public static String PROP_DISPLAY_NAME = "displayName";
    public static String PROP_STATUS = "status";
    public static String PROP_CREATE_USER = "createUser";
    public static String PROP_CREATE_TIME = "createTime";
    public static String PROP_OPEN_DATE = "openDate";
    public static String PROP_ID = "id";
    public static String PROP_CLOSE_DATE = "closeDate";
    public static String PROP_PERSON = "person";
    public static String PROP_AREA = "area";
    public static String PROP_UPDATE_USER = "updateUser";


    // constructors
    public BaseSysUser() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseSysUser(java.lang.Long id) {
        this.setId(id);
        initialize();
    }

    /**
     * Constructor for required fields
     */
    public BaseSysUser(
            java.lang.Long id,
            java.lang.String loginName) {

        this.setId(id);
        this.setLoginName(loginName);
        initialize();
    }

    protected void initialize() {
    }


    private int hashCode = Integer.MIN_VALUE;

    // primary key
    private java.lang.Long id;

    // fields
    /*登陆名*/
    /*登陆名*/
    private java.lang.String loginName;

    /*密码*/
    /*密码*/
    private java.lang.String password;

    /*显示名称*/
    /*显示名称*/
    private java.lang.String displayName;

    /*帐号状态*/
    /*帐号状态*/
    private java.lang.String status;

    /*创建时间*/
    /*创建时间*/
    private java.sql.Timestamp createTime;

    /*更新时间*/
    /*更新时间*/
    private java.sql.Timestamp updateTime;

    /*更新人*/
    /*更新人*/
    private java.lang.String updateUser;

    /*创建人*/
    /*创建人*/
    private java.lang.String createUser;

    /*失效/锁定原因*/
    /*失效/锁定原因*/
    private java.lang.String reasonDesc;

    /*开通日期*/
    /*开通日期*/
    private java.sql.Date openDate;

    /*截止日期*/
    /*截止日期*/
    private java.sql.Date closeDate;


    // many to one
    private com.gbcom.system.domain.SysPerson person;
    private com.gbcom.system.domain.SysArea area;
    private com.gbcom.system.domain.SysCodeDetail userType;

    // collections
    private java.util.Set<com.gbcom.system.domain.SysUserRole> sysUserRoles;
    private java.util.Set<SysUserPrivilege> sysUserPrivileges;


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
     * Return the value associated with the column: LOGIN_NAME
     */
    public java.lang.String getLoginName() {
        return loginName;
    }

    /**
     * Set the value related to the column: LOGIN_NAME
     *
     * @param loginName the LOGIN_NAME value
     */
    public void setLoginName(java.lang.String loginName) {
        this.loginName = loginName;
    }


    /**
     * Return the value associated with the column: PASSWORD
     */
    public java.lang.String getPassword() {
        return password;
    }

    /**
     * Set the value related to the column: PASSWORD
     *
     * @param password the PASSWORD value
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Return the value associated with the column: DISPLAY_NAME
     */
    public java.lang.String getDisplayName() {
        return displayName;
    }

    /**
     * Set the value related to the column: DISPLAY_NAME
     *
     * @param displayName the DISPLAY_NAME value
     */
    public void setDisplayName(java.lang.String displayName) {
        this.displayName = displayName;
    }


    /**
     * Return the value associated with the column: STATUS
     */
    public java.lang.String getStatus() {
        return status;
    }

    /**
     * Set the value related to the column: STATUS
     *
     * @param status the STATUS value
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
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
     * Return the value associated with the column: REASON_DESC
     */
    public java.lang.String getReasonDesc() {
        return reasonDesc;
    }

    /**
     * Set the value related to the column: REASON_DESC
     *
     * @param reasonDesc the REASON_DESC value
     */
    public void setReasonDesc(java.lang.String reasonDesc) {
        this.reasonDesc = reasonDesc;
    }


    /**
     * Return the value associated with the column: OPEN_DATE
     */
    public java.sql.Date getOpenDate() {
        return openDate;
    }

    /**
     * Set the value related to the column: OPEN_DATE
     *
     * @param openDate the OPEN_DATE value
     */
    public void setOpenDate(java.sql.Date openDate) {
        this.openDate = openDate;
    }


    /**
     * Return the value associated with the column: CLOSE_DATE
     */
    public java.sql.Date getCloseDate() {
        return closeDate;
    }

    /**
     * Set the value related to the column: CLOSE_DATE
     *
     * @param closeDate the CLOSE_DATE value
     */
    public void setCloseDate(java.sql.Date closeDate) {
        this.closeDate = closeDate;
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


    /**
     * Return the value associated with the column: USER_TYPE_ID
     */
    public com.gbcom.system.domain.SysCodeDetail getUserType() {
        return userType;
    }

    /**
     * Set the value related to the column: USER_TYPE_ID
     *
     * @param userType the USER_TYPE_ID value
     */
    public void setUserType(com.gbcom.system.domain.SysCodeDetail userType) {
        this.userType = userType;
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


    /**
     * Return the value associated with the column: sysUserPrivileges
     */
    public java.util.Set<SysUserPrivilege> getSysUserPrivileges() {
        if (sysUserPrivileges == null) {
            sysUserPrivileges = new java.util.LinkedHashSet<SysUserPrivilege>();
        }
        return sysUserPrivileges;
    }

    /**
     * Set the value related to the column: sysUserPrivileges
     *
     * @param sysUserPrivileges the sysUserPrivileges value
     */
    public void setSysUserPrivileges(java.util.Set<SysUserPrivilege> sysUserPrivileges) {
        this.sysUserPrivileges = sysUserPrivileges;
    }

    public void addTosysUserPrivileges(SysUserPrivilege sysUserPrivilege) {
        if (null == getSysUserPrivileges()) setSysUserPrivileges(new java.util.LinkedHashSet<SysUserPrivilege>());
        getSysUserPrivileges().add(sysUserPrivilege);
    }


    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (!(obj instanceof com.gbcom.system.domain.SysUser)) return false;
        else {
            com.gbcom.system.domain.SysUser sysUser = (com.gbcom.system.domain.SysUser) obj;
            if (null == this.getId() || null == sysUser.getId()) return false;
            else return (this.getId().equals(sysUser.getId()));
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
        builder.append(loginName);
        builder.append(password);
        builder.append(displayName);
        builder.append(status);
        builder.append(createTime);
        builder.append(updateTime);
        builder.append(updateUser);
        builder.append(createUser);
        builder.append(reasonDesc);
        builder.append(openDate);
        builder.append(closeDate);
        return builder.toString();
    }

	public void setArea(com.gbcom.system.domain.SysArea area) {
		this.area = area;
	}

	public com.gbcom.system.domain.SysArea getArea() {
		return area;
	}


}