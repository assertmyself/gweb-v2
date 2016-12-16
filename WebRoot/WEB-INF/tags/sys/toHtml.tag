<%@ tag pageEncoding="UTF-8" %>
<%--
 author: tcg

 用法：
 1、在jsp页面中引入taglib
    <%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
 2、在需要系统代码的地方使用如下代码：
 <sys:toHtml>${content}</sys:toHtml>
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ tag import="com.hc.core.utils.JspHelper" %>

<jsp:doBody scope="request" var="_html"/>
<%
    String bodyHtml = (String) request.getAttribute("_html");
    bodyHtml = JspHelper.FormatOutPutString(bodyHtml);
    request.setAttribute("bodyHtml", bodyHtml);
%>
${bodyHtml}

