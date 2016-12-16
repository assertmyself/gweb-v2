<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    $(function () {
        var conf = {
            gridOpts: {
                url: "${ctx}/sysUserPrivilege/gridDataCustom.do",
                colNames: ['ID',
                    '是否禁止授权',
                    '操作'
                ],
                colModel: [
                    {name: 'id', width: 10, align: "center", searchtype: "integer", hidden: true},
                    {name: "isDeny", width: "800", align: "center", searchtype: "integer", sortable: true, formatter: booleanFormat}
                ],
                actModel: [
                    {name: 'operation', width: 80, align: 'center',title:false}
                ],
                pager: '#pager',
                caption: "用户与权限关联列表",
                shrinkToFit: true,
                gridComplete: function () {  //在此事件中循环为每一行添加修改和删除链接
                    var ids = jQuery("#listGrid").jqGrid('getDataIDs');
                    for (var i = 0; i < ids.length; i++) {
                        var id = ids[i];
                        var opButton = '<input type="button" value="查看" onclick="doView(' + id + ')" class="button_normal"> ';
                        opButton += '<input type="button" value="编辑" onclick="doEdit(' + id + ')" class="button_normal"> ';
                        opButton += '<input type="button" value="删除" onclick="doDelete(' + id + ')" class="button_normal">';
                        jQuery("#listGrid").jqGrid('setRowData', ids[i], { operation: opButton});
                    }
                }
            },
            userOpts: {
                defaultQuery: { "groupOp": "AND", "rules": [
                    { "field": "是否禁止授权", "op": "cn", "data": ""}
                ]},
                queryButton: $("#queryButton"),
                queryDesc: $("#queryConditionDesc")
            }
        };
        gridinit($("#listGrid"), conf);
    });
    function doAdd() {
        openWindow("添加用户与权限关联", "${ctx}/sysUserPrivilege/add.do", true);
    }
    function doEdit(id) {
        openWindow("修改用户与权限关联", "${ctx}/sysUserPrivilege/modify.do?id=" + id, true);
    }
    function doView(id) {
        openWindow("查看用户与权限关联", "${ctx}/sysUserPrivilege/view.do?id=" + id, false);
    }
    function doDelete(id) {
        doGridDelete("${ctx}/sysUserPrivilege/delete.do?id=" + id);
    }
</script>

<div class="title_Search">
    <div class="gridQueryArea">
        <div style="float:left;padding-left: 5px">
            <input type="button" name="queryButton" id="queryButton" value="查询" class="btn_Search"/>
        </div>
        <div style="float:left;padding-left: 10px" id="conditionsDesc">
            <input type="text" name="queryConditionDesc" id="queryConditionDesc" value="" class="title_input"
                   readonly="true"/>
        </div>
        <div style="float:right;padding-right: 10px">
            <input type="button" value="添加" class="button_add"
                   onclick="doAdd()"/>
        </div>
    </div>
</div>
<div class="gridQueryTable">
    <table id="listGrid"></table>
    <div id="pager"></div>
</div>