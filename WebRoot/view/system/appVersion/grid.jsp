<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    $(function () {

        // Android版本文件
        var conf;
        conf = {
            gridOpts: {
                url: "${ctx}/appVersion/gridDataCustom.do?appOsType=1",
                colNames: ['标识', 'APP类型', 'APP版本号', 'APP版本文件名称', 'APP版本文件路径', 'APP版本文件大小(M)', '操作'
                ],
                colModel: [
                    {name: 'id', width: 10, searchtype: "integer", hidden: true, key: true},
                    {name: 'appOsType', width: "50", searchtype: "integer", sortable: true, formatter: osFormat},
                    {name: "appVersionName", width: "50", searchtype: "string", sortable: true},
                    {name: "appFileName", width: "50", searchtype: "string", sortable: true},
                    {name: "appFilePath", width: "50", searchtype: "string", sortable: true},
                    {name: "appFileSize", width: "50", searchtype: "string", sortable: true}//,
                    //{name: "updateTime", width: "50", searchtype: "datetime", sortable: true, formatter: 'date', formatoptions: {srcformat: 'Y-m-d H:i:s', newformat: 'Y-m-d H:i:s'}},
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
                        //var item = jQuery("#listGrid").jqGrid('getRowData', id);
                        //var path = item.appFilePath;
                        var opButton = '<input type="button" value="下载" onclick="doDownload(\'' + id + '\')"  class="btn btn-sm btn-success" /> ';
                        opButton += '<input type="button" value="编辑" onclick="doEdit(\'' + id + '\')"  class="btn btn-sm btn-primary" /> ';
                        opButton += '<input type="button" value="删除" onclick="doDelete(\'' + id + '\')"  class="btn btn-sm btn-danger" /> ';
                        opButton += '<input type="button" value="查看" onclick="doView(\'' + id + '\')"  class="btn btn-sm btn-info" /> ';
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

        // IOS版本文件
        var conf2;
        conf2 = {
            gridOpts: {
                url: "${ctx}/appVersion/gridDataCustom.do?appOsType=2",
                colNames: ['标识', 'APP类型', 'APP版本号', 'APP版本文件名称', 'APP版本文件路径', 'APP版本文件日期', 'APP版本文件大小(M)', '操作'
                ],
                colModel: [
                    {name: 'id', width: 10, searchtype: "integer", hidden: true, key: true},
                    {name: 'appOsType', width: "50", searchtype: "integer", sortable: true, formatter: osFormat},
                    {name: "appVersion", width: "50", searchtype: "string", sortable: true},
                    {name: "appFileName", width: "50", searchtype: "string", sortable: true},
                    {name: "appFilePath", width: "50", searchtype: "string", sortable: true},
                    {name: "appFileDate", width: "50", searchtype: "string", sortable: true},
                    {name: "appFileSize", width: "50", searchtype: "string", sortable: true}//,
                    //{name: "updateTime", width: "50", searchtype: "datetime", sortable: true, formatter: 'date', formatoptions: {srcformat: 'Y-m-d H:i:s', newformat: 'Y-m-d H:i:s'}},
                ],
                actModel: [
                    {name: 'operation', width: 40}
                ],
                pager: '#pager2',
                caption: "IOS版本文件列表",
                shrinkToFit: true,
                gridComplete: function () {  //在此事件中循环为每一行添加修改和删除链接
                    var ids = jQuery("#listGrid2").jqGrid('getDataIDs');
                    for (var i = 0; i < ids.length; i++) {
                        var id = ids[i];
                        //var item = jQuery("#listGrid2").jqGrid('getRowData', id);
                        //var path = item.appFilePath;
                        var opButton = '<input type="button" value="下载" onclick="doDownload(\'' + id + '\')"  class="btn btn-sm btn-success" /> ';
                        opButton += '<input type="button" value="编辑" onclick="doEdit(\'' + id + '\')"  class="btn btn-sm btn-primary" /> ';
                        opButton += '<input type="button" value="删除" onclick="doDelete(\'' + id + '\')"  class="btn btn-sm btn-danger" /> ';
                        opButton += '<input type="button" value="查看" onclick="doView(\'' + id + '\')"  class="btn btn-sm btn-info" /> ';
                        jQuery("#listGrid2").jqGrid('setRowData', ids[i], { operation: opButton});
                    }
                }
            },
            userOpts: {
                defaultQuery: { "groupOp": "AND", "rules": [
                ]},
                queryButton: $("#queryButton2"),
                queryDesc: $("#queryConditionDesc2")
            }
        };
        gridinit($("#listGrid2"), conf2);

    });

    function doDownload(id) {
        //saveAjaxData("${ctx}/appVersion/download.do?id=" + id, null, null, null);
        window.open("${ctx}/appVersion/download.do?id=" + id);
    }

    function doEdit(id) {
        openWindow("修改APP版本", "${ctx}/appVersion/modify.do?id=" + id, true);
    }

    function doDelete(id) {
        doGridDelete("${ctx}/appVersion/delete.do?id=" + id);
    }

    function doView(id) {
        openWindow("查看APP版本", "${ctx}/appVersion/view.do?id=" + id, false);
    }

    function doAdd() {
        openWindow("添加APP版本", "${ctx}/appVersion/add.do", true);
    }

    function osFormat(cellvalue, options, rowObject) {
        if (cellvalue == "1") {
            return "Android";
        } else if (cellvalue == "2") {
            return "IOS";
        } else {
            return "";
        }
    }

</script>
<form class="form-inline title_Search">
	<div class="form-group">
		<button class="btn btn-sm btn-info" onclick="doAdd()">
			<i class="default-icon-plus"></i>添加
		</button>
		</div>
</form>
<div class="jqGrid_wrapper">
	<table id="listGrid"></table>
	<div id="pager"></div>
</div>
