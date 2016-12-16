<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    $(function () {
        var conf = {
            gridOpts: {
                url: "${ctx}/sysPerson/gridDataCustom.do",
                colNames: ['ID',
                    '编号',
                    '姓名',
                    '性别',
//                    '身份证号',
                    '移动电话',
//                    '固定电话',
                    '所属单位',
                    '所属部门',
                    '操作'
                ],
                colModel: [
                    {name: 'id', width: 10,   searchtype: "integer", hidden: true, key: true},
                    {name: "code", width: "50", searchtype: "string", sortable: true},
                    {name: "name", width: "30", searchtype: "string", sortable: true},
                    {name: "sex", width: "15", searchtype: "integer", sortable: true, formatter: sexFormat},
//                    {name:"card",width:"50",align:"center",searchtype:"string",sortable:true,formatter:cardFormat},
                    {name: "mobile", width: "40",   searchtype: "string", sortable: true},
//                    {name:"officeTel",width:"40",align:"center",searchtype:"string",sortable:true},
                    {name: "company.name", width: "37", searchtype: "string", sortable: true},
                    {name: "dept.name", width: "28",  searchtype: "string", sortable: true}
                ],
                actModel: [
                    {name: 'operation', width: 40 ,title:false}
                ],
                pager: '#pager',
                caption: false,
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
                    { "field": "编号", "op": "cn", "data": ""},
                    { "field": "姓名", "op": "cn", "data": ""},
//                    { "field":"移动电话", "op":"cn", "data":""},
//                    { "field":"固定电话", "op":"cn", "data":""},
//                    { "field":"所属单位", "op":"cn", "data":""},
                    { "field": "所属部门", "op": "cn", "data": ""}
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
        openWindow("查看人员管理", "${ctx}/sysPerson/view.do?id=" + id, false);
    }
    <c:if test="${canEdit}">
    function doAdd() {
        openWindow("添加人员管理", "${ctx}/sysPerson/add.do", true);
    }
    function doEdit(id) {
        openWindow("修改人员管理", "${ctx}/sysPerson/modify.do?id=" + id, true);
    }
    function doDelete(id) {
        doGridDelete("${ctx}/sysPerson/delete.do?id=" + id);
    }
    function doJdperson(id) {
        loadMainPage("${ctx}/jdPersonSubject/grid.do?personid=" + id, "系统管理 >> 人事管理 >> 人员资质");
    }
    </c:if>
    //custom formatter
    function cardFormat(cellvalue, options, rowObject) {
        return cellvalue;
    }
    function sexFormat(cellvalue, options, rowObject) {
        if (cellvalue == "true") {
            return "男";
        } else if (cellvalue == "false") {
            return "女";
        } else {
            return "";
        }
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
					编号:&nbsp;
				</label>
				<div class="form-group">
					<input class="form-control" type="text" name="code" id="code"placeholder="编号">
					&nbsp;&nbsp;&nbsp;
				</div>
				<label class=" control-label">
					姓名:&nbsp;
				</label>
				<div class="form-group">
					<input class="form-control" type="text"name="name" id="name" placeholder="姓名">
					&nbsp;&nbsp;&nbsp;
				</div>
				<label class=" control-label">
					所属部门:&nbsp;
				</label>
				<div class="form-group">
					<input class="form-control" type="text"name="dept.name" id="dept.name" placeholder="所属部门">
					&nbsp;&nbsp;&nbsp;
				</div>
				<div class="form-group">
					<button class="btn btn-sm btn-primary" id="queryBtn" type="button">
						<i class="glyphicon glyphicon-search"></i>&nbsp;
						<strong>查询</strong>
					</button>&nbsp;&nbsp;&nbsp;
				</div>
				<div class="form-group">
					<button class="btn btn-sm btn-success" onclick="doAdd();" type="button">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;
						<strong>新增</strong>
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