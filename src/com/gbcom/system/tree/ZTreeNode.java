//off checkstyle
package com.gbcom.system.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * ZTree 节点
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-9-11,下午03:25:00
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.system.tree.ZTreeNode
 */
public class ZTreeNode extends Node {
    private String id;
    private String name;
    private String text;
    private Boolean check;
    private Boolean open;
    private Boolean isParent;
    private Boolean isLeaf;
    private String uid;
    private String type;
    private String icon;
    private Boolean extendLeaf;

	private List<ZTreeNode> children = new ArrayList<ZTreeNode>();

    public List<ZTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<ZTreeNode> children) {
		this.children = children;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean parent) {
        isParent = parent;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

	/**
	 * @return the extendLeaf
	 */
	public Boolean getExtendLeaf() {
		return extendLeaf;
	}

	/**
	 * @param extendLeaf the extendLeaf to set
	 */
	public void setExtendLeaf(Boolean extendLeaf) {
		this.extendLeaf = extendLeaf;
	}
    
}
