//off checkstyle
package com.gbcom.system.domain.base;

import java.io.Serializable;
import java.util.Set;

import com.gbcom.system.domain.SysAreaNes;


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

public abstract class BaseSysArea implements Serializable, com.hc.core.entity.Treeable {

    public static String REF = "SysArea";
    public static String PROP_AREA_NAME= "areaName";
    public static String PROP_PID = "pid";
    public static String PROP_ZIPCODE = "areaCode";
    public static String PROP_LAYER = "layer";
    public static String PROP_DISPLAY_NAME = "displayName";
    public static String PROP_ID = "id";


    // constructors
    public BaseSysArea() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseSysArea(java.lang.Long id) {
        this.setId(id);
        initialize();
    }


    protected void initialize() {
    }


    private static int hashCode = Integer.MIN_VALUE;

    // primary key
    private java.lang.Long id;
    // primary key
//    private java.lang.Long pid;
    // primary key
    private java.lang.Long layer;

    // fields
    /*登陆名*/
    /*登陆名*/
    private java.lang.String areaName;

    /*密码*/
    /*密码*/
    private java.lang.String areaCode;

    /*显示名称*/
    /*显示名称*/
    private java.lang.String displayName;

    
    /*系统定义*/
    /*系统定义*/
    private java.lang.Boolean isReserved;
    
    /*叶节点*/
    /*叶节点*/
    private java.lang.Boolean isLeaf;

    /*树形层次*/
    /*树形层次*/
    private java.lang.String treeId;

    /*备注*/
    /*备注*/
    private java.lang.String description;
    

    // many to one
    private com.gbcom.system.domain.SysArea parent;
    
    
    private Set<SysAreaNes> nes;
    //not recomanded ,the class is sys , normally cannot depend on business
//    private Set<SysAreaConfigTpl> sysTpls;
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
        //builder.append(parent.getId());
        builder.append(layer);
        builder.append(displayName);
        builder.append(areaName);
        builder.append(areaCode);
        return builder.toString();
    }



	public java.lang.Long getLayer() {
		return layer;
	}

	public void setLayer(java.lang.Long layer) {
		this.layer = layer;
	}

	public java.lang.String getAreaName() {
		return areaName;
	}

	public void setAreaName(java.lang.String areaName) {
		this.areaName = areaName;
	}

	public java.lang.String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(java.lang.String zipcode) {
		this.areaCode = zipcode;
	}

	public java.lang.String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(java.lang.String displayName) {
		this.displayName = displayName;
	}

	public java.lang.Boolean getIsReserved() {
		return isReserved;
	}

	public void setIsReserved(java.lang.Boolean isReserved) {
		this.isReserved = isReserved;
	}

	public java.lang.Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(java.lang.Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public java.lang.String getTreeId() {
		return treeId;
	}

	public void setTreeId(java.lang.String treeId) {
		this.treeId = treeId;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public com.gbcom.system.domain.SysArea getParent() {
		return parent;
	}

	public void setParent(com.gbcom.system.domain.SysArea parent) {
		this.parent = parent;
	}

	public void setNes(Set<SysAreaNes> nes) {
		this.nes = nes;
	}

	public Set<SysAreaNes> getNes() {
		return nes;
	}

}