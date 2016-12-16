/**
 * 批量操作组件封装
 * @param 数据表格的ID
 * @param canEdit 用户编辑权限
 * @param config 配置信息
 * @param elementId
 */
function initBatch(gridId,canEdit,config,elementId){
    
    if (gridId == null)
	gridId = listGridId;
        
    //按钮属性
    var button = config.button;
    //列表操作列中的按钮属性
    var opers = config.transfer.opers;
        
    var btnHtml = "<button class='btn_all default_t_green' id='batchTransfer' style='width: 130px;'> <i class='glyphicon glyphicon-repeat'></i>&nbsp;"+button.name+" </button>"+
    		  "<button class='btn_all default_t_blue' id='batchConfirm' style='display: none;width: 80px;margin-left: 240px;'><i class='glyphicon glyphicon-ok'></i>&nbsp;确定</button>"+
    		  "<button class='btn_all default_yellow' id='batchCancel' style='display:none;'><i class='glyphicon glyphicon-remove'></i>&nbsp;取消</button>"+
    		  "<button class='btn_all default_t_blue' id='batchAllChoose' style='display: none;'><i class='glyphicon glyphicon-ok-circle'></i>&nbsp;全选</button>";
    $("#"+elementId).html(btnHtml);
    
    //
    $("#batchTransfer").click(function(){
	$("#batchTransfer").css("visibility","hidden");
	$("#batchConfirm,#batchCancel,#batchAllChoose").css("display","inline");
		
	var ids = jQuery("#"+gridId).jqGrid('getDataIDs');
        for (var i = 0; i < ids.length; i++) {
            var id = ids[i];
            var opButton = "<input type='checkbox' name='checkbox"+ id +"' id='checkbox"+id+"'>";
            jQuery("#"+gridId).jqGrid('setRowData', ids[i], { operation: opButton});
        }
    });
    
    //确认事件
    $("#batchConfirm").click(function(){
	var ids = jQuery("#"+gridId).jqGrid('getDataIDs');
	var idString = "";
        for (var i = 0; i < ids.length; i++) {
            var id = ids[i];           
            var checkbox = document.getElementById("checkbox"+ids[i]).checked;
            if(checkbox){
        	idString += id +"," ;
            }
        }
        if(idString == ""){
            showWarningMsg("没有选择项!");
            return;
        }
        saveAjaxData(button.url + idString);
        
        $("#batchTransfer").css("visibility","inline");
        $("#batchConfirm,#batchCancel,#batchAllChoose").css("display","none");
        for (var i = 0; i < ids.length; i++) {
            var id = ids[i];
	    var opButton = '<a href="javascript:void(0);" onclick="doView('+ id +')" title="查看"><i class="glyphicon glyphicon-info-sign opt-btn opt-btn-green"></i></a>';
	    if(canEdit){
		for ( var j = 0; j < opers.length; j++) {
		    opButton +="<a href='#' title='"+opers[j].title+"' onclick='"+opers[j].method+"(" + id +")'>&nbsp;&nbsp;<i class='glyphicon glyphicon-"+ opers[j].icon+" opt-btn opt-btn-"+opers[j].color+"'></i></a>";
		}
	    }
	    jQuery("#"+gridId).jqGrid('setRowData', ids[i], { operation: opButton});
        }
    });
    
    //全选/全不选
    var ischeck = true;
    $("#batchAllChoose").click(function(){
	var ids = jQuery("#listGrid").jqGrid("getDataIDs");
        for (var i = 0; i < ids.length; i++) {
            var checkbox = document.getElementById("checkbox"+ids[i]);
            checkbox.checked = ischeck;
        }
        ischeck = !ischeck;
    });
    //取消切换
    $("#batchCancel").click(function(){
	$("#batchTransfer").css("visibility","inline");
	$("#batchConfirm,#batchCancel,#batchAllChoose").css("display","none");
	var ids = jQuery("#listGrid").jqGrid('getDataIDs');
	for (var i = 0; i < ids.length; i++) {
	    var id = ids[i];
            var opButton = '<a href="javascript:void(0);" onclick="doView(' + id +')" title="查看"><i class="glyphicon glyphicon-info-sign opt-btn opt-btn-green"></i></a>';
            if(canEdit){
        	for ( var j = 0; j < opers.length; j++) {
        	    opButton +="<a href='#' title='"+opers[j].title+"' onclick='"+opers[j].method+"(" + id +")'>&nbsp;&nbsp;<i class='glyphicon glyphicon-"+ opers[j].icon+" opt-btn opt-btn-"+opers[j].color+"'></i></a>";
		}
            }
            jQuery("#"+gridId).jqGrid('setRowData', ids[i], { operation: opButton});
	}
    });
}