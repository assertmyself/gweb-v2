//off checkstyle
package com.gbcom.system.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SYS_CODE_DETAIL table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 系统代码明细
 * SyncTemplatepatterns : tree\w*
 * SyncDao : false
 * TableName : 系统代码明细
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : true
 * SubSystem : system
 * Projectable : false
 *
 * @hibernate.class table="SYS_CODE_DETAIL"
 */

public abstract class BaseSysCodeDetail implements Serializable, com.hc.core.entity.Treeable {

    public static String REF = "SysCodeDetail";
    public static String PROP_NAME = "name";
    public static String PROP_PARENT = "parent";
    public static String PROP_SYS_CODE = "sysCode";
    public static String PROP_DESCRIPTION = "description";
    public static String PROP_IS_LEAF = "isLeaf";
    public static String PROP_IS_VALID = "isValid";
    public static String PROP_TAG = "tag";
    public static String PROP_ID = "id";
    public static String PROP_IS_RESERVED = "isReserved";
    public static String PROP_TREE_ID = "treeId";
    public static String PROP_CODE = "code";


    // constructors
    public BaseSysCodeDetail() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseSysCodeDetail(java.lang.Long id) {
        this.setId(id);
        initialize();
    }

    protected void initialize() {
    }


    private int hashCode = Integer.MIN_VALUE;

    // primary key
    private java.lang.Long id;

    // fields
    /*编码*/
    /*编码*/
    private java.lang.String code;

    /*名称*/
    /*名称*/
    private java.lang.String name;

    /*叶节点*/
    /*叶节点*/
    private java.lang.Boolean isLeaf;

    /*树形层次*/
    /*树形层次*/
    private java.lang.String treeId;

    /*是否系统定义*/
    /*是否系统定义*/
    private java.lang.Boolean isReserved;

    /*特殊标记*/
    /*特殊标记*/
    private java.lang.String tag;

    /*是否有效*/
    /*是否有效*/
    private java.lang.Boolean isValid;

    /*备注*/
    /*备注*/
    private java.lang.String description;


    // many to one
    private com.gbcom.system.domain.SysCodeDetail parent;
    private com.gbcom.system.domain.SysCode sysCode;


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
     * Return the value associated with the column: IS_RESERVED
     */
    public java.lang.Boolean getIsReserved() {
        return isReserved;
    }

    /**
     * Set the value related to the column: IS_RESERVED
     *
     * @param isReserved the IS_RESERVED value
     */
    public void setIsReserved(java.lang.Boolean isReserved) {
        this.isReserved = isReserved;
    }


    /**
     * Return the value associated with the column: TAG
     */
    public java.lang.String getTag() {
        return tag;
    }

    /**
     * Set the value related to the column: TAG
     *
     * @param tag the TAG value
     */
    public void setTag(java.lang.String tag) {
        this.tag = tag;
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
     * Return the value associated with the column: PARENT_ID
     */

    public com.gbcom.system.domain.SysCodeDetail getParent() {
        return parent;
    }

    /**
     * Set the value related to the column: PARENT_ID
     *
     * @param parent the PARENT_ID value
     */
    public void setParent(com.gbcom.system.domain.SysCodeDetail parent) {
        this.parent = parent;
    }


    /**
     * Return the value associated with the column: SYS_CODE_ID
     */

    public com.gbcom.system.domain.SysCode getSysCode() {
        return sysCode;
    }

    /**
     * Set the value related to the column: SYS_CODE_ID
     *
     * @param sysCode the SYS_CODE_ID value
     */
    public void setSysCode(com.gbcom.system.domain.SysCode sysCode) {
        this.sysCode = sysCode;
    }


    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (!(obj instanceof com.gbcom.system.domain.SysCodeDetail)) return false;
        else {
            com.gbcom.system.domain.SysCodeDetail sysCodeDetail = (com.gbcom.system.domain.SysCodeDetail) obj;
            if (null == this.getId() || null == sysCodeDetail.getId()) return false;
            else return (this.getId().equals(sysCodeDetail.getId()));
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
        builder.append(isLeaf);
        builder.append(treeId);
        builder.append(isReserved);
        builder.append(tag);
        builder.append(isValid);
        builder.append(description);
        return builder.toString();
    }


}