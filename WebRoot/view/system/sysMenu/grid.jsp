<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    $(function () {
        var conf = {
            gridOpts: {
                url: "${ctx}/sysMenu/gridDataCustom.do",
                colNames: ['ID','名称','编码','层次','链接地址','是否有效','显示图标','操作'
                ],
                colModel: [
                    {name: 'id', width: 10 , searchtype: "integer", hidden: true,key:true},
                    {name: "name", width: "70" , searchtype: "string", sortable: true},
                    {name: "privilege", width: "70" , searchtype: "string", sortable: true},
                    {name: "menuLevel", width: "30" , searchtype: "integer", sortable: true, formatter: 'integer', formatoptions: {thousandsSeparator: ",", defaulValue: 0}},
                    {name: "url", width: "100",   searchtype: "string", sortable: true},
                    {name: "isValid", width: "30",  searchtype: "integer", sortable: true, formatter: booleanFormat},
                    {name: "icon", width: "30",  searchtype: "string", sortable: true, formatter: iconFormat}
                ],
                actModel: [
                    {name: 'operation', width: 40, title:false, sortable: false}
                ],
                pager: '#pager',
                caption: false,
                shrinkToFit: true,
                gridComplete: function () {  //在此事件中循环为每一行添加修改和删除链接
                    var ids = jQuery("#listGrid").jqGrid('getDataIDs');
                    for (var i = 0; i < ids.length; i++) {
                        var id = ids[i];
                        var opButton = '<a href="javascript:void(0);" onclick="doView(' + id +')" title="查看"class="btn btn-sm btn-info">查看</a>';
                        jQuery("#listGrid").jqGrid('setRowData', ids[i], { operation: opButton});
                    }
                }
            },
            userOpts: {
                defaultQuery: { "groupOp": "AND", "rules": [
                    { "field": "名称", "op": "cn", "data": ""},
                    { "field": "编码", "op": "cn", "data": ""}
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
        openWindow("添加系统菜单", "${ctx}/sysMenu/add.do", true);
    }
    function doEdit(id) {
        openWindow("修改系统菜单", "${ctx}/sysMenu/modify.do?id=" + id, true);
    }
    function doView(id) {
        openWindow("查看系统菜单", "${ctx}/sysMenu/view.do?id=" + id, false);
    }
    function doDelete(id) {
        doGridDelete("${ctx}/sysMenu/delete.do?id=" + id);
    }
    function move(id) {
        openWindow("查看系统菜单", "${ctx}/sysMenu/move.do?id=" + id, false);
    }
    //custom formatter
    function iconFormat(cellvalue, options, rowObject) {
        return '<span class="icon ' + cellvalue + '">&nbsp;</span>';
    }

    $("#queryBtn").click(function () {
        //构建查询条件
        var values = {};
        $(".title_Search input").each(function (i) {
            if ($(this).attr("id") == 'queryParams')return;
            values[$(this).attr("name")] = $(this).val();
        })
        //存入缓存
        $("#queryParams").val(JSON.stringify(values));
        $("#listGrid").trigger("reloadGrid");
    });

</script>
<form class="form-inline title_Search">
	<div class="form-group">
		<!--当userOpts 存在时，queryParams必须存在，用来放查询的参数  -->
		<input type="hidden" id="queryParams" name="queryParms" value="" />
		<label class="control-label">
			名称:&nbsp;
		</label>
		<div class="form-group">
			<input class="form-control" type="text" name="name" id="name" placeholder="名称">
			&nbsp;&nbsp;&nbsp;
		</div>
		<label class=" control-label">
			编码:&nbsp;
		</label>
		<div class="form-group">
			<input class="form-control" type="text"  name="privilege" id="privilege" placeholder="编码" >
			&nbsp;&nbsp;&nbsp;
		</div>
		<div class="form-group">
			<button class="btn btn-sm btn-primary" id="queryBtn" type="button">
				<i class="glyphicon glyphicon-search"></i>&nbsp;
				<strong>查询</strong>
			</button>
		</div>
	</div>
</form>
<div class="jqGrid_wrapper">
    <table id="listGrid"></table>
    <div id="pager"></div>
</div>