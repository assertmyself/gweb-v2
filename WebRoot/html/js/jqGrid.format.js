/** -------- formatter formatter--------- */
/**
 * jqGrid自定义格式函数,设备类型格式化
 * @param cellvalue 当前cell的值
 * @param options 该cell的options设置，包括{rowId, colModel,pos,gid}
 * @param rowObject 当前cell所在row的值，如{ id=1, name="name1", price=123.1, ...}
 * @return string
 */
function devFormFormat(cellvalue, options, rowObject) {
	if(cellvalue=="0"){
		return "所有";
	}
	if(cellvalue=="1"){
		return "网关";
	}
	if(cellvalue=="2"){
		return "AP";
	}
	return "其它";
}

/**
 * jqGrid自定义格式函数,开关格式化
 */
function switchFormat(cellvalue, options, rowObject) {
	if(cellvalue=="0"){
		return "关闭";
	}
	if(cellvalue=="1"){
		return "开启";
	}
	return "其他";
}

/**
 * jqGrid自定义格式函数,优先级格式化
 */
function priorityFormat(cellvalue, options, rowObject) {
	if(cellvalue=="0"){
		return "低";
	}
	if(cellvalue=="1"){
		return "中";
	}
	if(cellvalue=="2"){
		return "高";
	}
	return "其它";
}

/**
 * jqGrid自定义格式函数,根据型号ID 获得型号名称
 */
function sysModelFormat(cellvalue, options, rowObject){
	if(cellvalue=="9001002"){
		return "WA2020";
	}else if(cellvalue=="9001002"){
		return "TA2020SP";
	}else if(cellvalue=="9002001"){
		return "TA2025";
	}else if(cellvalue=="9002001"){
		return "CA2025";
	}else if(cellvalue=="10001001"){
		return "OA2020";
	}else if(cellvalue=="10001001"){
		return "OA2020L";
	}else if(cellvalue=="10001002"){
		return "OA2025";
	}else if(cellvalue=="7004001"){// 网关
		return "CGW500";
	}else if(cellvalue=="7004002"){
		return "CGW500U";
	}else if(cellvalue=="7003001"){
		return "CGW1200";
	}else if(cellvalue=="7003002"){
		return "CGW1200H";
	}else if(cellvalue=="7001002"){
		return "CGW2000";
	}else if(cellvalue=="7001003"){
		return "CGW3000";
	}else if(cellvalue=="7001004"){
		return "CGW3000T";
	}else if(cellvalue=="1200100"){// 网监
		return "DW-CGW1200";
	}else if(cellvalue=="1200200"){
		return "DW-LA2000";
	}else if(cellvalue=="1200300"){
		return "DW-LA1000";
	}else if(cellvalue=="1200400"){
		return "DW-LFC25";
	}else if(cellvalue=="9002003"){// 齐邦定制
		return "CA1020C";
	}else if(cellvalue=="9002003"){
		return "CA2020C";
	}else if(cellvalue=="9002003"){
		return "CA2025C";
	}else if(cellvalue=="9002003"){
		return "TA2020C";
	}else if(cellvalue=="9002003"){
		return "CGW1000";
	}else{
		return "";
	}
	
}

/**
 * jqGrid自定义格式函数,状态格式化
 */
function stateFormat(cellvalue, options, rowObject) {
	if(cellvalue=="1"){
		// $(this).css({text-align:"center",color:"green"});
		return "在线";
	}
	if(cellvalue=="2"){
		// $(this).css({text-align:"center",color:"red"});
		return "离线";
	}
	if(cellvalue=="0"){
		// $(this).css({text-align:"center",color:"red"});
		return "离线";
	}
	return "未知";
}

/**
 * 软件版本，文件类型显示
 */
function fileTypeFormat(cellvalue, options, rowObject) {
	if(rowObject['fileType'] =="4"||rowObject['fileType'] =="5"||rowObject['fileType'] =="99"||rowObject['fileType'] =="ver"){
		return "版本";
	}
	if(rowObject['fileType'] =="sh"){
		return "脚本";
	}
	if(rowObject['fileType'] =="config"){
		return "配置文件";
	}
	return "未知";
}

/**
 * jqGrid自定义格式函数,布尔值格式化
 */
function booleanFormat(cellvalue, options, rowObject) {
    return (cellvalue == "true" ||cellvalue == "1")? "是" : "否";
}

/**
 * jqGrid自定义格式函数,状态格式
 */
function jobStateFormat(cellvalue, options, rowObject) {
	if(cellvalue=="1"){
		return "等待";
	}
	if(cellvalue=="2"){
		return "运行";
	}
	if(cellvalue=="3"){
		return "挂起";
	}
	if(cellvalue=="4"){
		return "失效";
	}
	
}

/**
 * jqGrid自定义格式函数,状态格式
 */
function jobLevelFormat(cellvalue, options, rowObject) {
	if(cellvalue=="1"){
		return "全局";
	}
	if(cellvalue=="2"){
		return "区域";
	}
	if(cellvalue=="3"){
		return "设备";
	}
	
}

/**
 * jqGrid自定义格式函数,状态格式
 */
function validFormat(cellvalue, options, rowObject) {
    return cellvalue == "true" ? "有效" : "无效";
}

/**
 * jqGrid自定义格式函数
 */
function userType(cellvalue, options, rowObject) {
	if(cellvalue=="0"){
		return "none";
	}
	if(cellvalue=="1"){
		return "userpasswd";
	}
	if(cellvalue=="2"){
		return "coupon";
	}
	if(cellvalue=="3"){
		return "fixedpasswd";
	}
	if(cellvalue=="4"){
		return "phone";
	}
	if(cellvalue=="5"){
		return "qq";
	}
	if(cellvalue=="6"){
		return "weibo";
	}
	if(cellvalue=="7"){
		return "nopasswd";
	}
	if(cellvalue=="8"){
		return "weixin";
	}
	if(cellvalue=="9"){
		return "alipay";
	}
	return "other";
}

/**
 * jqGrid自定义格式函数，半分比格式
 */
function apppercent(cellvalue, options, rowObject) {
	return cellvalue+"%";
}

/**
 * jqGrid自定义格式函数，安全模式格式函数
 */
function securityMode(cellvalue, options, rowObject) {
	// none(0),wep(1),wpa-psk(2),wpa2-psk(3),psk-mixed(4),wpa(5),wpa2(6),wpa-wpa2-mixed(7)

	if(cellvalue=="0"){
		return "none";
	}
	if(cellvalue=="1"){
		return "open";
	}
	if(cellvalue=="2"){
		return "shared";
	}
	if(cellvalue=="3"){
		return "wpa-psk";
	}
	if(cellvalue=="4"){
		return "wpa2-psk";
	}
	if(cellvalue=="5"){
		return "wpapsk-wap2psk";
	}
	if(cellvalue=="6"){
		return "wpa";
	}
	if(cellvalue=="7"){
		return "wpa2";
	}
	if(cellvalue=="8"){
		return "wpa-wap2";
	}
	return "other";
}

/**
 * jqGrid自定义格式函数，认证类型格式函数
 */
function authTypeFormat(cellvalue, options, rowObject){
	if(cellvalue=="1"){
		return "Disabled";
	}
	if(cellvalue=="2"){
		return "Web";
	}
}

/**
 * jqGrid自定义格式函数，安全模式格式函数
 */
function securityModeFormat(cellvalue, options, rowObject){
	if(cellvalue=="1"){
		return "Open";
	}
	if(cellvalue=="2"){
		return "Shared";
	}
	if(cellvalue=="3"){
		return "Wpa-psk";
	}
	if(cellvalue=="4"){
		return "Wpa2-psk";
	}
	if(cellvalue=="5"){
		return "Wpapsk-wpa2psk";
	}
	if(cellvalue=="6"){
		return "Wpa";
	}
	if(cellvalue=="7"){
		return "Wpa2";
	}
	if(cellvalue=="8"){
		return "wpa-wap2";
	}
}

/**
 * jqGrid自定义格式函数，加密类型格式函数
 */
function encryptTypeFormat(cellvalue, options, rowObject){
	if(cellvalue=="0"){
		return "None";
	}
	if(cellvalue=="1"){
		return "Tkip";
	}
	if(cellvalue=="2"){
		return "aes";
	}
	if(cellvalue=="3"){
		return "Auto";
	}
}

/**
 * jqGrid自定义格式函数，wepKey格式函数
 */
function wepKeyFormat(cellvalue, options, rowObject){
	if(cellvalue=="0"){
		return "wep-64bit-16hex";
	}
	if(cellvalue=="1"){
		return "wep-64bit-ascii";
	}
	if(cellvalue=="2"){
		return "wep-128bit-16hex";
	}
	if(cellvalue=="3"){
		return "wep-128bit-ascii";
	}
}

/**
 * jqGrid自定义格式函数,是否可用
 */
function isEableFormat(cellvalue, options, rowObject){
	if(cellvalue=="0"){
		return "Disabled";
	}
	if(cellvalue=="1"){
		return "Enable";
	}
}

/**
 * jqGrid自定义格式函数，是否广播
 */
function isBroadcastFormat(cellvalue, options, rowObject){
	if(cellvalue=="0"){
		return "On";
	}
	if(cellvalue=="1"){
		return "Off";
	}
}

/**
 * jqGrid自定义格式函数，信道模式
 */
function channelBGNModeFormat(cellvalue, options, rowObject){
	if(cellvalue=="2"){
		return "b";
	}
	if(cellvalue=="3"){
		return "g";
	}
	if(cellvalue=="6"){
		return "bg";
	}
	if(cellvalue=="16"){
		return "n";
	}
	if(cellvalue=="20"){
		return "g-gn";
	}
	if(cellvalue=="22"){
		return "bgn";
	}
}

/**
 * jqGrid自定义格式函数，信道状态
 */
function channelStateFormat(cellvalue, options, rowObject){
	if(cellvalue=="0"){
		return "Static"
	}
	if(cellvalue=="1"){
		return "Auto";
	}
}

/**
 * jqGrid自定义格式函数
 */
function bwModeFormat(cellvalue, options, rowObject){
	if(cellvalue=="0"){
		return "bw20MHz"
	}
	if(cellvalue=="1"){
		return "Auto";
	}
	if(cellvalue=="2"){
		return "bw40Hz";
	}
}

/**
 * jqGrid自定义格式函数
 */
function channelANModeFormat(cellvalue, options, rowObject){
	if(cellvalue=="1"){
		return "dot11a"
	}
	if(cellvalue=="8"){
		return "dot11n";
	}
	if(cellvalue=="9"){
		return "dot11a-an";
	}
}

/**
 * jqGrid自定义格式函数
 */
function ipModeFormat(cellvalue, options, rowObject){
	if(cellvalue=="1"){
		return "Static";
	}
	if(cellvalue=="2"){
		return "DHCP";
	}
}

/**
 * for grid netsiteType
 * 审计模块-场所基本信息-服务类型
 */
function netsiteTypeFormat(cellvalue, options, rowObject){
    if(cellvalue=="1"){
	return "旅店宾馆类(住宿服务场所)";
    }else if(cellvalue=="2"){
	return "图书馆阅览室";
    }else if(cellvalue=="3"){
	return "电脑培训中心类";
    }else if(cellvalue=="4"){
	return "娱乐休闲场所类";
    }else if(cellvalue=="5"){
	return "交通枢纽";
    }else if(cellvalue=="6"){
	return "公共交通工具";
    }else if(cellvalue=="7"){
	return "餐饮服务场所";
    }else if(cellvalue=="8"){
	return "金融服务场所";
    }else if(cellvalue=="A"){
	return "购物场所";
    }else if(cellvalue=="B"){
	return "公共服务场所";
    }else if(cellvalue=="C"){
	return "文化服务场所";
    }else if(cellvalue=="D"){
	return "公共休闲场所";
    }else{
	return "其他";
    }
}

/**
 * 审计模块-场所基本信息-状态
 */
function statusFormat(cellvalue,options,rowObject){
    if(cellvalue == "1"){
	return "正常";
    }else if(cellvalue == "2"){
	return "维护";
    }else if(cellvalue == "3"){
	return "注销";
    }
}

/**
* jqGrid自定义格式函数,告警等级格式
*/
function levelFormat(cellvalue,options,rowObject){
    if(cellvalue == "1"){
	return "致命告警";
    } else if(cellvalue == "2"){
	return "严重告警";
    } else if(cellvalue == "3"){
	return "警告告警";
    } else if(cellvalue == "4"){
	return "次要告警";
    }else{
	return "未知告警";
    }
}

/**
* jqGrid自定义格式函数:是否邮件通知
*/
function mailFormat(cellvalue,options,rowObject){
	if(cellvalue == "false"){
		return "否";
	}
	if(cellvalue == "true"){
		return "是";
	}
}

/**
* jqGrid自定义格式函数,时间格式函数
*/
function dateFormat(cellvalue,options,rowObject){
	var date = new Date(cellvalue);
	var timestamp = date.getTime()-6*60*60*1000;
	date = new Date(timestamp);
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	var day = date.getDate();
	if(month < 10){
		month = "0"+month;
	}
	if(day < 10){
		day = "0"+day;
	}
	return year+"-"+month+"-"+day+" "+date.toLocaleTimeString();
}

/**
* jqGrid自定义格式函数：关注功能
*/
function attentionFormat(cellvalue,options,rowObject) {
	var cell = rowObject.id;
	if(rowObject['attentionKey'] ==1){
		cell = '<span class="badge badge-warning">'+options.rowId+'</span>';
	}else if(rowObject['attentionKey'] ==2){
		cell = '<span class="badge badge-important">'+options.rowId+'</span>';
	}else{
		cell = '<span class="badge">'+options.rowId+'</span>';
	}
	// data-toggle="tab" data-target="#foo" 
	return '<a data-toggle="tab" data-action="open"  onclick="doAttention(' + rowObject.id + ')">'+cell+'</a>';
}


/**
 * 设备列表中热点名称格式化
 */
function apLinkedFormat(cellValue,options,rowObject){
	return "<a href=\"javascript:void(0);\" id=\"jqgrid_hover\" onclick=\"doView('" + rowObject.id + "')\">"+cellValue+"</a>"
}

/** ---- cellAttr ---- */
/**
 * 为jqGrid单元格添加属性，如果单元格值为"在线"，则显示为"绿色"，如果单元格值为"离线"，则显示为"红色"
 */
function stateCellAttr(rowId, val, rawObject) {
	 if (val == "在线") {
		 //text-align:center;color:red;font-weight:bold
	     return "style='color:green;font-weight:bold'";
	 }
	 if (val == "离线") {
         return "style='color:red;font-weight:bold'";
     }
}