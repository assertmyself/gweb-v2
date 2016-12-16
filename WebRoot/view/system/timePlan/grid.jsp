<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    $(function () {

        // 数据库备份定时任务
        var conf;
        conf = {
            gridOpts: {
                url: "${ctx}/timePlan/gridDataCustom.do?ownerType=1",
                colNames: ['标识', '定时类型', '开始时间', '重复时间', '间隔分钟', '状态', '操作'
                ],
                colModel: [
                    {name: 'id', width: 10, align: "center", searchtype: "integer", hidden: true, key: true},
                    {name: 'type', width: "50", align: "center", searchtype: "integer", sortable: true, formatter: timerTypeFormat},
                    {name: "beginTime", width: "50", align: "center", searchtype: "string", sortable: true},
                    {name: "repeatTime", width: "50", align: "center", searchtype: "string", sortable: true},
                    {name: "intervalTime", width: "50", align: "center", searchtype: "integer", sortable: true},
                    {name: "state", width: "50", align: "center", searchtype: "boolean", sortable: true}
                    //{name: "updateTime", width: "50", align: "center", searchtype: "datetime", sortable: true, formatter: 'date', formatoptions: {srcformat: 'Y-m-d H:i:s', newformat: 'Y-m-d H:i:s'}},
                ],
                actModel: [
                    {name: 'operation', width: 40, align: 'center',title:false}
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
                ]},
                queryButton: $("#queryButton"),
                queryDesc: $("#queryConditionDesc")
            }
        };
        gridinit($("#listGrid"), conf);

    });

    function doEdit(id) {
        openWindow("修改定时任务", "${ctx}/timePlan/modify.do?id=" + id, true);
    }

    function doDelete(id) {
        doGridDelete("${ctx}/timePlan/delete.do?id=" + id);
    }

    function doView(id) {
        openWindow("查看定时任务", "${ctx}/timePlan/view.do?id=" + id, false);
    }

    function doAdd(ownerType) {
        openWindow("添加定时任务", "${ctx}/timePlan/add.do?ownerType="+ownerType, true);
    }

    function timerTypeFormat(cellvalue, options, rowObject) {
        if (cellvalue == "1") {
            return "一次性任务";
        } else if (cellvalue == "2") {
            return "每天重复";
        } else if (cellvalue == "3") {
            return "每周重复";
        } else if (cellvalue == "4") {
            return "每月重复";
        } else if (cellvalue == "5") {
            return "分钟周期重复";
        } else {
            return "";
        }
    }

</script>



<div class="ibox col-sm-12">
	<div class="ibox-content">
		<form class="form-inline title_Search" >
			<div class="form-group">
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