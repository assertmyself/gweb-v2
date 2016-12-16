<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
	$(function(){
		var conf = {
			gridOpts:{
				url:"${ctx}/sysOnlineUser/gridDataCustom.do",
				colNames:['序号','用户名','显示名称','用户IP','所属单位','登录时间','在线时间（分钟）','操作'],
				colModel:[
					{name:'id',width:50,sortable:true},
					{name:'userName',width:50,sortable:true},
					{name:'realName',width:50, sortable:true},
					{name:'ipAddress',width:50, sortable:true},
					{name:'dept',width:50, sortable:true},
					{name:'loginTime',width:50, sortable:true},
					{name:'onlineTimeMinute',width:50, sortable:true}
				],
				actModel:[
					{name:'operation',width:50, title:false, sortable: false}
				],
				width:'auto',
				heigth:100,
				rowNum:15,
				pager:'#pager',
				gridComplete:function(){
					var ids = jQuery("#listGrid").jqGrid('getDataIDs');
					for(var i=0;i<ids.length;i++){
						var id = ids[i];
						var rowData = jQuery("#listGrid").jqGrid("getRowData",id);
						var data = rowData['userName'];
						var opButton = '<a href="javascript:void(0);" onclick="doView(\'' +data +'\')" title="查看"class="btn btn-sm btn-info">查看</a>';
                        jQuery("#listGrid").jqGrid('setRowData',id,{operation:opButton}); 
					}
				}
			},
			userOpts: {
	        	defaultQuery: { "groupOp": "AND", "rules": [
	            	{ "field": "用户名", "op": "cn", "data": ""},
	                { "field": "显示名称", "op": "cn", "data": ""}]
				},
	            queryButton: $("#queryButton"),
				queryDesc: $("#queryConditionDesc"),
				buildQuery: $("#queryParams")
	        },
			isExportExcel: true
		};
		gridinit($("#listGrid"),conf);
	});

	function doView(data){
		openWindow("查看在线用户信息", "${ctx}/sysOnlineUser/view.do?username=" + data, false);
	}
	
	$("#queryBtn").click(function () {
		//构建查询条件
		var values = {};
		$(".title_Search input").each(function(i){
			if($(this).attr("id")=='queryParams')
			    return;
			values[$(this).attr("name")]=$(this).val();
		})
		 $("#listGrid").jqGrid("setGridParam",{
        	 url:"${ctx}/sysOnlineUser/gridDataCustom.do?userName="+$("#userName").val()+"&realName="+$("#realName").val()
        });
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
					<input class="form-control" type="text" name="userName"
						id="userName" placeholder="用户名">
					&nbsp;&nbsp;&nbsp;
				</div>
				<label class=" control-label">
					显示名称:&nbsp;
				</label>
				<div class="form-group">
					<input class="form-control" type="text" name="realName"
						id="realName" placeholder="显示名称">
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