<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <title>系统提示</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@include file="/common/header.jsp" %>
    <script language="javascript">
        function doReturn() {
            var returnUrl = "${msg.url}";
            if (returnUrl != "") {
                window.location = returnUrl;
            } else {
                history.back();
            }

        }
    </script>
</head>

<body>
ldsdg
<br>
<table width="450" height="300" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td background="${ctx}/skin/images/error.jpg">
            <table width="476" height="300" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                    <td colspan="2" height="40"></td>
                </tr>
                <tr>
                    <td width="120">&nbsp;</td>
                    <td height="130" style="word-break:break-all" class="msg_hints">
                        ${msg.msg}&nbsp;
                    </td>
                </tr>
                <tr>
                    <td height="30" align="center">&nbsp; </td>
                    <td>
                        <input type="button" value="返回" onClick="doReturn();" class="button_back">
                    </td>
                </tr>
                <tr>
                    <td colspan="2" height="50"></td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
<script>
    //5秒后自动跳转
    //    setTimeout("doReturn()", 5000);
</script>
</html>