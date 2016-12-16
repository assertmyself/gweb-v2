<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    $(function () {
        var conf = {
            gridOpts: {
                url: "${ctx}/sysRole/gridDataCustom.do",
                colNames: ['ID','角色编码','角色名称','描述', '权限设置','操作'
                ],
                colModel: [
                    {name: 'id', width: "20",  searchtype: "integer", key: true},
                    {name: "code", width: "86",  searchtype: "string", sortable: true},
                    {name: "roleName", width: "80",  searchtype: "string", sortable: true},
                    {name: "description", width: "144" , searchtype: "string", sortable: true}
                ],
                actModel: [
                    {name: 'setPrivilege', width: 42 , sortable: false,title:false},
                    {name: 'operation', width: 80,  sortable: false,title:false}
                ],
                pager: '#pager',
                caption: false,
                shrinkToFit: true,
                gridComplete: function () {  //在此事件中循环为每一行添加修改和删除链接，“权限设置”项添加"授权"按钮
                    var ids = jQuery("#listGrid").jqGrid('getDataIDs');
                    for (var i = 0; i < ids.length; i++) {
                        var id = ids[i];
                        <c:if test="${canDesignate}">
	                        var opButtonPrivilege = '<button type="button" class="btn btn-sm btn-warning" onclick="doSetPrivilege(' + id + ')"><i class="fa fa-share-alt"></i>&nbsp授权</button>';
	                        jQuery("#listGrid").jqGrid('setRowData', ids[i], { setPrivilege: opButtonPrivilege});
                        </c:if>
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
                    { "field": "角色编码", "op": "cn", "data": ""},
                    { "field": "角色名称", "op": "cn", "data": ""}//,
                ]},
                queryButton: $("#queryButton"),
                queryDesc: $("#queryConditionDesc"),
                buildQuery: $("#queryParams")
            },
            isExportExcel: true
        };
        gridinit($("#listGrid"), conf);
    });
    function doSetPrivilege(id) {
        openWindow("角色权限管理", "${ctx}/sysRole/setPrivilege.do?id=" + id, true, 800, 500);
    }
    function doAdd() {
        openWindow("添加角色管理", "${ctx}/sysRole/add.do", true);
    }
    function doEdit(id) {
        openWindow("修改角色管理", "${ctx}/sysRole/modify.do?id=" + id, true);
    }
    function doView(id) {
        openWindow("查看角色管理", "${ctx}/sysRole/view.do?id=" + id, false);
    }
    function doDelete(id) {
        doGridDelete("${ctx}/sysRole/delete.do?id=" + id);
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
		<form class="form-inline title_Search">
			<div class="form-group">
				<!--当userOpts 存在时，queryParams必须存在，用来放查询的参数  -->
				<input type="hidden" id="queryParams" name="queryParms" value="" />
				<label class="control-label">
					角色编码:&nbsp;
				</label>
				<div class="form-group">
					<input class="form-control" type="text" name="code" id="code" placeholder="角色编码">
					&nbsp;&nbsp;&nbsp;
				</div>
				<label class=" control-label">
					角色名称:&nbsp;
				</label>
				<div class="form-group">
					<input class="form-control" type="text"name="roleName" id="roleName"placeholder="角色名称">
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
	</div>
</div>