<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    $(function () {
        var conf = {
            gridOpts: {
                url: <c:choose>
                        <c:when test="${codeId!=null}">
                        "${ctx}/sysCodeDetail/gridDataCustom.do?codeId=" + ${codeId},
                </c:when>
                <c:otherwise>
                "${ctx}/sysCodeDetail/gridDataCustom.do?",
                </c:otherwise>
                </c:choose>
                colNames: ['ID',
                    '编码',
                    '名称',
                    '是否系统定义',
                    '特殊标记',
                    '是否有效',
                    '备注',
                    '操作'
                ],
                colModel: [
                    {name: 'id', width: 10, searchtype: "integer", hidden: true,key: true},
                    {name: "code", width: "100", searchtype: "string", sortable: true},
                    {name: "name", width: "100", searchtype: "string", sortable: true},
                    {name: "isReserved", width: "68", searchtype: "integer", sortable: true, formatter: booleanFormat},
                    {name: "tag", width: "104", searchtype: "string", sortable: true},
                    {name: "isValid", width: "48", searchtype: "integer", sortable: true, formatter: booleanFormat},
                    {name: "description", width: "190", searchtype: "string", sortable: true}
                ],
                actModel: [
                    {name: 'operation', width: 50, sortable: false,title:false}
                ],
                pager: '#pager',
                caption: false,
                shrinkToFit: true,
                gridComplete: function () {  //在此事件中循环为每一行添加修改和删除链接
                    var ids = jQuery("#listGrid").jqGrid('getDataIDs');
                    for (var i = 0; i < ids.length; i++) {
                        var id = ids[i];
                        var opButton = '<a href="javascript:void(0);" onclick="doView(' + id +')" class="btn btn-sm btn-info"> 查看 </a>';
                        //opButton = '<input type="button" title="查看" value="查看" onclick="doView(' + id + ')" class="grid_button default_green" /> ';
                        jQuery("#listGrid").jqGrid('setRowData', ids[i], { operation: opButton});
                    }
                }
            },
            userOpts: {
                defaultQuery: { "groupOp": "AND", "rules": [
                    { "field": "编码", "op": "cn", "data": ""},
                    { "field": "名称", "op": "cn", "data": ""},
                    { "field": "特殊标记", "op": "cn", "data": ""}
                ]},
                queryButton: $("#queryButton"),
                queryDesc: $("#queryConditionDesc"),
                buildQuery: $("#queryParams")
            },
            isExportExcel: true
        };
        gridinit($("#listGrid"), conf);
    });
    function doView(id) {
        openWindow("查看系统代码明细", "${ctx}/sysCodeDetail/view.do?id=" + id, false);
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


<%--<button class="btn_all btn_basic" onclick="doAdd()"><i class="default-icon-plus"></i>新增</button>--%>

<form class="form-inline title_Search">
	<div class="form-group">
		<!--当userOpts 存在时，queryParams必须存在，用来放查询的参数  -->
		<input type="hidden" id="queryParams" name="queryParms" value="" /> <label
			class="control-label"> 编码:&nbsp; </label>
		<div class="form-group">
			<input class="form-control" type="text" name="code" id="code">&nbsp;&nbsp;&nbsp;
		</div>
		<label class=" control-label"> 名称:&nbsp; </label>
		<div class="form-group">
			<input class="form-control" type="text" name="name" id="name">&nbsp;&nbsp;&nbsp;
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