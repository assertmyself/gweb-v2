<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<%@page import="org.apache.commons.lang.StringUtils" %>
<%@page import="org.apache.commons.lang.exception.ExceptionUtils" %>
<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>
<%--<style type="text/css">
    body {
        font-size: 12px;
    }
</style>
--%><%
    try {
        //返回页面
        String redirectPage = "login.jsp";
        String referer = request.getHeader("REFERER");
        if (referer != null) {
            if (referer.indexOf("/site/") >= 0) {
                redirectPage = "site/index.do";
            }
        }
        String nullPage = "您所访问的页面不存在，请和管理员联系! <a href='" + request.getContextPath() + "/" + redirectPage + "' target='_parent'>返回首页</a>";

        // 获取错误状态码
        String statusCode = StringUtils.defaultIfEmpty(String.valueOf(request.getAttribute("javax.servlet.error.status_code")), "");

        if (statusCode.equals("404")) {
            out.println(nullPage);
            return;
        } else if (statusCode.equals("500")) {
            String msg = "您所访问的页面出现了异常，请联系管理员!";
            if (exception != null) {
                //记录日志
                Throwable ex = exception;
                Logger logger = LoggerFactory.getLogger("500.jsp");
                logger.error(ex.getMessage(), ex);

                if (ex.getMessage().indexOf("No matching handler method found for servlet request: path") >= 0) {
                    msg = nullPage;
                }
            }
            out.println(msg);

        } else {
            Throwable ex = (Exception) request.getAttribute("javax.servlet.error.exception");
            System.out.println("*****..ex.getMessage() = " + ex.getMessage());
            if (ex != null && ex instanceof org.springframework.dao.DataAccessException) {
                out.println("系统处理过程中出现了DAO");
            } else if(ex != null && ex instanceof java.lang.RuntimeException){
            	out.println("系统处理过程中出现了运行时异常");
            }else {
            	System.out.println("++++"+ex.getMessage());
                if (ex != null) {
                    ex.printStackTrace();
                    out.println("系统处理过程中出现了异常，异常消息如下  (MSG):"+ ExceptionUtils.getRootCause(ex).getLocalizedMessage());
                } else {
                    out.println("系统处理过程中出现了异常，请和管理员联系!");
                }
            }
           // out.println("{\"msg\":\"okdls\",\"ex\":\"exception\"}");
            out.close();
        }
    } catch (Exception e) {
        out.println("系统处理过程中出现了内部异常，异常消息如下: " + e.getMessage());
        e.printStackTrace();
    }
%>
