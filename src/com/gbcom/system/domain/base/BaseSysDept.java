//off checkstyle
package com.gbcom.system.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SYS_DEPT table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 系统单位部门
 * SyncTemplatepatterns : tree\w*
 * SyncDao : false
 * TableName : 系统单位部门
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : true
 * SubSystem : system
 * Projectable : false
 *
 * @hibernate.class table="SYS_DEPT"
 */

public abstract class BaseSysDept implements Serializable, com.hc.core.entity.Treeable {

    public static String REF = "SysDept";
    public static String PROP_PARENT = "parent";
    public static String PROP_TELEPHONE = "telephone";
    public static String PROP_UPDATE_TIME = "updateTime";
    public static String PROP_ORGANIZATION_CODE = "organizationCode";
    public static String PROP_CODE = "code";
    public static String PROP_CONTACTER = "contacter";
    public static String PROP_NAME = "name";
    public static String PROP_CREATE_USER = "createUser";
    public static String PROP_IS_LEAF = "isLeaf";
    public static String PROP_ORDER_NO = "orderNo";
    public static String PROP_IS_VALID = "isValid";
    public static String PROP_CREATE_TIME = "createTime";
    public static String PROP_SHORT_NAME = "shortName";
    public static String PROP_ADDRESS = "address";
    public static String PROP_MEMO = "memo";
    public static String PROP_ID = "id";
    public static String PROP_IS_COMAPNY = "isCompany";
    public static String PROP_TREE_ID = "treeId";
    public static String PROP_UPDATE_USER = "updateUser";


    // constructors
    public BaseSysDept() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseSysDept(java.lang.Long id) {
        this.setId(id);
        initialize();
    }

    protected void initialize() {
    }


    private int hashCode = Integer.MIN_VALUE;

    // primary key
    private java.lang.Long id;

    // fields
    /*单位代码*/
    /*单位代码*/
    private java.lang.String code;

    /*单位名称*/
    /*单位名称*/
    private java.lang.String name;

    /*简称*/
    /*简称*/
    private java.lang.String shortName;

    /*机构代码证*/
    /*机构代码证*/
    private java.lang.String organizationCode;

    /*注册地址*/
    /*注册地址*/
    private java.lang.String address;

    /*联系人*/
    /*联系人*/
    private java.lang.String contacter;

    /*联系电话*/
    /*联系电话*/
    private java.lang.String telephone;

    /*单位标志*/
    /*单位标志*/
    private java.lang.Boolean isCompany;

    /*是否有效*/
    /*是否有效*/
    private java.lang.Boolean isValid;

    /*叶节点*/
    /*叶节点*/
    private java.lang.Boolean isLeaf;

    /*树形层次*/
    /*树形层次*/
    private java.lang.String treeId;

    /*排序*/
    /*排序*/
    private java.lang.Long orderNo;

    /*备注*/
    /*备注*/
    private java.lang.String memo;

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


    // many to one
    private com.gbcom.system.domain.SysDept parent;

    // collections
    private java.util.Set<com.gbcom.system.domain.SysPersonDept> sysPersonDepts;


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
     * Return the value associated with the column: SHORT_NAME
     */
    public java.lang.String getShortName() {
        return shortName;
    }

    /**
     * Set the value related to the column: SHORT_NAME
     *
     * @param shortName the SHORT_NAME value
     */
    public void setShortName(java.lang.String shortName) {
        this.shortName = shortName;
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
     * Return the value associated with the column: IS_COMPANY
     */
    public java.lang.Boolean getIsCompany() {
        return isCompany;
    }

    /**
     * Set the value related to the column: IS_COMPANY
     *
     * @param isCompany the IS_COMPANY value
     */
    public void setIsCompany(java.lang.Boolean isCompany) {
        this.isCompany = isCompany;
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
     * Return the value associated with the column: IS_LEAF
     */
    public java.lang.Boolean getIsLeaf() {
        return isLeaf;
    }

    /**
     * Set the value related to the column: IS_LEAF
     *
     * @param isLeaf the IS_LEAF value
     */
    public void setIsLeaf(java.lang.Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }


    /**
     * Return the value associated with the column: TREE_ID
     */
    public java.lang.String getTreeId() {
        return treeId;
    }

    /**
     * Set the value related to the column: TREE_ID
     *
     * @param treeId the TREE_ID value
     */
    public void setTreeId(java.lang.String treeId) {
        this.treeId = treeId;
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
     * Return the value associated with the column: MEMO
     */
    public java.lang.String getMemo() {
        return memo;
    }

    /**
     * Set the value related to the column: MEMO
     *
     * @param memo the MEMO value
     */
    public void setMemo(java.lang.String memo) {
        this.memo = memo;
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
     * Return the value associated with the column: PARENT_ID
     */

    public com.gbcom.system.domain.SysDept getParent() {
        return parent;
    }

    /**
     * Set the value related to the column: PARENT_ID
     *
     * @param parent the PARENT_ID value
     */
    public void setParent(com.gbcom.system.domain.SysDept parent) {
        this.parent = parent;
    }

    /**
     * Return the value associated with the column: sysPersonDepts
     */

    public java.util.Set<com.gbcom.system.domain.SysPersonDept> getSysPersonDepts() {
        if (sysPersonDepts == null) {
            sysPersonDepts = new java.util.LinkedHashSet<com.gbcom.system.domain.SysPersonDept>();
        }
        return sysPersonDepts;
    }

    /**
     * Set the value related to the column: sysPersonDepts
     *
     * @param sysPersonDepts the sysPersonDepts value
     */
    public void setSysPersonDepts(java.util.Set<com.gbcom.system.domain.SysPersonDept> sysPersonDepts) {
        this.sysPersonDepts = sysPersonDepts;
    }

    public void addTosysPersonDepts(com.gbcom.system.domain.SysPersonDept sysPersonDept) {
        if (null == getSysPersonDepts())
            setSysPersonDepts(new java.util.LinkedHashSet<com.gbcom.system.domain.SysPersonDept>());
        getSysPersonDepts().add(sysPersonDept);
    }


    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (!(obj instanceof com.gbcom.system.domain.SysDept)) return false;
        else {
            com.gbcom.system.domain.SysDept sysDept = (com.gbcom.system.domain.SysDept) obj;
            if (null == this.getId() || null == sysDept.getId()) return false;
            else return (this.getId().equals(sysDept.getId()));
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
        builder.append(name);
        builder.append(shortName);
        builder.append(organizationCode);
        builder.append(address);
        builder.append(contacter);
        builder.append(telephone);
        builder.append(isCompany);
        builder.append(isValid);
        builder.append(isLeaf);
        builder.append(treeId);
        builder.append(orderNo);
        builder.append(memo);
        builder.append(createTime);
        builder.append(updateTime);
        builder.append(createUser);
        builder.append(updateUser);
        return builder.toString();
    }


}