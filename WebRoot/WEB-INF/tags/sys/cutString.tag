<%@ tag pageEncoding="UTF-8" %>
<%--
 author: chenjp
 截取指定字符长度显示，超过长度则以。。。表示

 用法：
 1、在jsp页面中引入taglib
    <%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
 2、在需要系统代码的地方使用如下代码：
 <sys:cutString length="">${content}</sys:cutString>
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ tag import="com.hc.core.utils.StringHelper" %>
<%@ attribute name="length" type="java.lang.Integer" description="截取长度" %>
<jsp:doBody scope="request" var="_cutString"/>
<%
    String html = (String) request.getAttribute("_cutString");
    html = StringHelper.truncLength(html, length);
    request.setAttribute("cutString", html);
%>
${cutString}

