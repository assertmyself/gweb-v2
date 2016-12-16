<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <title>系统提示</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="refresh" content="10;URL=jvm.jsp"/>
    <style type="text/css">
        .word {
            font-size: 14px;
            color: blue;
            font-weight: normal;
        }
    </style>
</head>

<body>
<br>
<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td align="center" valign="center" class="word">
            <%
                out.println("<li>JVM MAX MEMORY: " + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "M</li><br><br>");
                out.println("<li>JVM IS USING MEMORY:" + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "M</li><br><br>");
                out.println("<li>JVM IS FREE MEMORY:" + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "M</li><br>");
            %>
        </td>
    </tr>
</table>
</body>
</html>