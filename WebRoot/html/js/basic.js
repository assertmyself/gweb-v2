/**
 * basic.js ,框架基础 可以被自定义组件调用
 * @author Administrator
 */
var errAjaxMsg = "<div class=\"errMsg\">数据出错或服务器正忙，请稍候重新尝试！</div>";
var openWindowId = "winOpenId";
var alertWindowId = "alertWinOpenId";
var modal = "modal";
var msgTitleStyle = "alert-info";
var msgDefaultTitle = "系统提示";
var rootIcon = "home.png";
var listGridId = "listGrid";
var arrays = [];
var showNameArrays = [];
/** 
 * 初始化左侧菜单 
 */
function initLeftMenu() {
    var menulist = '';
    
    $.each(_menus.basic, function (i, n) {
        if (n.menus == "") {
            menulist += 
                '<li>'+
            		'<a href="#" rel="' + n.url + '" ><i class="' + n.icon + '"></i> <span class="nav-label">'+n.menuname+'</span></a>'+
            	'</li>';
        } else {
            menulist += 
                '<li>'+
    				'<a href="#"><i class="' + n.icon + '"></i> <span class="nav-label">'+n.menuname+'</span><span class="fa arrow"></span></a>'+
    				initSubMenu(n)+
            	'</li>';
        }
    });
    menulist += '</ul>';
    return menulist;
}

/** 
 * 加载左侧菜单的子菜单 
 * @param menu 子菜单
 */
function initSubMenu(menu) {
    var subMenulist = '';
    subMenulist += '<ul class="nav nav-second-level collapse">';
    $.each(menu.menus, function (j, o) {
        subMenulist += '<li><a class="J_menuItem" href="#" rel="' + o.url + '" jsevent="'+ o.jsevent +'" ><i class="' + o.icon + '"></i>'+o.menuname+'</a></li>';
    });
    subMenulist += '</ul>';
    return subMenulist;
}

/**
 * 判断是否是html
 */
function _isHtml(url){
	var html='html';
	var d=url.length-html.length;
	return (d>=0&&url.lastIndexOf(html)==d)
}
/**
 * 加载html  ifram标签
 */
function _loadHtml(url){
	var iframe='<iframe class="J_iframe" name="iframe" width="100%" height="100%"'+
		'src="'+url+'" frameborder="0"'+
			'data-id="index_v1.html" seamless></iframe>';
	$("#content-main").html(iframe);
}
/**
 * 加载main区域页面 
 * @param url 请求url
 * @param tabName tab标签名
 */
function loadMainPage(url, tabName,jsevent){
	suFn = function(data){
		$("#content-main").addClass("animated");
		$("#content-main").addClass("fadeInRight");
		$("#content-main").html(data);
	}
	if(jsevent=="open"){
		openWindow(tabName,url,false);
	}else{
		if(_isHtml(url)){
			_loadHtml(url); // html 页面， iframe框架处理
		}else{
			$("#content-main").removeClass("animated");
			$("#content-main").removeClass("fadeInRight");
			loadAjaxData("content-main", url,null,null,suFn);
		}
	}
}

/**
 * 异步获取ajax数据
 * @param objName 加载数据的容器
 * @param url 请求url
 * @param isTip 是否出现进度条
 * @param dataType 异步请求的数据类型，如json,html,text;默认是text
 * @param suFn 请求成功时执行的函数
 * @param erFn 请求失败时执行的函数
 */
function loadAjaxData(objName, url, isTip, dataType, suFn, erFn) {
	var before = function(){
		$("#"+objName).waiting();
	}
	var after = function(){
		$("#"+objName).waiting("hide");
	}
	if(isNull(dataType)){
		dataType = 'text';
	}
	if(isNull(suFn)){
		suFn = function(data){
			$("#"+objName).html(data);
		}
	}
	if(isNull(erFn)){
		erFn = function(XMLHttpRequest, textStatus, errorThrown){
			$("#"+objName).html(errAjaxMsg);
		}
	}
	if(isNull(isTip)){
		before = null;
		after = null;
	}
    $.ajax({
        url: url,
        cache: false,
        data: '',
        dataType: dataType,
        success: suFn,
		error: erFn,
		beforeSend: before,
		complete: after
    });
}



/**
 * 弹出对话框
 * @param title 对话框的标题
 * @param url 请求的url
 * @param isRefreshGrid 是否刷新jqGrid表格
 * @param width 弹出框的宽度
 * @param height 弹出框的高度
 */
function openWindow(title, url, isRefreshGrid) {
	/*
	var leaf = title;
	if(!isNull(title)&&title.indexOf(">>")>-1){
		var pos = title.split(">>");
		leaf = pos[pos.length-1];
	}*/
    openNewWindow(null, title, url, isRefreshGrid);

}
/**
 * 初始化form 样式
 * @param select 父级选择器
 * @return
 */
function initForm(){
	$(".form-group-hz").addClass("form-group");
	var labs=$(".form-group-hz label");
	$(labs).each(function(){
		$(this).addClass("col-sm-4 control-label");
		$(this).next("input,select,textarea").addClass("form-control");
		$(this).next("input,select,textarea").wrap("<div class=\"col-sm-7\"></div>");
	})
	$(":input[type='checkbox'],:input[type='radio']").iCheck({
		checkboxClass:"icheckbox_square-green",
		radioClass:"iradio_square-green",
		});
}
/**
 * 弹出提示框
 * @param windowId 	提示框的唯一标识id
 * @param alertTitle 提示框标题
 * @param titleStyle 提示框类型，包括错误、提醒、确认三种
 * @param msgString 提示信息
 * @param callback 回调函数
 * @param isConfirm 是否是确定提示框
 * @param args 回调函数参数
 * @param exception 异常栈信息
 * @return false
 */
function openAlertWindow(windowId, alertTitle, titleStyle, msgString,callback,isConfirm,args,exception) {
    if (windowId == null) {
        windowId = alertWindowId;
    }
    var btns="<button class=\"btn btn-primary\" data-dismiss=\"modal\" aria-hidden=\"true\" id=\"cancelBtn\" ><i class='glyphicon glyphicon-remove'></i>&nbsp;关闭</button>";
    if(isConfirm){
     	btns = 
        "<button type=\"button\" class=\"btn btn-primary\" id='confirmBtn' ><i class='glyphicon glyphicon-ok'></i>&nbsp;确定</button>"+ 
        "<button class=\"btn btn-close\" data-dismiss=\"modal\" aria-hidden=\"true\" id=\"cancelBtn\" ><i class='glyphicon glyphicon-remove'></i>&nbsp;取消</button>" ;
    }
    if ($('#' + windowId).length <= 0) {
        $("#wrapper").append(
        	// id=\"myAlertModalId\"	 .alert-dialog	
            "<div class=\"modal fade\" id=\"" + windowId + "\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n"+
                "    <div class=\"modal-dialog modal-dialog-sm\" 	>\n"+
                "        <div class=\"modal-content\">\n"+
                "            <div id=\"modal-header\" class=\"modal-header "+titleStyle+"\">\n"+
                "                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\n"+
                "                <h3 class=\"modal-title\" id=\"myModalLabel\"></h3>\n"+
                "            </div>\n"+
                "            <div class=\"modal-body alert_info\">\n"+
                "            </div>\n"+
                "            <div class=\"modal-footer\">\n"+
                "            </div>\n"+
                "        </div>\n"+
                "    </div>\n"+
                "</div>"
        );

    }

    $("#"+windowId+" .modal-footer").html(btns);
    if (callback != null){
        $("#"+windowId+" #confirmBtn").click(function(){
        	// fn1()//excute this function fn1,if need params pass it
        	if(!isNull(args)){
        		callback(args);
        	}else{
        		callback(true);
//        		callback(msgString);
        	}
        });
        $("#"+windowId +" #cancelBtn").click(function(){
            callback(false);
        });
    }

    if(!alertTitle){
        alertTitle=msgDefaultTitle;
    }
    $("#"+windowId+" #myModalLabel").html(alertTitle);
    if(titleStyle==null){
        titleStyle=msgTitleStyle;
    }
    $("#"+windowId+" #modal-header").removeClass();
    $("#"+windowId+" #modal-header").addClass("modal-header "+titleStyle);

    if (!isNull(msgString) && (msgString.length != 0)) {
	if(titleStyle=="alert-danger" && !isEmpty(exception)){
	    $("#" + windowId + " .modal-body").html(msgString+"<button id='exce_btn' class='btn_all default_yellow' " +
		"style='float:right;width:30px;height:30px;' title='异常信息' data-container='body' data-toggle='popover'" +
		" data-placement='right' data-content=''><i style='font-size:16px;' class='glyphicon glyphicon-paperclip'></i></button>");
	 
	    //弹出popover 异常
	    $("#exce_btn").hover(function(){
			$("#exce_btn").attr("data-content",exception).popover("show");
			$(".popover").css("width","450px");
			$(".popover").css("z-index",9999);
			$(".popover").css("word-wrap","break-word"); 
	    },function(){
	    	$("#exce_btn").attr("data-content","").popover("hide");
	    });
	}else{
	    $("#" + windowId + " .modal-body").html(msgString);
	}
    }
    /*
    $("#myAlertModalId").draggable({
    	handle: ".modal-header"
    });*/	
    //获取css中的z-index的值
    //设置新的css，后面弹出的提示框总是位于前面的提示框之前
    //var prezIndex = $("#"+windowId).css("z-index");
    $("#"+windowId).css("z-index","3000 !important");
   // $("#"+windowId).css("cssText","z-index:"+(parseInt(prezIndex)+1)+" !important");
    
    $("#" + windowId).modal({
        keyboard: true
    });
    //按钮获取焦点，按回车响应事件
    if(isConfirm){
    	setTimeout("$('.modal-footer #confirmBtn').focus();","1000");
    }else{
    	setTimeout("$('.modal-footer #cancelBtn').focus();","1000");
    }
    return false;
}

/**
 * 弹出对话框
 * @param windowId 对话框的唯一标识id
 * @param alertTitle 对话框标题
 * @param url 请求url
 * @param isRefreshGrid 是否刷新jqGrid表格数据
 * @param width 对话框宽度
 * @param height 对话框高度
 * @return false
 */

function openNewWindow(windowId, alertTitle, url,isRefreshGrid) {
    if (windowId == null) {
        windowId = openWindowId;
    }
    $("#focus").css("display","block");
    if ($('#' + windowId).length <= 0) {
        $("#wrapper").append(
        	//id=\"myModalId\"
        	"<input type='text' id='focus' />" + 
        	"<div class=\"modal fade\" id=\"" + windowId + "\"  role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n"+
                "    <div class=\"modal-dialog modal-dialog-sm\" >\n"+
                "        <div class=\"modal-content\">\n"+
                "            <div id=\"modal-header \" class=\"modal-header\">\n"+
                "                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\n"+
                "                <h3 class=\"modal-title\" id=\"myModalLabel\"></h3>\n"+
                "            </div>\n"+
                "            <div class=\"modal-body \">\n"+
                "            </div>\n"+
                "            <div class=\"modal-footer\">\n"+
                "<button class=\"btn btn-primary\" data-dismiss=\"modal\" aria-hidden=\"true\"><i class='glyphicon glyphicon-remove'></i>&nbsp;关闭</button>"+
                "            </div>\n"+
                "        </div>\n"+
                "    </div>\n"+
                "</div>"
        );
    } else {
        $('#'+windowId +" .modal-footer").html("<button class=\"btn btn-primary\" data-dismiss=\"modal\" aria-hidden=\"true\"><i class='glyphicon glyphicon-remove'></i>&nbsp;关闭</button>");
    }

    if (alertTitle == null) {
        alertTitle = msgDefaultTitle;
    }
    $("#" + windowId + " #myModalLabel").html(alertTitle);

    
    if ($('#'+windowId +" .modal-body").html().length != 0) {
        $('#'+windowId +" .modal-body").empty();
    }
    if (url != null) {
    	suFn = function(data){
			$("#"+windowId+" .modal-body").html(data);
			initForm();
		}
        loadAjaxData(windowId+" .modal-body", url,null, null, suFn);
    }
    $("#" + windowId).modal({
        keyboard: true,				// 设置为true,表示当点击Esc键时模态消失
        backdrop: 'true'			// backdrop类型为Boolean和String;设置backdrop(背景消失/遮盖层消失)为false或者'static'都可以做到，当点击遮盖层时模态不消失
    });
   // $(".modal-dialog").removeAttr("style");
	//实现模态框的拖动
    /*
    $("#myModalId").draggable({
    	handle: ".modal-header"
    });*/    
    $("#focus").focus();
    $("#focus").css("display","none");
    return false;
}


/**
 * 错误提示框
 * @param msgString 错误信息
 * @param exception 异常信息
 * @return false
 */
function showErrorMsg(msgString,exception) {
    showInfoMsg(msgString, "error",null,null,null,exception);
}

/**
 * 警告提示框
 * @param msgString 警告信息
 * @param fn 回调函数
 * @return false
 */
function showWarningMsg(msgString,fn) {
    showInfoMsg(msgString, "warning",fn);
}

/**
 * 确认提示框
 * @param content 提示信息
 * @param callback 回调函数
 * @param args 回调函数参数
 * @return false
 */
function showConfigMsg(content,callback,args) {
    showInfoMsg(content,"confirm", callback,null,args);
}

/**
 * 信息提示框
 * @param msgString 提示信息
 * @param msgType 提示框类型 
 * @param fn 回调函数
 * @param titleStyle 提示框标题
 * @param args 回调函数参数
 * @return false
 */
function showInfoMsg(msgString, msgType, fn,titleStyle,args,exception) {
    var title;
    var isConfirm=false;
    if (msgType == null || "" == msgType) {
        msgType = "info";
        title = msgDefaultTitle;
        titleStyle = msgTitleStyle;
    }
    else if (msgType == "error") {
        title = "出错了";
        titleStyle = "alert-danger";
    } else if (msgType == "warning") {
        title = "警告";
        titleStyle = "alert-warning";
    }
    else if(msgType == "confirm"){
        title = "系统提示";
        titleStyle = "alert-warning";
        isConfirm=true;
    }
    openAlertWindow(null,title,titleStyle, msgString,fn,isConfirm,args,exception);
}

/**
 * 调整元素居中
 * @param divName 元素的唯一标识id 
 */
function adjustDivPostionCenter(divName) {
    var postop = ($(document).height() - $("#" + divName).height()) / 2;
    var posleft = ($(window).width() - $("#" + divName).width()) / 2;
    var loginTop = 120;
    if (postop < loginTop) postop = loginTop;
    $("#" + divName).css({"left": posleft + "px", "top": postop + "px"});
    $("#" + divName).show();
}



/**
 * 表单验证初始化
 * @param conditions 表单验证条件 
 * @param formName 表单id
 * @param settings 自定义配置
 */
function validateInit(conditions, formName,settings) {
    for (var i = 0; i < conditions.length; i++) {
        var cond = conditions[i];
        // 下面两个效果相同 data-validation-engine=rule 和 class = rule 相同，不推荐是class
		// 可嗯呢该会影响css
        $("#" + formName + " [name=" + cond.name + "]").attr("data-validation-engine", cond.rule);
        // $("#bean [name=" + cond.name + "]").addClass(cond.rule);
    }
    var defSettings = {
        	// validationEventTriggers:"blur", //触发的事件
    		// validationEventTriggers:"keyup blur",
        	// inlineValidation: true,//是否即时验证，false为提交表单时验证,默认true
        	
        	scroll:true,    // 屏幕自动滚动到第一个验证不通过的位置
        	focusFirstField:true,    // 验证未通过时，是否给第一个不通过的控件获取焦点
            promptPosition: "bottomRight",
            isOverflown: true,
            overflownDIV: "#winOpenId"
        };
    if(!isNull(settings)){
    	$.extend(defSettings,settings);
    }
    // 注册表单验证事件，会自动根据rule进行校验 
    $("#" + formName).validationEngine('attach', defSettings);
}

/**
 * 验证表单基于 validationEngine机制，，目前比较流行validate插件：https://jqueryvalidation.org/
 * @param formName 表单id
 */
function validateForm(formName) {
	// $("#" + formName).validationEngine('validate'); 等效
    return $("#" + formName).validationEngine('validateform');
}

/**
 * 表单存储
 * 
 * @param url 请求url
 * @param formId 表单id
 * @param callbackFunc 回调函数  无论成功失败都会调用，异常情况下也调用，需要手动排毒按 val是否为空
 * @param opts(
 * 				showLoading  是否显示进度提示，默认显示，false表示不显示
 * 				showSuccessInfo 是否显示成功提示信息，默认显示，false表示不显示
 * 				windowId     关闭对话框的ID
 * 				gridId		 刷新表格的ID
 * 				zTreeId		 刷新树的ID
 * 				successFnc   请求成功后自定义的函数，参数为现有的数据，参数可选  //仅成功才调用
 *				errorFnc	 请求失败后自定义的函数，参数为现有的数据，参数可选 	
 *				参数可以继续扩展		
 *			  )
 * 
 */
function saveAjaxData(url, formId, callbackFunc, opts) {
    // if (formId == null || "" == formId)formId = "bean";
    var defaultOpts = {
	showLoading: true,
	showSuccessInfo: true,
	closeWindow: true,
	refreshGrid: true,
	refreshTree: true,
	successFnc: null,
	errorFnc: null
    };
    opts = $.extend(defaultOpts, opts||{});
    if (formId != null) {
    	try{
    	    if($("#"+formId).length>0){
    		sendData = $("#" + formId).serializeArray();
    	    }
    	}catch(e){
    	    sendData = formId;
    	}
    }else{
    	sendData = null;
    }
    $.ajax({
        type: 'POST',
        url: url,
        data: sendData,
        dataType: 'json',
        success: function (data) {
	    if(opts.showLoading === true){
	    	$("body").waiting("hide");
	    }
	    var isCallBackExec = false;
	    if(opts){
    		isCallBackExec = opts.showSuccessInfo;
	    }
            if (data.success) {
                if (opts.showSuccessInfo === true) {
                    showInfoMsg(data.msg, null, callbackFunc);
                }
                if(!isNull(opts.successFnc)){
                	opts.successFnc(data.val);
                }
                if (opts.closeWindow) {
                    closeWindow();
                }
                if (opts.refreshGrid) {
                	refreshGrid()
                }
                if (opts.refreshTree) {
                	refreshTree()
                }

            } else {
            	if(!isNull(opts.errorFnc)){
            		opts.errorFnc(data.val);
            	}
                showErrorMsg(data.msg,data.val);
            }
            if (callbackFunc && !isCallBackExec) {
                if ($.isFunction(callbackFunc)) {
                    callbackFunc(data.val);
                } else {
                    eval(callbackFunc);
                }
            }
        },
        error: function (xmlR, status, e) {
            if (callbackFunc && !isCallBackExec) {
                if ($.isFunction(callbackFunc)) {
                    callbackFunc(data.val);
                } else {
                    eval(callbackFunc);
                }
            }
            $("body").waiting("hide");
            showErrorMsg(xmlR.responseText,e);
           // openNewWindow("errorWindow", "错误", null, false, 600, 400);
            // $("#errorWindow").panel("body").html("[" + e + "]" +
			// xmlR.responseText);
        },
        beforeSend: function(){
            if(opts.showLoading === true){
        	$("body").waiting();
            }
        }
    });
}


/**
 * 上传文件
 * @param url 请求url
 * @param formId 表单id
 * @param callbackFunc 回调函数
 * @param opts(
 * 				showLoading  是否显示进度提示，默认显示，false表示不显示
 * 				showSuccessInfo 是否显示成功提示信息，默认显示，false表示不显示
 * 				windowId     关闭对话框的ID
 * 				gridId		 刷新表格的ID
 * 				zTreeId		 刷新树的ID
 * 				successFnc   请求成功后自定义的函数，参数为现有的数据，参数可选
 *				errorFnc	 请求失败后自定义的函数，参数为现有的数据，参数可选 	
 *				upFile      页面原始ID，不指定默认是upFile ，可以指定
 *				参数可以继续扩展		
 *			  )
 * 
 */
function saveAjaxFileUpload(url,formId, callbackFunc, opts){
	var defaultOpts = {
			showLoading: true,
			showSuccessInfo: true,
			closeWindow: true,
			refreshGrid: true,
			refreshTree: true,
			successFnc: null,
			errorFnc: null
	};
	opts = $.extend(defaultOpts, opts||{});
	var sendData = "";
    if (formId != null) {
    	try{
    		if($("#"+formId).length>0){
    			sendData = $("#" + formId).serializeArray();
    		}
    	}catch(e){
    		sendData = formId;
    	}
    }else{
    	sendData = null;
    }
	var eleId = 'upFile';
    if(opts.upFile){
    	eleId = opts.upFile;
    }
	$.ajaxFileUpload({
		url: url,
		secureuri:false, 
		fileElementId: eleId,
		data: sendData,
		dataType:'json', 
		success:function(data){
			
			if(opts.showLoading === true){
	    		$("body").waiting("hide");
	    	}
			var isCallBackExec = false;
			if(opts){
				isCallBackExec = opts.showSuccessInfo;
			}
	        if (data.success) {
	            if (opts.showSuccessInfo === true) {
	                showInfoMsg(data.msg, null, callbackFunc);
	            }
	            if(!isNull(opts.successFnc)){
	            	opts.successFnc(data.val);
	            }
	            if (opts.closeWindow) {
	                closeWindow();
	            }
	            if (opts.refreshGrid) {
	            	refreshGrid()
	            }
	            if (opts.refreshTree) {
	            	refreshTree()
	            }
	
	        } else {
	        	if(!isNull(opts.errorFnc)){
	        		opts.errorFnc(data.val);
	        	}
	            showErrorMsg(data.msg,data.val);
	        }
	        if (callbackFunc && !isCallBackExec) {
	            if ($.isFunction(callbackFunc)) {
	                callbackFunc(data.val);
	            } else {
	                eval(callbackFunc);
	            }
	        }
		},
		error:function(reponesText,status,e){
			$("body").waiting("hide");
			showErrorMsg("上传文件失败",e);
		},
        beforeSend: function(){
        	if(opts.showLoading === true){
        		$("body").waiting();
        	}
        }
	});
};


/**
 * 关闭弹出框
 * @param windowId 弹出框的id 
 */
function closeWindow(windowId) {
    if (windowId == null) windowId = openWindowId;
    if ($('#' + windowId).length <= 0) return;
    $('#' + windowId).modal('hide');
}

/**
 * 关闭提示框
 */
function closeAlertWindow(){
	$("#myAlertModalId").parent().modal('hide');
}

/**
 * 添加图标名称，主要是用于设备树的图标更改
 * @param icons 图标名称
 * @return string
 */
function addIcons(icons) {
    return rootIcon + "," + icons;
}

/**
 * 调整jqGrid表格宽度
 */
//function resizeGrid() {
//    setTimeout(function () {
//        $("#listGrid").setGridWidth($("#dataInfo").width() - 5);
//    }, 500);
//}

/**
 * 设置layout的大小
 */
function initSize() {
    $("#layout").height($("html").height() - $(".navbar").outerHeight() - $("#breadcrumbs").outerHeight()-84);
}

/**
 * 禁止按钮再次点击
 * @param btn 按钮
 * @param showName 显示名称
 */
function disableBtn(btn, showName) {
    if (showName == null) showName = "稍候.";
    btn.value = showName;
    btn.disabled = true;
}

/**
 * 刷新jqGrid表格
 * @param gridId 表格id
 */
function refreshGrid(gridId) {
    if (gridId == null)gridId = listGridId;
    if ($('#' + gridId).length <= 0) {
	    if($("#listGrid1").length<=0)return;
		$("#listGrid1").trigger("reloadGrid");
    }else{
    	$("#" + gridId).trigger("reloadGrid");
    }    
}

/**
 * 删除列表记录
 * @param url 请求url
 * @param msg 删除前提示信息
 * @param opts 自定义配置
 */
function doGridDelete(url, msg, opts) {
    if (msg == null) msg = "您确定要删除此记录吗?";
    showConfigMsg( msg, function (r) {
        if (r) {
            saveAjaxData(url, null, null, opts);
        }
    });
}

/**
 * 清空列表记录
 * @param url 请求url
 * @param msg 删除前提示信息
 * @param opts 自定义配置
 */
function doGridClear(url, msg, opts) {
    if (msg == null) msg = "您确定要清空此配置吗?";
    showConfigMsg( msg, function (r) {
        if (r) {
            saveAjaxData(url, null, null, opts);
        }
    });
}



/**
 * 选择日历
 * @param objName 对象
 * @param dateType 日历格式
 */
function calendar(objName, dateType) {
    var json = "";
    if (dateType != null) {
        if ('all' == dateType) {
            json += "dateFmt:'yyyy-MM-dd HH:mm:ss'";
        } else if ('datetime' == dateType) {
            json += "dateFmt:'yyyy-MM-dd HH:mm'";
        } else {
            json += "dateFmt:'yyyy-MM-dd'";
        }
    }
    if (objName != null && objName != "") {
        if (json != "") {
            json = "el:'" + objName + "'," + json;
        } else {
            json = "el:'" + objName + "'";
        }
    }
    if (json != "") json = "{" + json + "}";
    WdatePicker(eval("(" + json + ")"));
}

/**
 * 将表单数据，序列化为数组格式
 * @param formId 表单id
 * @return array
 */
function serializerToArray(formId){
	return $("#" + formId).serializeArray();
}

/**
 * ajax 导出文件 ctx 下载url前缀 ctx used ctxpath can not use 
 * @param url 请求url
 * @param ctx 工程前缀路径
 * @param callbackFunc 回调函数
 */
function downAjaxFile(url1,ctx,callbackFunc) {
    var genURL = url1;
    if(url1==null){
        return;
     }
    if(callbackFunc ==null){
    	callbackFunc = function(data){
    	    if(data.success){
		window.open(ctx+"/componentData/downFile.do?path="+data.val);// 统一下载
    	    }else{
		showErrorMsg(data.msg,data.val);
    	    }
	};
    }
    $.ajax({
	dataType : "json",
	url : genURL,
	success : function(data) {
	    $("body").waiting("hide");
	    closeWindow();
	    callbackFunc(data);
	},
	error : function(reponseText,status,e){
	    $("body").waiting("hide");
	    closeWindow();
	    showErrorMsg("生成文件失败",e);
	},
	beforeSend: $("body").waiting()
    });
}

/**
 * 获取浏览器可视窗口大小
 */
function pageSize() {
    var winW, winH; 
    if(window.innerHeight) {// all except IE
         winW = window.innerWidth; 
         winH = window.innerHeight; 
    } else if (document.documentElement && document.documentElement.clientHeight) {// IE 6
																					// Strict
																					// Mode
         winW = document.documentElement.clientWidth; 
         winH = document.documentElement.clientHeight; 
    } else if (document.body) { // other
         winW = document.body.clientWidth; 
         winH = document.body.clientHeight; 
    }  // for small pages with total size less then the viewport
    return {WinW:winW, WinH:winH}; 
}

/**
 * highchart简单封装
 * @param ele 存放highchart的容器id
 * @param setting 配置
 * @param options 选项
 * @return highchart图表
 */
function initHighChart(ele,setting,options){
	var defaultOptions = {
		colors:['#F7A35C', '#95CEFF','#CCFFCC','#90ED7D','#CCCC66','#00CC00', '#CCFF99', '#CCCC33'],
		chart: {
            type: null
        },
        title: {
            text: null
        },
        xAxis: {
        	title: {
        		text: null
        	},
            categories: null
        },
        yAxis: {
            title: {
                text: null
            }
        },
        tooltip: {
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        credits: {
        	enabled: false
        },
        series: null
	};
	var defaultSetting = {
		type   : null,
		title  : null,
		xtitle : null,
		ytitle : null,
		xcategories : null,
		series : null
	};
	defaultOptions = $.extend(defaultOptions,options || {});
	defaultSetting = $.extend(defaultSetting,setting || {});
	if(defaultSetting.type != null){
		defaultOptions.chart.type = defaultSetting.type;
	}
	if(defaultSetting.title != null){
		defaultOptions.title.text = defaultSetting.title;
	}
	if(defaultSetting.ytitle != null){
		defaultOptions.yAxis.title.text = defaultSetting.ytitle;
	}
	if(defaultSetting.xcategories !=null){
		defaultOptions.xAxis.categories = defaultSetting.xcategories;
	}
	if(defaultSetting.series != null){
		defaultOptions.series = defaultSetting.series;
	}
	ele.highcharts(defaultOptions);
}



/**
 * 给文本框赋值,并改变浏览文件按钮变成导入按钮
 * 必须指定toUpload方法
 * @param value 改变后按钮的值
 */
function upFileValue(value) {
	var upFileValue = $("#upFile").val();
	$("#textUpFile").val(upFileValue);
	$("#browseFile").remove();
	$("#btnFile").append("<button class='btn btn-sm btn-primary' onclick='toUpload();' type='button'><i class='glyphicon glyphicon-upload'></i>&nbsp;"+value+"</button>");
}

/**
 * 点击浏览文件，打开文件选择框,模拟点击
 */
function upFileValue2() {
	document.getElementById('upFile').click();
}

/**
 * 显示提示进度条
 * @param objName 显示进度条的容器id
 */
function showProcessInfo(objName){
	if(isNull(objName)){
		$("body").waiting();
	}else{
		$("#"+objName).waiting();
	}
}

/**
 * 隐藏提示进度条
 * @param objName 进度条的容器id
 */
function hideProcessInfo(objName){
	if(isNull(objName)){
		$("body").waiting("hide");
	}else{
		$("#"+objName).waiting("hide");
	}
}

/**
 * 键入enter键，进行查询,需要初始化
 * @param e 键盘响应
 * @return
 */
function queryEnterPress(e){
    var e=e||window.event;
    if (e.keyCode == 13) {
    	jQuery('#queryBtn').trigger('click');
    }
}

/**
 * 键入enter键，进行保存
 * @param e 键盘响应
 * @return
 */
function formEnterPress(e){
    var e=e||window.event;
    if (e.keyCode == 13) {
    	jQuery('#confirmBtn').trigger('click');
    }
}



/**
 * 验证输入日期是否合法
 * @author qinyeji
 * @param start 开始时间 ：选择器对象
 * @param end 结束时间：选择器对象
 * @param type 时间类型 start or end
 */
function verificationTime(start,end) {
    var startTime = start.val();
    var endTime = end.val();
    //粗略转换时间字符串为天数
    var sArray = startTime.split("-");
    var start2Day = sArray[0] + sArray[1] + sArray[2];    
    var eArray = endTime.split("-");
    var end2Day = eArray[0] + eArray[1] + eArray[2];
    //获取当前时间转换为字符串天数
    var d = new Date();
    var vYear = d.getFullYear();
    var vMon = d.getMonth() + 1;
    var vDay = d.getDate();
    if (vMon < 10) {
	vMon = "0" + vMon;
    }
    var currentTime = vYear + "" + vMon + "" + vDay;
    
    var startSpace = currentTime - parseInt(start2Day);
    var endSpace = currentTime - parseInt(end2Day);
    
    if (startTime!=null && startTime!="" && startSpace < 0) {
	showInfoMsg("开始时间不能大于当前时间");
	start.val("");
    }
    if (endTime!=null && endTime!="" && endSpace < 0) {
	showInfoMsg("结束时间不能大于当前时间");
	end.val("");
    }
    var space = parseInt(end2Day) - parseInt(start2Day);
    if (space < 0) {
	showInfoMsg("结束时间不能小于开始时间");
	return;
    }        
}


/**
 * 判断是否为null获取undefined
 * @param object 判断数据
 * @return Boolean
 */
function isNull(object){
	return object === null || object == undefined;
}

function isEmpty(object){
	return  isNull(object)||object =="";
}
