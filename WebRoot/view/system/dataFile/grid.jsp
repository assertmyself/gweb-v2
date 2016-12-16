<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    $(function () {
        var conf;
        conf = {
            gridOpts: {
                url: "${ctx}/dataFile/gridDataCustom.do",
                colNames: ['标识', '文件名称', '文件日期', '文件路径', '文件大小(M)', '操作'
                ],
                colModel: [
                    {name: 'id', width: 10, searchtype: "integer", hidden: true, key: true},
                    {name: 'fileName', width: "50", searchtype: "string", sortable: true},
                    {name: "fileDate", width: "50", searchtype: "string", sortable: true},
                    {name: "filePath", width: "50", searchtype: "string", sortable: true},
                    {name: "fileSize", width: "50", searchtype: "string", sortable: true}//,
                    //{name: "updateTime", width: "50", searchtype: "datetime", sortable: true, formatter: 'date', formatoptions: {srcformat: 'Y-m-d H:i:s', newformat: 'Y-m-d H:i:s'}},
                ],
                actModel: [
                    {name: 'operation', width: 40,title:false}
                ],
                pager: '#pager',
                caption: false,
                shrinkToFit: true,
                gridComplete: function () {  //在此事件中循环为每一行添加修改和删除链接
                    var ids = jQuery("#listGrid").jqGrid('getDataIDs');
                    for (var i = 0; i < ids.length; i++) {
                        var id = ids[i];
                        var item = jQuery("#listGrid").jqGrid('getRowData', id);
                        var name = item.fileName;
                        var opButton = '<input type="button" value="下载" onclick="doDownload(\'' + name + '\')"   class="btn btn-sm btn-info" /> ';
                        opButton += '<input type="button" value="删除" onclick="doDelete(\'' + name + '\')"  class="btn btn-sm btn-danger" /> ';
                        opButton += '<input type="button" value="恢复" onclick="doRecover(\'' + name + '\')"  class="btn btn-sm btn-primary" /> ';
                        jQuery("#listGrid").jqGrid('setRowData', ids[i], { operation: opButton});
                    }
                }
            },
            userOpts: {
                defaultQuery: { "groupOp": "AND", "rules": [
                    { "field": "在线状态", "op": "cn", "data": ""}
                ]},
                queryButton: $("#queryButton"),
                queryDesc: $("#queryConditionDesc")
            }
        };
        gridinit($("#listGrid"), conf);
    });

    function doDownload(name) {
        //saveAjaxData("${ctx}/dataFile/download.do?name=" + name, null, null, null);
        window.open("${ctx}/dataFile/download.do?name=" + name);
    }

    function doDelete(name) {
        doGridDelete("${ctx}/dataFile/delete.do?name=" + name);
    }

    function doRecover(name) {
        saveAjaxData("${ctx}/dataFile/recover.do?name=" + name, null, null, null);
    }

    function doBackup() {
        saveAjaxData("${ctx}/dataFile/backup.do", null, null, null);
    }

</script>

<form class="form-inline title_Search">
	<div class="form-group">
		<button class="btn btn-sm btn-info" onclick="doBackup()">
			<i class="default-icon-plus"></i>备份
		</button>
	</div>
</form>
<div class="jqGrid_wrapper">
	<table id="listGrid"></table>
	<div id="pager"></div>
</div>