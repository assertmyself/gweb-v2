<%@ tag pageEncoding="UTF-8" %>
<%--
 author: tcg

 用法：
 1、在jsp页面中引入taglib
    <%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
 2、在需要系统代码的地方使用如下代码：
 <sys:toLower>${content}</sys:toLower>
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ tag import="org.hibernate.annotations.common.util.StringHelper" %>
<jsp:doBody scope="request" var="_lowString"/>
<%
    String lowString = (String) request.getAttribute("_lowString");
    if (!StringHelper.isEmpty(lowString)) {
        lowString = lowString.toLowerCase();
    }
    request.setAttribute("lowString", lowString);
%>${lowString}

