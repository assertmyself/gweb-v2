//给 按钮添加点击事件
;(function($) {
$.extend($.fn, {
	tabInit: function(ele) {//初始化
		var url = $(ele).attr('rel');
		var jsevent=$(ele).attr('jsevent');
		if(url!=""&&url!='undefined'&&url!= undefined&&jsevent!="open"&&url!= '#'){
			url="../"+url;
			var tabName = $(ele).attr('tabName')!=null?$(ele).attr('tabName'):$(ele).html();
			var isNew=true;
			$(".J_menuTab").each(function(){
				if ($(this).hasClass("active")) {
					$(this).removeClass("active");
				}
				if($(this).attr("rel")==url){
					$(this).addClass("active");
					isNew=false;
				}
			})
			if(isNew){
				var tab='<a href="javascript:;" rel="'+url+'" class="active J_menuTab">'+tabName+' <i class="fa fa-times-circle"></i></a>';
				$(".page-tabs-content").append(tab);
			}
			//是否超出显示范围
			_outView($(".J_menuTab.active"));
		}
	}

});
	/**
	 * 当前tab是否超过显示范围，超出了调整显示区域
	 * @param tab 当前tab
	 * @return
	 */
	
	
	function _outView(tab){
	    var lastLength = _tabLength($(tab).prevAll());//当前标签页之前的 标签页总长 o
	    var beforLength = _tabLength($(tab).nextAll());//当前标签页之后的 标签页总长 q
	    var otherlength= _tabLength($(".content-tabs").children().not(".J_menuTabs"));//其他大小 l
	    var viewLength = $(".content-tabs").outerWidth(true) - otherlength;//显示区域大小 k
	    var marLeft=0;//标签页向左偏移量
	    if ($(".page-tabs-content").outerWidth() < viewLength) {
	    	marLeft = 0
	    }else{
	    	if (beforLength <= (viewLength - $(tab).outerWidth(true) - $(tab).next().outerWidth(true))) {
	            if ((viewLength - $(tab).next().outerWidth(true)) > beforLength) {
	            	marLeft= lastLength;
	                var m = tab;
	                while ((marLeft - $(m).outerWidth()) > ($(".page-tabs-content").outerWidth() - viewLength)) {
	                	marLeft -= $(m).prev().outerWidth();
	                    m = $(m).prev()
	                }
	            }
	        } else {
	            if (lastLength > (viewLength- $(tab).outerWidth(true) - $(tab).prev().outerWidth(true))) {
	            	marLeft= lastLength - $(tab).prev().outerWidth(true)
	            }
	        }
	    }
		//偏移动画
		$(".page-tabs-content").animate({
	        marginLeft: 0 - marLeft + "px"
	    },
	    "fast")
	}
	/**
	 * 元素的宽度
	 * @param tab 元素
	 * @return
	 */
	function _tabLength(tab){
		 var wid = 0;
	     $(tab).each(function() {
	    	 wid += $(this).outerWidth(true)
	     });
	     return wid;
	}
	/**
	 * 添加tab 页表签
	 * @return
	 */
//	function tabInit(){
//	    var url = $(this).attr('rel');
//	    var jsevent=$(this).attr('jsevent');
//	    if(url!=""&&url!='undefined'&&url!= undefined&&jsevent!="open"&&url!= '#'){
//	    	url="../"+url;
//	    	var tabName = $(this).html();
//	    	var isNew=true;
//	    	$(".J_menuTab").each(function(){
//	    		if ($(this).hasClass("active")) {
//	    			$(this).removeClass("active");
//	    		}
//	    		if($(this).html().lastIndexOf(tabName)>=0){
//	    			$(this).addClass("active");
//	    			isNew=false;
//	    		}
//	    	})
//	    	if(isNew){
//	    		var tab='<a href="javascript:;" rel="'+url+'" class="active J_menuTab">'+tabName+' <i class="fa fa-times-circle"></i></a>';
//	    		$(".page-tabs-content").append(tab);
//	    	}
//	    	//是否超出显示范围
//	    	_outView($(".J_menuTab.active"));
//		}
//	}
	/**
	 * 点击tab 页显示对应的页面内容
	 * @param obj 被点击的tab 
	 * @return
	 */
	function tabFlash(){
		var obj=$(this);
		var url='';
		if(!obj.hasClass("active")){
			$(".J_menuTab").each(function(){
				if ($(this).hasClass("active")) {
					$(this).removeClass("active");
				}
			})
			$(obj).addClass("active");
		}
		url=obj.attr('rel');
		if(url!=''){
			loadMainPage(url);//依赖 basic.js；该公共js实现 将div动态加载的 main-content
		}
	}
	/**
	 * 向左滚动
	 * @return
	 */
	function rolLeft(){
		var marLeft = Math.abs(parseInt($(".page-tabs-content").css("margin-left")));//左侧收缩 的长度 
		var otherlength= _tabLength($(".content-tabs").children().not(".J_menuTabs"));//其他大小 
		var viewLength = $(".content-tabs").outerWidth(true) - otherlength;//显示区域大小 
		var last= $(".J_menuTab:last").outerWidth(true);
		if ($(".page-tabs-content").outerWidth()< viewLength) {
			return;
		}else{
			var tab = $(".J_menuTab:first");//当前计算的tab 
			//将 tab 标签设置为当前显示的第一个标签
			var nowLength=0;
			marLeft=0;//将 marginleft 变为0
			while(nowLength<marLeft){
				nowLength=$(tab).outerWidth(true);
				tab=$(tab).next();
			}
			while ((nowLength + $(tab).outerWidth(true)) < (viewLength) && tab.length > 0) {
				nowLength += $(tab).outerWidth(true);
				tab = $(tab).next();
			}
			marLeft = _tabLength($(tab).prevAll());
		}
		if(marLeft>0){
			//偏移动画
			$(".page-tabs-content").animate({
				marginLeft: 0 - marLeft + "px"
			},
			"fast");
		}
	}
	/**
	 * 向右滚动
	 * @return
	 */
	function rolRight(){	
		var marLeft = Math.abs(parseInt($(".page-tabs-content").css("margin-left")));//左侧收缩 的长度 
		var otherlength= _tabLength($(".content-tabs").children().not(".J_menuTabs"));//其他大小 
		var viewLength = $(".content-tabs").outerWidth(true) - otherlength;//显示区域大小 
		var last= $(".J_menuTab:last").outerWidth(true);
		if ($(".page-tabs-content").outerWidth()< viewLength) {
			return;
		}else{
			var tab = $(".J_menuTab:first");//当前计算的tab 
			//将 tab 标签设置为当前显示的第一个标签
			var nowLength=0;
			marLeft=0;//将 marginleft 变为0
			while(nowLength<marLeft){
				nowLength=$(tab).outerWidth(true);
				tab=$(tab).next();
			}
			while ((nowLength + $(tab).outerWidth(true)) < (viewLength) && tab.length > 0) {
				nowLength += $(tab).outerWidth(true);
				tab = $(tab).prev();
			}
			marLeft = _tabLength($(tab).prevAll());
		}
		//偏移动画
		$(".page-tabs-content").animate({
			marginLeft: 0 - marLeft + "px"
		},
		"fast");
	}


	/**
	 * 关闭单个tab页
	 * @param obj  tab页
	 * @param type true:关闭当前 false:关闭其他
	 * @return
	 */
	function tabClose(event){
		if($(this).parents(".J_menuTab").hasClass("active")){
			if($(this).parents(".J_menuTab").next(".J_menuTab").size()){
				$(this).parents(".J_menuTab").next(".J_menuTab").click();
			}else if($(this).parents(".J_menuTab").prev(".J_menuTab").size()){
				$(this).parents(".J_menuTab").prev(".J_menuTab").click();
			}
		}
		//阻止事件冒泡
		 if(event.target==this){
			 event.stopPropagation();
		 }
		$(this).parents(".J_menuTab").remove();
		//调整 标签显示范围
		_outView($(".J_menuTab.active"));
	}
	/**
	 * 关闭所有
	 * @return
	 */
	function clossAll(){
	    $(".page-tabs-content").children(".J_menuTab").not(":first").each(function() {
	        $(this).remove();//移除所有
	    });
		$(".J_menuTab:first").click();//首页点击 
	    $(".page-tabs-content").css("margin-left", "0");
	}
	/**
	 * 关闭其他
	 * @return
	 */
	function closeOther(){
		$(".page-tabs-content").children(".J_menuTab").not(":first").not(".active").each(function() {
	        $(this).remove();//移除所有
	    });
	    $(".page-tabs-content").css("margin-left", "0");
	}
	/**
	 * 定位当前区域
	 * @return
	 */
	function showNow(){
		_outView($(".J_menuTab.active"));//判断当前区域是否超出显示范围。并调整显示区域。
	}
	$(function (){
		$(".J_menuTabs").on("click", ".J_menuTab i", tabClose);
		$(".J_menuTabs").on("click", ".J_menuTab", tabFlash);
		$(".J_tabLeft").on("click", rolLeft);
		$(".J_tabRight").on("click", rolRight);
		$(".J_tabCloseAll").on("click",clossAll);//关闭所有
		$(".J_tabCloseOther").on("click", closeOther);//关闭其他
		$(".J_tabShowActive").on("click",showNow);//定位当前
	});
})(jQuery);

	$(function (){
    $("#side-menu").on("click", "li a",function (){$(this).tabInit(this)} );//初始化
	});