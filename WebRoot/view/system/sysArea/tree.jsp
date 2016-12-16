<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<SCRIPT type="text/javascript">
    var canEdit = ${canEdit};               //编辑权限

    //var icon = addIcons("code.gif,code_detail.gif");  //节点图标
    var clazz = '${clazz}';    //供上移下移使用
    var clazzDetail = '${clazzDetail}';
	
    $(document).ready(function () {
        //不覆盖zTree的配置，如果需要额外配置，可直接在setting里面添加
        var setting = {
            simpleOpts: {
                treeUrl: CONTEXT_NAME + "/sysArea/treeData.do", //树的链接
                isOpenRoot: true, //是否打开根节点
                clickOpts: {
                    //"root": CONTEXT_NAME + "/sysArea/grid.do",
                    //"data": CONTEXT_NAME + "/sysArea/grid.do?id={id}"
                    
                    //"detail": ["查看代码", CONTEXT_NAME + "/sysCodeDetail/view.do?id={id}"]
                },
                rightMenuOpts: function (treeNode, zTree) {
                    var menu = [];
                    if (canEdit) {
                        if (treeNode.id == 'root') {
                            menu.push(["添加区域", ["添加区域", CONTEXT_NAME + "/sysArea/add.do?parentId={id}"]]);
                        }else if(treeNode.isLeaf == true){
                        	menu.push(["修改区域", ["修改区域", CONTEXT_NAME + "/sysArea/modify.do?id={id}"]]);
                            menu.push([]);
                            menu.push(["删除区域", CONTEXT_NAME + "/sysArea/delete.do?id={id}", true]);
                            menu.push([]);
                            menu.push(["查看区域", ["查看区域", CONTEXT_NAME + "/sysArea/view.do?id={id}"]]);
                            menu.push([]);
                            menu.push(["上移", "moveUp"]);
                            menu.push(["下移", "moveDown"]);
                        }
                        else {
                            menu.push(["添加区域", ["添加区域", CONTEXT_NAME + "/sysArea/add.do?parentId={id}"]]);
                            menu.push([]);
                            menu.push(["修改区域", ["修改区域", CONTEXT_NAME + "/sysArea/modify.do?id={id}"]]);
                            menu.push([]);
                            menu.push(["删除区域", CONTEXT_NAME + "/sysArea/delete.do?id={id}", true]);
                            menu.push([]);
                            menu.push(["查看区域", ["查看区域", CONTEXT_NAME + "/sysArea/view.do?id={id}"]]);
                            menu.push([]);
                            menu.push(["上移", "moveUp"]);
                            menu.push(["下移", "moveDown"]);
                        }
                        menu.push([]);
                    }
                    menu.push(["刷新", "refreshNode"]);

                    return menu;
                }
            }
        };
        initTree($("#treeId"), setting);
        console.log("#treeId");
    })
</SCRIPT>
<ul id="treeId" class="ztree"></ul>