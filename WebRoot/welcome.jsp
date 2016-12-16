<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>欢迎页面</title>
    <script type="text/javascript">
    	$(function(){
    		$("tr:even").css("background-color","rgb(249, 249, 249)");
			//window.setTimeout('loadMainPage("${ctx}/firstPage/index.do", "首页");',10000);
    	});
/*
    	$(function(){
            $("[rel=drevil]").popover({
                trigger:'manual',
                placement : 'bottom', //placement of the popover. also can use top, bottom, left or right
                title : '<div style="text-align:center; color:red; text-decoration:underline; font-size:14px;"> Muah ha ha</div>', //this is the top title bar of the popover. add some basic css
                html: 'true', //needed to show html of course
                content : '<div id="popOverBox"><img src="http://www.hd-report.com/wp-content/uploads/2008/08/mr-evil.jpg" width="251" height="201" /></div>', //this is the content of the html box. add the image here or anything you want really.
                animation: false
            }).on("mouseenter", function () {
                        var _this = this;
                        $(this).popover("show");
                        $(this).siblings(".popover").on("mouseleave", function () {
                            $(_this).popover('hide');
                        });
                    }).on("mouseleave", function () {
                        var _this = this;
                        setTimeout(function () {
                            if (!$(".popover:hover").length) {
                                $(_this).popover("hide")
                            }
                        }, 100);
                    });
        });*/
    </script>
    <script type="text/javascript">
/*判断浏览器版本是否过低*/
$(document).ready(function() {
var explorer = window.navigator.userAgent ;
 if (explorer.indexOf("MSIE") >= 0) {
var b_name = navigator.appName;
var b_version = navigator.appVersion;
var version = b_version.split(";");
var trim_version = version[1].replace(/[ ]/g, "");
if (b_name == "Microsoft Internet Explorer") {
/*如果是IE6或者IE7*/
if (trim_version == "MSIE7.0" || trim_version == "MSIE6.0"|| trim_version == "MSIE8.0") {
$("#showVersionMsg").append("<p style='color: red;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;浏览器版本过低，推荐使用 火狐（强烈建议） google、IE8 以上浏览器！！</p>");
}
}
}
 //firefox 
else if (explorer.indexOf("Firefox") >= 0) {

}
//Chrome
else if(explorer.indexOf("Chrome") >= 0){

}
//Opera
else if(explorer.indexOf("Opera") >= 0){

}
//Safari
else if(explorer.indexOf("Safari") >= 0){

}
});
</script> 
  </head>
  
  <body>
<!-- 
<a href="#" rel="drevil">
    <span class="glyphicon glyphicon-shopping-cart"> </span> 购物车
</a> -->

  <div id="welDiv">
	<marquee scrollamount="5" width="300" behavior="slide" onmouseover=this.stop() onmouseout=this.start()>
  	<h4>
	<i class="glyphicon glyphicon-user"></i>&nbsp  欢迎&nbsp${userName}&nbsp进入${oem.vendor.product} 系统 &nbsp 
	</h4>
	</marquee>
  	<table class="table table-striped table-hover " id="welTable">
  			<tr>
  			<td colspan="2">用户信息</td>
  			</tr>
  			<tr>
  				<th width="30%">登录名</th>
  				<td>${userName}</td>
  			</tr>
  			<tr>
  				<th>用户别名</th>
  				<td>${realName}</td>  
  			</tr><%--
  			<tr>
  				<th>所属单位</th>
  				<td>${dept}</td>
  			</tr>
  			--%><tr>
  				<th>对应角色</th>
  				<td>${sysRole}</td>
  			</tr>
  			<tr>
  				<th>用户IP</th>
  				<td>${ipAddress}</td>
  			</tr>
  			<tr>
  				<th>登录时间</th>
  				<td>${time}</td>
  			</tr>
  			<tr>
  				<th>管理区域</th>
  				<td>${area}</td>
  			</tr>
  			<tr>
  				<th> 管理网元</th>
  				<td style="word-wrap:break-word;word-break:break-all;">${nes}</td>
  			</tr>
  	</table>
  </div>
  <div id="showVersionMsg"></div>
<%--
<div>
    <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
        Launch demo modal
    </button>
    <div class="modal fade in" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-backdrop fade in"></div>
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                </div>
                <div class="modal-body">
                    ...
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                </div>
            </div>
        </div>
    </div>
</div>
--%><%-- 

	//can open by js $("#processWindowId").modal({
	//	 keyboard: true,
	//	 backdrop: 'static'
	//});
		//js - center ;normal is ok.
		//
		$('#editMsg ').modal('hide').css({
			'margin-top': function () {
			return ($(this).height() * 2);
			}
			});
		
    	})

  --%>
</body>
</html>