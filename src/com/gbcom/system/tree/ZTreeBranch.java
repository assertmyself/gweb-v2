package com.gbcom.system.tree;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * ZTree树结构
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-9-11,下午03:20:03
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.system.tree.ZTreeBranch
 */
public class ZTreeBranch {
    List<Node> treeNodeList;
    String[] icons = {"home.png","fd-ye.png","code_detail.gif"};//fd-ye.png,code_detail.gif
//    String[] icons = {"home.png","fd.png","file.png"};

    /***
     * 获取树节点列表
     * 非空处理
     * @return List<Node>
     */
    public List<Node> getTreeNodeList() {
        if (treeNodeList == null) {
            treeNodeList = new ArrayList<Node>();
        }
        return treeNodeList;
    }

    /**
     * set 树节点 
     * @param treeNodeList List<Node>
     */
    public void setTreeNodeList(List<Node> treeNodeList) {
        this.treeNodeList = treeNodeList;
    }

    /**
     * 新增一个节点
     * @param treeNode Node
     */
    public void addTreeNode(Node treeNode) {
        getTreeNodeList().add(treeNode);
    }

    /**
     * 获取图表
     * @return String[]
     */
    public String[] getIcons() {
        return icons;
    }

    /**
     * 修改图表
     * @param icons  String[]
     */
    public void setIcons(String[] icons) {
        this.icons = icons;
    }

    /**
     * 转化成json
     * @param hasCheckBox 是否要添加checkbox 多选框
     * @return String
     */
    public String toJsonString(boolean hasCheckBox) {
        List<Node> list = getTreeNodeList();
        for (Node node : list) {
        	parseNode(node);
        }
        JsonConfig jsonConfig = new JsonConfig();
        if (!hasCheckBox) {
            jsonConfig.setExcludes(new String[]{"checked"});
        }
        JSONArray tranfer = JSONArray.fromObject(list, jsonConfig);
        return tranfer.toString();
    }

    /**
     * 对节点的处理。node 属性的处理 
     * @param node 原来NODE
     * @return 新的node
     */
    public Node processNode(Node node) {
        ZTreeNode treeNode = (ZTreeNode) node;
        if (treeNode.getIcon() != null) {
            treeNode.setIcon(getIconPath(getIcons(), Integer.parseInt(treeNode.getIcon())));
        } else {
            treeNode.setIcon(getIconPath(getIcons(), 0));
        }
        //建设controller设置代码，如业务复杂可以开放uid应用
        String uid = treeNode.getType() + "|<id>" + treeNode.getId() + "</id>";
        if (treeNode.getUid() == null) {
            treeNode.setUid(uid);
        }
        if(treeNode.getText() != null && !"".equals(treeNode.getText())){
        	treeNode.setText(treeNode.getText());
        }else{
        	treeNode.setText("");
        }
        treeNode.setOpen(false);
        treeNode.setIsParent(!treeNode.getIsLeaf());
        return treeNode;
    }

    /**
     * 获取根节点
     * @param name String
     * @return  ZTreeNode
     */
    public ZTreeNode getRootNode(String name) {
        ZTreeNode treeNode = new ZTreeNode();
        treeNode.setId("root");
        treeNode.setIsParent(true);
        treeNode.setIsLeaf(false);
        treeNode.setOpen(true);
//            treeNode.setHasChildren(true);
        treeNode.setName(name);
        treeNode.setText(name);
        treeNode.setUid("root");
        treeNode.setIcon("0");
        treeNode.setType("root");
        return treeNode;
    }

    /**
     * 获取图片路径
     *
     * @param icons String[]
     * @param no int
     * @return String
     */
    public String getIconPath(String[] icons, int no) {
        String iconPath = "default.gif";
        if (icons != null) {
            if (icons.length > no) {
                iconPath = icons[no];
            }
        }
        return "../html/img/icon/" + iconPath;
    }
    
    private void parseNode(Node node){
    	processNode(node);
    	 if(((ZTreeNode)node).getChildren().size()>0){
    		 for(Node node1 : ((ZTreeNode)node).getChildren()){
    			 parseNode(node1);
    		 }
    	 }
    }
}
