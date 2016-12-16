<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    $(function () {
        var conf = {
            gridOpts: {
                url: "${ctx}/sysDept/gridDataCustom.do",
                colNames: ['ID','单位代码','单位名称','机构代码证','联系人','联系电话', '注册地址','单位/部门', '是否有效', '操作'
                ],
                colModel: [
                    {name: 'id', width: 10, searchtype: "integer", hidden: true,key:true},
                    {name: "code", width: "42", searchtype: "string", sortable: true},
                    {name: "name", width: "42", searchtype: "string", sortable: true},
                    {name: "organizationCode", width: "42", searchtype: "string", sortable: true},
                    {name: "contacter", width: "42", searchtype: "string", sortable: true},
                    {name: "telephone", width: "42", searchtype: "string", sortable: true},
                    {name: "address", width: "42", searchtype: "string", sortable: true},
                    {name: "isCompany", width: "20", searchtype: "integer", sortable: true, formatter: booleanFormat},
                    {name: "isValid", width: "20", searchtype: "integer", sortable: true, formatter: booleanFormat}
                ],
                actModel: [
                    {name: 'operation', width: 20, title:false,sortable: false}
                ],
                pager: '#pager',
                caption: false,
                shrinkToFit: true,
                gridComplete: function () { 
                    var ids = jQuery("#listGrid").jqGrid('getDataIDs');
                    for (var i = 0; i < ids.length; i++) {
                        var id = ids[i];
                        var rowData = jQuery("#listGrid").jqGrid('getRowData', id);
                        var opButton = '<a href="javascript:void(0);" onclick="doView(' + id + ', \'' + rowData.isCompany + '\')" class="btn btn-sm btn-info"> 查看 </a>';
                        jQuery("#listGrid").jqGrid('setRowData', ids[i], { operation: opButton});
                    }
                }
            },
            userOpts: {
                defaultQuery: { "groupOp": "AND", "rules": [
                    { "field": "单位代码", "op": "cn", "data": ""},
                    { "field": "单位名称", "op": "cn", "data": ""}
                ]},
                queryButton: $("#queryButton"),
                queryDesc: $("#queryConditionDesc"),
                buildQuery: $("#queryParams")
            },
            isExportExcel: true
        };
        gridinit($("#listGrid"), conf);
    });
    function doAdd() {
        openWindow("添加单位部门", "${ctx}/sysDept/add.do", true);
    }
    function doEdit(id) {
        openWindow("修改单位部门", "${ctx}/sysDept/modify.do?id=" + id, true);
    }
    function doView(id, type) {
        if (type == "是") {
            openWindow("查看单位", "${ctx}/sysDept/viewDw.do?id=" + id, false);
        } else if (type == "否") {
            openWindow("查看部门", "${ctx}/sysDept/viewDept.do?id=" + id, false);
        }
    }
    function doDelete(id) {
        doGridDelete("${ctx}/sysDept/delete.do?id=" + id);
    }

    $("#queryBtn").click(function () {
        //构建查询条件
        var values = {};
        $(".title_Search input").each(function(i){
            if($(this).attr("id")=='queryParams')return;
            values[$(this).attr("name")]=$(this).val();
        })
        //存入缓存
        $("#queryParams").val(JSON.stringify(values));
        $("#listGrid").trigger("reloadGrid");
    });

</script>

<form class="form-inline title_Search">
	<div class="form-group">
		<input type="hidden" id="queryParams" name="queryParms" value="" /> <label
			class="control-label">单位代码:&nbsp; </label>
		<div class="form-group">
			<input class="form-control" type="text" name="code" id="code">&nbsp;&nbsp;&nbsp;
		</div>
		<label class="control-label">单位名称:&nbsp; </label>
		<div class="form-group">
			<input class="form-control" type="text" name="name" id="name">&nbsp;&nbsp;&nbsp;
		</div>
		<button class="btn btn-sm btn-primary" id="queryBtn" type="button">
			<i class="glyphicon glyphicon-search"></i>&nbsp; <strong>查询</strong>
		</button>
	</div>
</form>
<div class="jqGrid_wrapper">
	<table id="listGrid"></table>
	<div id="pager"></div>
</div>