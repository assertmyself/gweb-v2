<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    $(function () {
        var conf = {
            gridOpts: {
                url: <c:choose>
						<c:when test="${codeId!=null}">
                        	"${ctx}/sysArea/gridDataCustom.do?codeId=" + ${codeId},
                		</c:when>
                		<c:otherwise>
                			"${ctx}/sysArea/gridDataCustom.do?",
                		</c:otherwise>
                	 </c:choose>
                colNames: ['ID','编码', '名称', '层次', '是否叶子节点', '备注', '管理热点', '操作'],
                colModel: [
                    {name: 'id', width: "15", searchtype: "integer", hidden: false,key: true},
                    {name: "areaCode", width: "50", searchtype: "string", sortable: true},
                    {name: "areaName", width: "40", searchtype: "string", sortable: true},
                    //{name: "isReserved", width: "50", searchtype: "integer", sortable: true, formatter: booleanFormat},
                    {name: "layer", width: "30", searchtype: "string", sortable: true},
                    {name: "isLeaf", width: "48", searchtype: "integer", sortable: true, formatter: booleanFormat},
                    {name: "description", width: "90", searchtype: "string", sortable: true}
                ],
                actModel: [
                    {name: 'setPrivilege', width: 32, sortable: false,title:false},
                    {name: 'operation', width: 60, sortable: false,title:false}
                ],
                pager: '#pager',
                caption: false,
                shrinkToFit: true,
                gridComplete: function () {  //在此事件中循环为每一行添加修改和删除链接
                    var ids = jQuery("#listGrid").jqGrid('getDataIDs');
                    for (var i = 0; i < ids.length; i++) {
                        var id = ids[i];


                        var row = jQuery("#listGrid").jqGrid('getRowData',id);
                        var isLeaf = row['isLeaf'];
                        if(isLeaf=="是"){
                        	var opButtonPrivilege = '<button type="button" class="btn btn-sm btn-warning" onclick="doSet(' + id + ')"><i class="fa fa-share-alt"></i>&nbsp热点</button>';
                        	jQuery("#listGrid").jqGrid('setRowData', ids[i], { setPrivilege: opButtonPrivilege});
						}
                        var opButton = '<a href="javascript:void(0);" onclick="doView(' + id +')" class="btn btn-sm btn-info"> 查看 </a> ';
                        <c:if test="${canEdit}">
                        	opButton += '<a href="javascript:void(0);" onclick="doEdit(' + id +')" class="btn btn-sm btn-primary"> 编辑 </a> ';
                        	opButton += '<a href="javascript:void(0);" onclick="doDelete(' + id +')" class="btn btn-sm btn-danger"> 删除 </a> ';
                        </c:if>
                        jQuery("#listGrid").jqGrid('setRowData', ids[i], { operation: opButton});
                    }
                }
            },
            userOpts: {
                defaultQuery: { "groupOp": "AND", "rules": [
                    { "field": "编码", "op": "cn", "data": ""},
                    { "field": "名称", "op": "cn", "data": ""},
                    { "field": "层次", "op": "cn", "data": ""}
                ]},
                queryButton: $("#queryButton"),
                queryDesc: $("#queryConditionDesc"),
                buildQuery: $("#queryParams")
            },
            isExportExcel: true
        };
        gridinit($("#listGrid"), conf);
    });

    function doSet(id) {
        openWindow("热点列表", "${ctx}/sysArea/setMngNes.do?id=" + id, true, 200, 500);
    }
    
    function doView(id) {
        openWindow("查看明细", "${ctx}/sysArea/view.do?id=" + id, false);
    }

    function doEdit(id) {
        openWindow("修改角色管理", "${ctx}/sysArea/modify.do?id=" + id, true);
    }

    function doDelete(id) {
        doGridDelete("${ctx}/sysArea/delete.do?id=" + id);
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
		<!--当userOpts 存在时，queryParams必须存在，用来放查询的参数  -->
		<input type="hidden" id="queryParams" name="queryParms" value="" /> <label
			class="control-label"> 编码:&nbsp; </label>
		<div class="form-group">
			<input class="form-control" type="text" name="areaCode" id="areaCode">&nbsp;&nbsp;&nbsp;
		</div>
		<label class=" control-label"> 名称:&nbsp; </label>
		<div class="form-group">
			<input class="form-control" type="text" name="areaName" id="areaName">&nbsp;&nbsp;&nbsp;
		</div>
		<label class=" control-label"> 层次:&nbsp; </label>
		<div class="form-group">
			<input class="form-control" type="text" name="layer" id="layer">&nbsp;&nbsp;&nbsp;
		</div>
		<div class="form-group">
			<button class="btn btn-sm btn-primary" id="queryBtn" type="button">
				<i class="glyphicon glyphicon-search"></i>&nbsp; <strong>查询</strong>
			</button>
		</div>
	</div>
</form>

<div class="jqGrid_wrapper">
	<table id="listGrid"></table>
	<div id="pager"></div>
</div>