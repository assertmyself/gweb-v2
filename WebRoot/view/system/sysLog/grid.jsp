<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<script type="text/javascript">
    $(function () {
        var conf = {
            gridOpts: {
                url: "${ctx}/sysLog/gridDataCustom.do",
                colNames: ['ID',
                    '用户名',
                    'IP地址',
                    '登陆时间',
                    '退出时间',
                    '浏览器版本',
                    '访问页面','模块','事件类型','结果','附加消息',
                    '操作'
                ],
                colModel: [
                    {name: 'id', width: 10,  searchtype: "integer",  key: true},
                    {name: "user.loginName", width: "25",  searchtype: "string", sortable: true},
                    {name: "ipAddress", width: "25",  searchtype: "string", sortable: true},
                    {name: "enterTime", width: "40" , searchtype: "datetime", sortable: true, formatter: 'date', formatoptions: {srcformat: 'Y-m-d H:i:s', newformat: 'Y-m-d H:i:s'}},
                    {name: "outTime", width: "40", searchtype: "datetime", sortable: true, formatter: 'date', formatoptions: {srcformat: 'Y-m-d H:i:s', newformat: 'Y-m-d H:i:s'}},
                    {name: "ieVersion", width: "80", searchtype: "string", sortable: true,hidden:true},
                    {name: "pageUrl", width: "50",  searchtype: "string", sortable: true},
                    {name: "moudle", width: "20",  searchtype: "string", sortable: true},
                    {name: "eventType", width: "15", searchtype: "string", sortable: true},
                    {name: "result", width: "15",  searchtype: "string", sortable: true},
                    {name: "message", width: "50",  searchtype: "string", sortable: true,hidden:true}
                ],
                actModel: [
                    {name: 'operation', width: 25, title:false, sortable: false}
                ],
                pager: '#pager',
                caption: false,
                shrinkToFit: true,
                gridComplete: function () {  //在此事件中循环为每一行添加修改和删除链接
                    var ids = jQuery("#listGrid").jqGrid('getDataIDs');
                    for (var i = 0; i < ids.length; i++) {
                        var id = ids[i];
                        var opButton ='<a href="javascript:void(0);" onclick="doView(' + id +')" title="查看"class="btn btn-sm btn-info">查看</a>';
                        //opButton = '<input type="button" value="查看" onclick="doView(' + id + ')" class="grid_button default_green" /> ';
                        jQuery("#listGrid").jqGrid('setRowData', ids[i], { operation: opButton});
                    }
                }
            },
            userOpts: {
                defaultQuery: { "groupOp": "AND", "rules": [
                    { "field": "用户名", "op": "cn", "data": ""},
                    { "field": "IP地址", "op": "cn", "data": ""},
                    { "field": "登陆时间", "op": "gt", "data": ""},
                    { "field": "退出时间", "op": "lt", "data": ""},
                    { "field": "访问页面", "op": "cn", "data": ""},
                    { "field": "模块", "op": "cn", "data": ""},
                    { "field": "事件类型", "op": "cn", "data": ""},
                    { "field": "结果", "op": "cn", "data": ""},
                    { "field": "附加消息", "op": "cn", "data": ""}
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
        openWindow("查看用户日志", "${ctx}/sysLog/view.do?id=" + id, false);
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
					用户名:&nbsp;
				</label>
				<div class="form-group">
					<input class="form-control" type="text" name="user.loginName" id="user.loginName">&nbsp;&nbsp;&nbsp;
				</div>
				<label class=" control-label">
					IP地址:&nbsp;
				</label>
				<div class="form-group">
					<input class="form-control" type="text"name="ipAddress" id="ipAddress">&nbsp;&nbsp;&nbsp;
				</div>
				<label class=" control-label">
					访问模块:&nbsp;
				</label>
				<div class="form-group">
					<input class="form-control" type="text"name="moudle" id="moudle">&nbsp;&nbsp;&nbsp;
				</div>
				<label class=" control-label">
					事件类型:&nbsp;
				</label>
				<div class="form-group">
					<input class="form-control" type="text"name="eventType" id="eventType">&nbsp;&nbsp;&nbsp;
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
