<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<SCRIPT type="text/javascript">
    var canEdit = ${canEdit};               //编辑权限
    var icon = addIcons("company.gif,dept.gif");
    var clazz = '${clazz}';    //供上移下移使用
    $(document).ready(function () {
        //不覆盖zTree的配置，如果需要额外配置，可直接在setting里面添加
        var setting = {
            simpleOpts: {
                treeUrl: CONTEXT_NAME + "/sysDept/treeData.do?icon=" + icon, //树的链接
                isOpenRoot: true, //是否打开根节点
                clickOpts: {
                    "root": CONTEXT_NAME + "/sysDept/grid.do",
                    "unit": ["查看单位", CONTEXT_NAME + "/sysDept/viewDw.do?id={id}"],
                    "dept": ["查看部门", CONTEXT_NAME + "/sysDept/viewDept.do?id={id}"]
                },
                rightMenuOpts: function (treeNode, zTree) {
                    var menu = [];
                    if (canEdit) {
                        if (treeNode.type == 'root') {
                            menu.push(["添加单位", ["添加单位", CONTEXT_NAME + "/sysDept/addDw.do?parentId={id}"]]);
                        } else if (treeNode.type == 'unit') {
                            menu.push(["添加部门", ["添加部门", CONTEXT_NAME + "/sysDept/addDept.do?parentId={id}"]]);
                            menu.push(["修改单位", ["修改单位", CONTEXT_NAME + "/sysDept/modifyDw.do?id={id}"]]);
                        } else if (treeNode.type == "dept") {
                            menu.push(["添加部门", ["添加部门", CONTEXT_NAME + "/sysDept/addDept.do?parentId={id}"]]);
                            menu.push(["修改部门", ["修改部门", CONTEXT_NAME + "/sysDept/modifyDept.do?id={id}"]]);
                        }
                        if (treeNode.id != 'root') {
                            menu.push([]);
                            menu.push(["删除", CONTEXT_NAME + "/sysDept/delete.do?id={id}", true]);
                            menu.push([]);
                            menu.push(["上移", "moveUp"]);
                            menu.push(["下移", "moveDown"]);
//                            menu.push([]);
//                        menu.push(["刷新父节点", "refreshParentNode"]);
//                        menu.push(["自定义操作", function (treeNode, zTree) {
//                            alert(treeNode);
//                        }, true]);
                        }
                        menu.push([]);
                    }
                    menu.push(["刷新", "refreshNode"]);
                    return menu;
                }
            }
        };
        initTree($("#treeId"), setting);
    })
</SCRIPT>
<ul id="treeId" class="ztree"></ul>