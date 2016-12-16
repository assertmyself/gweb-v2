<%@ page import="org.springframework.security.web.authentication.AbstractProcessingFilter" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%-- 把 content 属性关联到 HTTP 头部。(response  响应报文,request 头信息无法修改)  content-type expires refresh set-cookie 
    <meta http-equiv="expires" content="11111"/>
	<meta http-equiv="Refresh" content="50 ; url=http://www.163.com" /> --%>
	<link rel="shortcut icon" href="assets/img/oem/${oem.vendor.imgPath}/favicon.ico" type="image/vnd.microsoft.icon"> 
	<link rel="icon" href="assets/img/oem/${oem.vendor.imgPath}/favicon.ico" type="image/vnd.microsoft.icon">
	<title>${oem.vendor.product}设备管理系统</title>
    <%@include file="/common/header.jsp" %>
    <script type="text/javascript">

        $(function () {
            adjustDivPostionCenter("loginDiv");
            <%
                if (session.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) != null) {
            %>

            var errMsg = "${SPRING_SECURITY_LAST_EXCEPTION.message}";
            var emptyErr = "${not empty param.login_error}";
            if (errMsg != "" && emptyErr == "true") {
                //showWarningMsg(errMsg);
                $(".loginDiv").css({"padding":"20px 40px"});            
                $(".e_validation").show();
                $("#bubbleLayerWrap p").empty();
                $("#bubbleLayerWrap p").append(errMsg);
                $("#bubbleLayer").css("top","280px");                 
                $("#bubbleLayer").show();
                shake2("bubbleLayer");  
            }
            <%
                }
            %>
        });
        $(window).resize(function () {
            adjustDivPostionCenter("loginDiv");
        });

        function reloadImage() {
            document.getElementById("pic").src = "${ctx}/common/imageCode.jsp?p=" + Math.random();
        }

        function submitForm(form) {
            //判断验证码是否隐藏
            var temp=$(".e_validation").is(":hidden");                
            var loginName = document.getElementById("j_username").value;
            var password = document.getElementById("j_password").value;
            var validation_code = document.getElementById("j_validation_code").value;
            if (loginName == null || loginName == "" || loginName == "请输入用户名") {
               // showWarningMsg("用户名必须输入！", function () {
                    document.getElementById("j_username").focus();                   
               // });
                 $("#bubbleLayerWrap p").empty();
                 $("#bubbleLayerWrap p").append("请先输入用户名");
                 $("#bubbleLayer").css("top","255px");                  
                 $("#bubbleLayer").show();
                 shake("bubbleLayer");                
                return false;
            }

            if (password == null || password == "" || password == "请输入登录密码") {
                //showWarningMsg("密码必须输入！", function () {
                    document.getElementById("j_password").focus();                   
               // });
                 $("#bubbleLayerWrap p").empty();
                 $("#bubbleLayerWrap p").append("请输入您的密码");
                 $("#bubbleLayer").css("top","305px");                 
                 $("#bubbleLayer").show();
                 shake("bubbleLayer");                
                return false;
            }
         if(!temp){   
            if (validation_code == null || validation_code == "") {
                //showWarningMsg("验证码必须输入！", function () {
                    document.getElementById("j_validation_code").focus();
               // });
                 $("#bubbleLayerWrap p").empty();
                 $("#bubbleLayerWrap p").append("请输入您的验证码");
                 $("#bubbleLayer").css("top","335px");                  
                 $("#bubbleLayer").show();
                 shake("bubbleLayer");   
                return false;
            }
          }
            return true;
        }
        function reset(){
        	document.getElementById("j_username").value="";
        	document.getElementById("j_password").value="";
        	document.getElementById("j_validation_code").value="";       	
        }
        //鼠标点击文本框提示信息消失
        function notice(){
        $("#bubbleLayerWrap p").empty();
        $("#bubbleLayer").hide();
        }
        //提示信息抖动
        function shake(o){
        var $panel = $("#"+o);
            box_left = ($(window).width() -  $panel.width())/2;
            box_left = box_left+230;
            $panel.css({'left': box_left,'position':'absolute'});
       for(var i=1; 4>=i; i++){
            $panel.animate({left:box_left-(40-10*i)},50);
            $panel.animate({left:box_left+2*(40-10*i)},50);
             }
        }
         //提示验证失败时抖动
        function shake2(o){
        var $panel = $("#"+o);
            box_left = ($(window).width() -  $panel.width())/2;
            box_left = box_left+180;
            $panel.css({'left': box_left,'position':'absolute'});
       for(var i=1; 4>=i; i++){
            $panel.animate({left:box_left-(40-10*i)},50);
            $panel.animate({left:box_left+2*(40-10*i)},50);
             }
        }
    </script>

    <link type="text/css" rel="stylesheet" href="${ctx}/assets/css/login.css" media="screen"/>
</head>

<body class="userlogin-body body-content" style=" BACKGROUND: url(assets/img/oem/${oem.vendor.imgPath}/main.jpg) #4B7588  no-repeat top center;background-size:100%;">
<!-- fix window not open -->
<div class="main-content"> </div>
<form id="loginForm" name="loginForm" action="j_spring_security_check" method="post" onsubmit="return submitForm(this)">
    <div style="margin:155px auto 0px;width: 450px;height:304px">
    <div style="width: 309px;height: 54px;">
    	<!-- header pic ../assets/img/oem/${oem.name}/titlePic.png -->
    	<img src="assets/img/oem/${oem.vendor.imgPath}/headerPic.png" alt=""/>
    </div>
    <div class="loginDiv" id="loginDiv">
    	<div>
    		<!-- product logo -->
    		<img src="assets/img/oem/${oem.vendor.imgPath}/productLogo.png" alt="" style="margin-left:190px"/>
    	</div>
    	 <div style="height: 40px;width: 370px;margin-top:10px">
                <div class="input-group">
                    <span class="input-group-addon">&nbsp;&nbsp;用户名：</span>
                    <input type="text" type="text" name="j_username" id="j_username"
                           value="<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>"
                           class="form-control"  style="width: 275px;" onclick="notice();"/>                   
                </div>               
         </div>
         <div style="height: 40px;width: 370px;margin-top:5px">
                <div class="input-group">
                    <div class="input-group-addon" style="width: 95px;">
                    	<span>&nbsp;&nbsp;密 &nbsp;码：</span>
                    </div>
                    <input type="password" name="j_password" id="j_password" maxlength="50"
                           class="form-control" style="width: 275px" onclick="notice();"/>
                </div>
          </div>
          <div style="height: 40px;width: 370px;margin-top:5px;display: none;" class="e_validation">
                <div class="input-group">
                    <span class="input-group-addon">&nbsp;&nbsp;验证码：</span>
                    <input type="text" name="j_validation_code" id="j_validation_code" maxlength="4"
                           class="form-control " style="width: 190px ;height:34px" onclick="notice();"/>
                <span class="input-group-addon">
                    <img id="pic" src="<c:url value="common/imageCode.jsp"/>" onclick="reloadImage();" height="20"
                         title="点击更换图片" style="vertical-align: middle;cursor:pointer"/>
                </span>
                </div>
           </div>
           <div style="height:40px;width: 370px;margin-top:10px">
           		<input type="image" src="assets/img/loginIn.png"  alt="登录" style="cursor:pointer;margin-right:38px;" onmouseover="this.src='assets/img/loginOut.png'" 
           		onmouseout="this.src='assets/img/loginIn.png'"/>
           		<input type="image" src="assets/img/resetIn.png" alt="重置" style="cursor:pointer;" onclick="reset();return false;" onmousemove="this.src='assets/img/resetOut.png'"
           		onmouseout="this.src='assets/img/resetIn.png'"/>
            </div>
    </div>
    </div>
</form>
<!--  
<div id="layNotice" class="alert alert-danger notice" style="display: none;"></div>-->
<div style="top: 250px;display: none;" id="bubbleLayer" class="layer bubbleLayer-show">
	<div class="layer-hd"></div>
	<div id="bubbleLayerWrap" class="layer-mid"><p></p></div><div class="error-tt"></div>
	<div class="layer-ft"></div>
	<div style="top: 25.5px;" id="layerArr" class="layer-arrow"></div>
</div>

<script src="assets/js/jquery.backstretch.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
	$("body").backstretch(
			[ "assets/img/bg/1.jpg", "assets/img/bg/2.jpg",
					"assets/img/bg/3.jpg", "assets/img/bg/4.jpg" ], {
				duration : 4000,
				fade : 1000
			});
	});
</script>
</body>
</html>