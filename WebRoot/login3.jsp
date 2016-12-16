<%@ page
	import="org.springframework.security.web.authentication.AbstractProcessingFilter"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<%-- 把 content 属性关联到 HTTP 头部。(response  响应报文,request 头信息无法修改)  content-type expires refresh set-cookie 
    <meta http-equiv="expires" content="11111"/>
	<meta http-equiv="Refresh" content="50 ; url=http://www.163.com" /> --%>

		<title>${oem.vendor.product} 设备管理系统</title>
		<%@include file="/common/header.jsp"%>
		<script type="text/javascript">

        $(function () {
            adjustDivPostionCenter("loginDiv");
            <%if (session
					.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) != null) {%>

            var errMsg = "${SPRING_SECURITY_LAST_EXCEPTION.message}";
            var emptyErr = "${not empty param.login_error}";
            if (errMsg != "" && emptyErr == "true") {
                showWarningMsg(errMsg);
            }
            <%}%>
        });
        $(window).resize(function () {
            adjustDivPostionCenter("loginDiv");
        });

        function reloadImage() {
            document.getElementById("pic").src = "${ctx}/common/imageCode.jsp?p=" + Math.random();
        }

        function submitForm(form) {
            var loginName = document.getElementById("j_username").value;
            var password = document.getElementById("j_password").value;
            var validation_code = document.getElementById("j_validation_code").value;
            if (loginName == null || loginName == "" || loginName == "请输入用户名") {
                showWarningMsg("用户名必须输入！", function () {
                    document.getElementById("j_username").focus();
                });

                return false;
            }

            if (password == null || password == "" || password == "请输入登录密码") {
                showWarningMsg("密码必须输入！", function () {
                    document.getElementById("j_password").focus();
                });
                return false;
            }

            if (validation_code == null || validation_code == "") {
                showWarningMsg("验证码必须输入！", function () {
                    document.getElementById("j_validation_code").focus();
                });
                return false;
            }

            return true;
        }
        function reset(){
        	document.getElementById("j_username").value="";
        	document.getElementById("j_password").value="";
        	document.getElementById("j_validation_code").value="";
        }
    </script>

		<link type="text/css" rel="stylesheet"
			href="${ctx}/assets/css/login.css" media="screen" />

		<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

/* 禁用响应式布局：重新设置container的宽度。如果没有后面三行的代码，在IE6环境下navbar-top会显示为940px宽度 */
.container,.navbar-static-top .container,.navbar-fixed-top .container,.navbar-fixed-bottom .container
	{
	width: 1140px;
}
</style>
	</head>

	<body class="userlogin-body body-content">
		<!-- fix window not open -->
		<div class="main-content">
		</div>
		<form id="loginForm" name="loginForm" action="j_spring_security_check"
			method="post" onsubmit="return submitForm(this)">
			<div style="margin: 155px auto 0px; width: 450px; height: 304px">
				<div style="width: 309px; height: 54px;">
					<!-- header pic ../assets/img/oem/${oem.vendor.imgPath}/titlePic.png -->
					<img src="assets/img/oem/${oem.vendor.imgPath}/headerPic.png" alt="" />
				</div>
				<div class="loginDiv" id="loginDiv">
					<div>
						<!-- product logo -->
						<img src="assets/img/oem/${oem.vendor.imgPath}/productLogo.png" alt=""
							style="margin-left: 190px" />
					</div>
					<div style="height: 40px; width: 370px; margin-top: 10px">
						<div class="input-group">
							<span class="input-group-addon">&nbsp;&nbsp;用户名：</span>
							<input type="text" type="text" name="j_username" id="j_username"
								value="<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>"
								class="form-control" style="width: 275px;" />
						</div>
					</div>
					<div style="height: 40px; width: 370px; margin-top: 5px">
						<div class="input-group">
							<span class="input-group-addon">&nbsp;&nbsp;密&nbsp;&nbsp;码：</span>
							<input type="password" name="j_password" id="j_password"
								maxlength="10" class="form-control" style="width: 275px" />
						</div>
					</div>

					<div style="height: 40px; width: 370px; margin-top: 5px">
						<div class="input-group">
							<span class="input-group-addon">&nbsp;&nbsp;验证码：</span>
							<input type="text" name="j_validation_code"
								id="j_validation_code" maxlength="4" class="form-control "
								style="width: 190px; height: 34px" />
							<span class="input-group-addon"> <img id="pic"
									src="<c:url value="common/imageCode.jsp"/>"
									onclick="reloadImage();" height="20" title="点击更换图片"
									style="vertical-align: middle; cursor: pointer" /> </span>
						</div>
					</div>

					<div style="height: 40px; width: 370px; margin-top: 10px">
						<input type="image" src="assets/img/loginIn.png" alt="登录"
							style="cursor: pointer; margin-right: 38px;"
							onmouseover="this.src='assets/img/loginOut.png'"
							onmouseout="this.src='assets/img/loginIn.png'" />
						<input type="image" src="assets/img/resetIn.png" alt="重置"
							style="cursor: pointer;" onclick="reset();"
							onmousemove="this.src='assets/img/resetOut.png'"
							onmouseout="this.src='assets/img/resetIn.png'" />
					</div>
				</div>
			</div>
		</form>
<!-- 
		<script src="assets/js/jquery-1.11.0.min.js" type="text/javascript"></script>
		<script src="assets/js/jquery.backstretch.min.js"
			type="text/javascript"></script>
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
		 -->
	</body>
</html>