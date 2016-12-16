<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    $(function () {
        var conf = {
            gridOpts: {
                url: "${ctx}/sysUser/gridDataCustom.do",
                colNames: ['ID','用户名','显示名称','帐号状态','角色','操作'
                ],
                colModel: [
                    {name: 'id', width: "20",  searchtype: "integer", key: true},
                    {name: "loginName", width: "30", searchtype: "string", sortable: true},
                    {name: "displayName", width: "35", searchtype: "string", sortable: true},
                    {name: "status", width: "20", searchtype: "string", sortable: false, formatter: customFormat},
                    {name: "roleNames", width: "50", searchtype: "string", sortable: false}
                ],
                actModel: [
                    {name: 'operation', width: 60, title:false, sortable: false}
                ],
                pager: '#pager',
                caption: false,
    			viewrecords : true,
                shrinkToFit: true,
                size:1,
                gridComplete: function () {  //在此事件中循环为每一行添加修改和删除链接
                    var ids = jQuery("#listGrid").jqGrid('getDataIDs');
                    for (var i = 0; i < ids.length; i++) {
                        var id = ids[i];
                        var opButton = '<a href="javascript:void(0);" onclick="doView(' + id +')"  class="btn btn-sm btn-info">&nbsp;查看&nbsp;</a> ';
                        
                        <c:if test="${canEdit}">
	                        opButton += '<a href="javascript:;"  onclick="doEdit(' + id +')"  class="btn btn-sm btn-primary">&nbsp;编辑&nbsp;</a> ';
	                        opButton += '<a href="javascript:void(0);"  onclick="doReset(' + id +')" class="btn btn-sm btn-warning">&nbsp;密码重置&nbsp;</a> ';
	                        opButton += '<a href="javascript:void(0);"  onclick="doDelete(' + id +')"  class="btn btn-sm btn-danger">&nbsp;删除</a>';
                        </c:if>
                        jQuery("#listGrid").jqGrid('setRowData', ids[i], { operation: opButton});
                    }
                }
            },
            userOpts: {
                defaultQuery: { "groupOp": "AND", "rules": [
                    { "field": "用户名", "op": "cn", "data": "", "alias": "loginName"},
                    { "field": "显示名称", "op": "cn", "data": "", "alias": "displayName"}
                ]},
                queryButton: $("#queryButton"),
                queryDesc: $("#queryConditionDesc"),
                resetButton: "#conditionsDesc",
                buildQuery: $("#queryParams")
            }
        };
        gridinit($("#listGrid"), conf);
    });
    function doView(id) {
        openWindow("查看用户管理", "${ctx}/sysUser/view.do?id=" + id, false);
    }
    <c:if test="${canEdit}">
    function doAdd() {
        openWindow("添加用户管理", "${ctx}/sysUser/add.do", true);
    }
    function doSync() {
    	saveAjaxData("${ctx}/sysUser/sync.do",null);
    }
    function doEdit(id) {
        openWindow("修改用户管理", "${ctx}/sysUser/modify.do?id=" + id, true);
    }
    function doDelete(id) {
        doGridDelete("${ctx}/sysUser/delete.do?id=" + id);
    }
    function doReset(id) {
        doGridDelete("${ctx}/sysUser/reset.do?id=" + id,"您确定要重置该用户密码吗？");
    }
    </c:if>
    //状态
    function customFormat(cellvalue, options, rowObject) {
        return cellvalue == "1" ? "有效" : "无效";
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
					用户名:&nbsp;
				</label>
				<div class="form-group">
					<input class="form-control" type="text" name="loginName" id="loginName">&nbsp;&nbsp;&nbsp;
				</div>
				<label class=" control-label">
					显示名称:&nbsp;
				</label>
				<div class="form-group">
					<input class="form-control" type="text"name="displayName" id="displayName">&nbsp;&nbsp;&nbsp;
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