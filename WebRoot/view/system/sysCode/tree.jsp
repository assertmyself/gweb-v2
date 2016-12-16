<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<SCRIPT type="text/javascript">
    var canEdit = ${canEdit};               //编辑权限

    var icon = addIcons("fd-ye.png,code_detail.gif");  //节点图标
    var clazz = '${clazz}';    //供上移下移使用
    var clazzDetail = '${clazzDetail}';

    $(document).ready(function () {
        //不覆盖zTree的配置，如果需要额外配置，可直接在setting里面添加
        var setting = {
            simpleOpts: {
                treeUrl: CONTEXT_NAME + "/sysCode/treeData.do?icon=" + icon, //树的链接
                isOpenRoot: true, //是否打开根节点
                clickOpts: {
                    "root": CONTEXT_NAME + "/sysCodeDetail/grid.do",
                    "data": CONTEXT_NAME + "/sysCodeDetail/grid.do?id={id}",
                    "detail": ["查看代码", CONTEXT_NAME + "/sysCodeDetail/view.do?id={id}"]
                },
                rightMenuOpts: function (treeNode, zTree) {
                    var menu = [];
                    if (canEdit) {
                        if (treeNode.id == 'root') {
                            menu.push(["添加代码集", ["添加代码集", CONTEXT_NAME + "/sysCode/add.do?parentId={id}"]]);
                        }

                        else if (treeNode.type == 'data') {
                            menu.push(["添加代码项", ["添加代码项", CONTEXT_NAME + "/sysCodeDetail/add.do?parentId={id}"]]);
                            menu.push([]);
                            menu.push(["修改代码集", ["修改代码集", CONTEXT_NAME + "/sysCode/modify.do?id={id}"]]);
                            menu.push([]);
                            menu.push(["删除代码集", CONTEXT_NAME + "/sysCode/delete.do?id={id}", true]);
                            menu.push([]);
                            menu.push(["查看代码集", ["查看代码集", CONTEXT_NAME + "/sysCode/view.do?id={id}"]]);
                            menu.push([]);
                            menu.push(["上移", "moveUp"]);
                            menu.push(["下移", "moveDown"]);
                        }
                        else {
                            menu.push(["修改代码项", ["修改代码项", CONTEXT_NAME + "/sysCodeDetail/modify.do?id={id}"]]);
                            menu.push([]);
                            menu.push(["删除代码项", CONTEXT_NAME + "/sysCodeDetail/delete.do?id={id}", true]);
                            menu.push([]);
                            menu.push(["上移", function (treeNode, zTree) {
                                treeActions.moveUp(treeNode, zTree, clazzDetail);
                            }]);
                            menu.push(["下移", function (treeNode, zTree) {
                                treeActions.moveDown(treeNode, zTree, clazzDetail);
                            }]);
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