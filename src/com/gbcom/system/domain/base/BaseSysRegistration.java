//off checkstyle
package com.gbcom.system.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SYS_REGISTRATION table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 注册信息
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : 注册信息
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * SubSystem : system
 * Projectable : false
 *
 * @hibernate.class table="SYS_REGISTRATION"
 */

public abstract class BaseSysRegistration implements Serializable {

    public static String REF = "SysRegistration";
    public static String PROP_TELEPHONE = "telephone";
    public static String PROP_REGISTER_DATE = "registerDate";
    public static String PROP_CHECK_USER = "checkUser";
    public static String PROP_PROVINCE = "province";
    public static String PROP_ORGANIZATION_CODE = "organizationCode";
    public static String PROP_ORGANIZATION_CERTIFICATE = "organizationCertificate";
    public static String PROP_CONTACTER = "contacter";
    public static String PROP_CITY = "city";
    public static String PROP_NAME = "name";
    public static String PROP_LICENSE = "license";
    public static String PROP_CHECK_RESULT = "checkResult";
    public static String PROP_ADDRESS = "address";
    public static String PROP_NOTIFY_PHONE = "notifyPhone";
    public static String PROP_ID = "id";
    public static String PROP_CHECK_DATE = "checkDate";


    // constructors
    public BaseSysRegistration() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseSysRegistration(java.lang.Long id) {
        this.setId(id);
        initialize();
    }

    /**
     * Constructor for required fields
     */
    public BaseSysRegistration(
            java.lang.Long id,
            java.lang.String name,
            java.lang.String organizationCode,
            java.lang.String address,
            java.lang.String contacter,
            java.lang.String telephone,
            java.lang.String notifyPhone) {

        this.setId(id);
        this.setName(name);
        this.setOrganizationCode(organizationCode);
        this.setAddress(address);
        this.setContacter(contacter);
        this.setTelephone(telephone);
        this.setNotifyPhone(notifyPhone);
        initialize();
    }

    protected void initialize() {
    }


    private int hashCode = Integer.MIN_VALUE;

    // primary key
    private java.lang.Long id;

    // fields
    /*企业名称*/
    /*企业名称*/
    private java.lang.String name;

    /*机构代码证*/
    /*机构代码证*/
    private java.lang.String organizationCode;

    /*地址*/
    /*地址*/
    private java.lang.String address;

    /*联系人*/
    /*联系人*/
    private java.lang.String contacter;

    /*联系人号码*/
    /*联系人号码*/
    private java.lang.String telephone;

    /*短信通知号码*/
    /*短信通知号码*/
    private java.lang.String notifyPhone;

    /*营业执照扫描件*/
    /*营业执照扫描件*/
    private java.lang.String license;

    /*机构代码证扫描件*/
    /*机构代码证扫描件*/
    private Byte[] organizationCertificate;

    /*注册时间*/
    /*注册时间*/
    private java.sql.Date registerDate;

    /*审核结果*/
    /*审核结果*/
    private java.lang.String checkResult;

    /*审核时间*/
    /*审核时间*/
    private java.sql.Date checkDate;


    // many to one
    private com.gbcom.system.domain.SysUser checkUser;
    private com.gbcom.system.domain.SysCodeDetail city;
    private com.gbcom.system.domain.SysCodeDetail province;


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
     * Return the value associated with the column: NAME
     */
    public java.lang.String getName() {
        return name;
    }

    /**
     * Set the value related to the column: NAME
     *
     * @param name the NAME value
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Return the value associated with the column: ORGANIZATION_CODE
     */
    public java.lang.String getOrganizationCode() {
        return organizationCode;
    }

    /**
     * Set the value related to the column: ORGANIZATION_CODE
     *
     * @param organizationCode the ORGANIZATION_CODE value
     */
    public void setOrganizationCode(java.lang.String organizationCode) {
        this.organizationCode = organizationCode;
    }


    /**
     * Return the value associated with the column: ADDRESS
     */
    public java.lang.String getAddress() {
        return address;
    }

    /**
     * Set the value related to the column: ADDRESS
     *
     * @param address the ADDRESS value
     */
    public void setAddress(java.lang.String address) {
        this.address = address;
    }


    /**
     * Return the value associated with the column: CONTACTER
     */
    public java.lang.String getContacter() {
        return contacter;
    }

    /**
     * Set the value related to the column: CONTACTER
     *
     * @param contacter the CONTACTER value
     */
    public void setContacter(java.lang.String contacter) {
        this.contacter = contacter;
    }


    /**
     * Return the value associated with the column: TELEPHONE
     */
    public java.lang.String getTelephone() {
        return telephone;
    }

    /**
     * Set the value related to the column: TELEPHONE
     *
     * @param telephone the TELEPHONE value
     */
    public void setTelephone(java.lang.String telephone) {
        this.telephone = telephone;
    }


    /**
     * Return the value associated with the column: NOTIFY_PHONE
     */
    public java.lang.String getNotifyPhone() {
        return notifyPhone;
    }

    /**
     * Set the value related to the column: NOTIFY_PHONE
     *
     * @param notifyPhone the NOTIFY_PHONE value
     */
    public void setNotifyPhone(java.lang.String notifyPhone) {
        this.notifyPhone = notifyPhone;
    }


    /**
     * Return the value associated with the column: LICENSE
     */
    public java.lang.String getLicense() {
        return license;
    }

    /**
     * Set the value related to the column: LICENSE
     *
     * @param license the LICENSE value
     */
    public void setLicense(java.lang.String license) {
        this.license = license;
    }

    public Byte[] getOrganizationCertificate() {
        return organizationCertificate;
    }

    public void setOrganizationCertificate(Byte[] organizationCertificate) {
        this.organizationCertificate = organizationCertificate;
    }

    /**
     * Return the value associated with the column: REGISTER_DATE
     */
    public java.sql.Date getRegisterDate() {
        return registerDate;
    }

    /**
     * Set the value related to the column: REGISTER_DATE
     *
     * @param registerDate the REGISTER_DATE value
     */
    public void setRegisterDate(java.sql.Date registerDate) {
        this.registerDate = registerDate;
    }


    /**
     * Return the value associated with the column: CHECK_RESULT
     */
    public java.lang.String getCheckResult() {
        return checkResult;
    }

    /**
     * Set the value related to the column: CHECK_RESULT
     *
     * @param checkResult the CHECK_RESULT value
     */
    public void setCheckResult(java.lang.String checkResult) {
        this.checkResult = checkResult;
    }


    /**
     * Return the value associated with the column: CHECK_DATE
     */
    public java.sql.Date getCheckDate() {
        return checkDate;
    }

    /**
     * Set the value related to the column: CHECK_DATE
     *
     * @param checkDate the CHECK_DATE value
     */
    public void setCheckDate(java.sql.Date checkDate) {
        this.checkDate = checkDate;
    }


    /**
     * Return the value associated with the column: CHECK_USER_ID
     */
    public com.gbcom.system.domain.SysUser getCheckUser() {
        return checkUser;
    }

    /**
     * Set the value related to the column: CHECK_USER_ID
     *
     * @param checkUser the CHECK_USER_ID value
     */
    public void setCheckUser(com.gbcom.system.domain.SysUser checkUser) {
        this.checkUser = checkUser;
    }


    /**
     * Return the value associated with the column: CITY_ID
     */
    public com.gbcom.system.domain.SysCodeDetail getCity() {
        return city;
    }

    /**
     * Set the value related to the column: CITY_ID
     *
     * @param city the CITY_ID value
     */
    public void setCity(com.gbcom.system.domain.SysCodeDetail city) {
        this.city = city;
    }


    /**
     * Return the value associated with the column: PROVINCE_ID
     */
    public com.gbcom.system.domain.SysCodeDetail getProvince() {
        return province;
    }

    /**
     * Set the value related to the column: PROVINCE_ID
     *
     * @param province the PROVINCE_ID value
     */
    public void setProvince(com.gbcom.system.domain.SysCodeDetail province) {
        this.province = province;
    }


    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (!(obj instanceof com.gbcom.system.domain.SysRegistration)) return false;
        else {
            com.gbcom.system.domain.SysRegistration sysRegistration = (com.gbcom.system.domain.SysRegistration) obj;
            if (null == this.getId() || null == sysRegistration.getId()) return false;
            else return (this.getId().equals(sysRegistration.getId()));
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
        builder.append(name);
        builder.append(organizationCode);
        builder.append(address);
        builder.append(contacter);
        builder.append(telephone);
        builder.append(notifyPhone);
        builder.append(license);
        builder.append(organizationCertificate);
        builder.append(registerDate);
        builder.append(checkResult);
        builder.append(checkDate);
        return builder.toString();
    }


}