<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <title>系统提示</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Refresh" content="1;URL=${ctx}/${url}"/>
    <style type="text/css">
        .word {
            font-size: 13px;
            color: blue;
            font-weight: normal;
        }
    </style>
        <script type="text/javascript">
   		function logoutFun(){
   			location.href = '${ctx}/j_spring_security_logout';
		}
    	$(function(){
			window.setTimeout('logoutFun();',2);
    	})
    </script>
</head>

<body>
<br>
<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td align="center" valign="center" class="word">
           	 已退出系统，正在跳转登录界面......
        </td>
    </tr>
</table>
</body>
</html>