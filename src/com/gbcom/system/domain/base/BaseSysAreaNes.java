//off checkstyle
package com.gbcom.system.domain.base;

import java.io.Serializable;


/**
 * 管理区域网元设备。
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-5-25,下午03:35:25
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.system.domain.base.BaseSysAreaNes
 */
public abstract class BaseSysAreaNes implements Serializable {



    // constructors
    public BaseSysAreaNes() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseSysAreaNes(java.lang.Long id) {
        this.setId(id);
        initialize();
    }


    protected void initialize() {
    }


    private static int hashCode = Integer.MIN_VALUE;

    // primary key
    private java.lang.Long id;

    /**
     * 网元
     */
    private java.lang.String neName;

    /**
     * 网元ID
     */
    private java.lang.String neID;
    
    /**
     * 保留字段，，另一种设计。
     */
    private java.lang.String fkCol;
    private java.lang.String fkValue;
    private java.lang.String fkTable;

    // many to one
    private com.gbcom.system.domain.SysArea area;
    
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
        builder.append(neName);
        builder.append(neID);
        builder.append(fkCol);
        builder.append(fkValue);
        return builder.toString();
    }

	public java.lang.String getNeName() {
		return neName;
	}

	public void setNeName(java.lang.String neName) {
		this.neName = neName;
	}

	public java.lang.String getNeID() {
		return neID;
	}

	public void setNeID(java.lang.String neID) {
		this.neID = neID;
	}

	public java.lang.String getFkCol() {
		return fkCol;
	}

	public void setFkCol(java.lang.String fkCol) {
		this.fkCol = fkCol;
	}

	public java.lang.String getFkValue() {
		return fkValue;
	}

	public void setFkValue(java.lang.String fkValue) {
		this.fkValue = fkValue;
	}

	public java.lang.String getFkTable() {
		return fkTable;
	}

	public void setFkTable(java.lang.String fkTable) {
		this.fkTable = fkTable;
	}

	public com.gbcom.system.domain.SysArea getArea() {
		return area;
	}

	public void setArea(com.gbcom.system.domain.SysArea area) {
		this.area = area;
	}




}