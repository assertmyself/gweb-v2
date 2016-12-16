<%@ tag pageEncoding="UTF-8" %>
<%--
 author: cjp

 用法：
 1、在jsp页面中引入taglib
    <%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
 2、在需要系统代码的地方使用如下代码：
 <sys:code code="SysRegPersonType" detailCode="unit" detailId="10" defaultName="无"/>
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"
        %>
<%@ tag import="com.hc.core.utils.JspHelper"
        %>
<%@ tag import="com.gbcom.system.domain.SysCodeDetail"
        %>
<%@ tag import="com.gbcom.system.manager.SysCodeManager"
        %>
<%@ tag import="org.hibernate.annotations.common.util.StringHelper"
        %>
<%@ tag import="org.springframework.context.ApplicationContext"
        %>
<%@ tag import="org.springframework.web.context.support.WebApplicationContextUtils"
        %>
<%@ attribute name="code" type="java.lang.String" required="true" description="系统代码编码"
        %>
<%@ attribute name="detailCode" type="java.lang.String" description="系统代码明细code"
        %>
<%@ attribute name="detailId" type="java.lang.String" description="系统代码明细ID"
        %>
<%@ attribute name="defaultName" type="java.lang.String" description="为空时显示内容"
        %>
<%
    ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession(true).getServletContext());
    SysCodeManager sysCodeManager = (SysCodeManager) applicationContext.getBean("sysCodeManager");
    String ret = JspHelper.getString(defaultName);
    if (!StringHelper.isEmpty(detailCode)) {
        SysCodeDetail codeDetail = sysCodeManager.getCodeDetailByCode(code, detailCode);
        if (codeDetail != null) {
            ret = codeDetail.getName();
        }
    } else if (!StringHelper.isEmpty(detailId)) {
        try {
            SysCodeDetail codeDetail = sysCodeManager.getCodeListById(Long.valueOf(detailId));
            if (codeDetail != null) {
                ret = codeDetail.getName();
            }
        } catch (Exception e) {
            ret = "";
        }
    }
    out.print(ret);
%>