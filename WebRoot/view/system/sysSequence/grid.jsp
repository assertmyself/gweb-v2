<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    $(function () {
        var conf = {
            gridOpts: {
                url: "${ctx}/sysSequence/gridDataCustom.do",
                colNames: ['ID',
                    '下一标识',
                    '操作'
                ],
                colModel: [
                    {name: 'id', width: 10, searchtype: "integer", hidden: true},
                    {name: "lastid", width: "800", searchtype: "string", sortable: true}
                ],
                actModel: [
                    {name: 'operation', width: 80, title:false, sortable: false}
                ],
                pager: '#pager',
                caption: "false",
                shrinkToFit: true,
                gridComplete: function () {  //在此事件中循环为每一行添加修改和删除链接
                    var ids = jQuery("#listGrid").jqGrid('getDataIDs');
                    for (var i = 0; i < ids.length; i++) {
                        var id = ids[i];
                        var opButton = '<a href="javascript:void(0);" onclick="doView(' + id +')"  class="btn btn-sm btn-info">&nbsp;查看&nbsp;</a> ';
                        <c:if test="${canEdit}">
	                        opButton += '<a href="javascript:;"  onclick="doEdit(' + id +')"  class="btn btn-sm btn-primary">&nbsp;编辑&nbsp;</a> ';
	                        opButton += '<a href="javascript:void(0);"  onclick="doDelete(' + id +')"  class="btn btn-sm btn-danger">&nbsp;删除</a>';
                   		</c:if>
                        jQuery("#listGrid").jqGrid('setRowData', ids[i], { operation: opButton});
                    }
                }
            },
            userOpts: {
                defaultQuery: { "groupOp": "AND", "rules": [
                    { "field": "下一标识", "op": "cn", "data": ""}
                ]},
                queryButton: $("#queryButton"),
                queryDesc: $("#queryConditionDesc")
            }
        };
        gridinit($("#listGrid"), conf);
    });
    function doAdd() {
        openWindow("添加序列", "${ctx}/sysSequence/add.do", true);
    }
    function doEdit(id) {
        openWindow("修改序列", "${ctx}/sysSequence/modify.do?id=" + id, true);
    }
    function doView(id) {
        openWindow("查看序列", "${ctx}/sysSequence/view.do?id=" + id, false);
    }
    function doDelete(id) {
        doGridDelete("${ctx}/sysSequence/delete.do?id=" + id);
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


<div class="ibox col-sm-12">

	<div class="ibox-content">
	
		<form class="form-inline title_Search" >
			<div class="form-group">
				<input type="hidden" id="queryParams" name="queryParms" value="" />
				<label class="control-label">
					下一个标识:&nbsp;
				</label>
				<div class="form-group">
					<input class="form-control" type="text" name="lastid" id="lastid">&nbsp;&nbsp;&nbsp;
				</div>
			
				<div class="form-group">
					<button class="btn btn-sm btn-primary" id="queryBtn" type="button">
						<i class="glyphicon glyphicon-search"></i>&nbsp;
						<strong>查询</strong>
					</button>
				</div>
				<c:if test="${canEdit }">
				<div class="form-group">&nbsp;&nbsp;&nbsp;
					<button class="btn btn-sm btn-success" onclick="doAdd();" type="button">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;
						<strong>新增</strong>
					</button>
				</div>
				</c:if>
			</div>
		</form>
		
	
		<div class="jqGrid_wrapper">
			<table id="listGrid"></table>
			<div id="pager"></div>
		</div>
	</div>
</div>
