//off checkstyle
package com.gbcom.system.domain.base;

import com.gbcom.system.domain.SysUserPrivilege;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SYS_PRIVILEGE table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 系统权限管理
 * SyncTemplatepatterns : tree\w*
 * SyncDao : false
 * TableName : 系统权限管理
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : true
 * SubSystem : system
 * Projectable : false
 *
 * @hibernate.class table="SYS_PRIVILEGE"
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public abstract class BaseSysPrivilege implements Serializable, com.hc.core.entity.Treeable {

    public static String REF = "SysPrivilege";
    public static String PROP_NAME = "name";
    public static String PROP_PARENT = "parent";
    public static String PROP_DESCRIPTION = "description";
    public static String PROP_URL = "url";
    public static String PROP_IS_LEAF = "isLeaf";
    public static String PROP_TYPE = "type";
    public static String PROP_TAG = "tag";
    public static String PROP_ID = "id";
    public static String PROP_TREE_ID = "treeId";
    public static String PROP_CODE = "code";
    public static String PROP_DEFINITION = "definition";


    // constructors
    public BaseSysPrivilege() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseSysPrivilege(java.lang.Long id) {
        this.setId(id);
        initialize();
    }

    /**
     * Constructor for required fields
     */
    public BaseSysPrivilege(
            java.lang.Long id,
            java.lang.String code) {

        this.setId(id);
        this.setCode(code);
        initialize();
    }

    protected void initialize() {
    }


    private int hashCode = Integer.MIN_VALUE;

    // primary key
    private java.lang.Long id;

    // fields
    /*权限编码*/
    /*权限编码*/
    private java.lang.String code;

    /*权限名称*/
    /*权限名称*/
    private java.lang.String name;

    /*TAG*/
    /*TAG*/
    private java.lang.Long tag;

    /*页面地址*/
    /*页面地址*/
    private java.lang.String url;

    /*定义*/
    /*定义*/
    private java.lang.String definition;

    /*描述*/
    /*描述*/
    private java.lang.String description;

    /*是否叶节点*/
    /*是否叶节点*/
    private java.lang.Boolean isLeaf;

    /*树形层次*/
    /*树形层次*/
    private java.lang.String treeId;


    // many to one
    private com.gbcom.system.domain.SysPrivilege parent;
    private com.gbcom.system.domain.SysCodeDetail type;

    // collections
    private java.util.Set<com.gbcom.system.domain.SysRolePrivilege> sysRolePrivileges;
    private java.util.Set<com.gbcom.system.domain.SysPrivilege> sysPrivileges;
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
     * Return the value associated with the column: TAG
     */
    public java.lang.Long getTag() {
        return tag;
    }

    /**
     * Set the value related to the column: TAG
     *
     * @param tag the TAG value
     */
    public void setTag(java.lang.Long tag) {
        this.tag = tag;
    }


    /**
     * Return the value associated with the column: URL
     */
    public java.lang.String getUrl() {
        return url;
    }

    /**
     * Set the value related to the column: URL
     *
     * @param url the URL value
     */
    public void setUrl(java.lang.String url) {
        this.url = url;
    }


    /**
     * Return the value associated with the column: DEFINITION
     */
    public java.lang.String getDefinition() {
        return definition;
    }

    /**
     * Set the value related to the column: DEFINITION
     *
     * @param definition the DEFINITION value
     */
    public void setDefinition(java.lang.String definition) {
        this.definition = definition;
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
     * Return the value associated with the column: PARENT_ID
     */
    public com.gbcom.system.domain.SysPrivilege getParent() {
        return parent;
    }

    /**
     * Set the value related to the column: PARENT_ID
     *
     * @param parent the PARENT_ID value
     */
    public void setParent(com.gbcom.system.domain.SysPrivilege parent) {
        this.parent = parent;
    }


    /**
     * Return the value associated with the column: TYPE
     */
    public com.gbcom.system.domain.SysCodeDetail getType() {
        return type;
    }

    /**
     * Set the value related to the column: TYPE
     *
     * @param type the TYPE value
     */
    public void setType(com.gbcom.system.domain.SysCodeDetail type) {
        this.type = type;
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
     * Return the value associated with the column: sysPrivileges
     */
    public java.util.Set<com.gbcom.system.domain.SysPrivilege> getSysPrivileges() {
        if (sysPrivileges == null) {
            sysPrivileges = new java.util.LinkedHashSet<com.gbcom.system.domain.SysPrivilege>();
        }
        return sysPrivileges;
    }

    /**
     * Set the value related to the column: sysPrivileges
     *
     * @param sysPrivileges the sysPrivileges value
     */
    public void setSysPrivileges(java.util.Set<com.gbcom.system.domain.SysPrivilege> sysPrivileges) {
        this.sysPrivileges = sysPrivileges;
    }

    public void addTosysPrivileges(com.gbcom.system.domain.SysPrivilege sysPrivilege) {
        if (null == getSysPrivileges())
            setSysPrivileges(new java.util.LinkedHashSet<com.gbcom.system.domain.SysPrivilege>());
        getSysPrivileges().add(sysPrivilege);
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
        if (!(obj instanceof com.gbcom.system.domain.SysPrivilege)) return false;
        else {
            com.gbcom.system.domain.SysPrivilege sysPrivilege = (com.gbcom.system.domain.SysPrivilege) obj;
            if (null == this.getId() || null == sysPrivilege.getId()) return false;
            else return (this.getId().equals(sysPrivilege.getId()));
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
        builder.append(tag);
        builder.append(url);
        builder.append(definition);
        builder.append(description);
        builder.append(isLeaf);
        builder.append(treeId);
        return builder.toString();
    }


}