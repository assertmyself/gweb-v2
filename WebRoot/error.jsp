<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<%@page import="org.apache.commons.lang.StringUtils" %>
<%@page import="org.apache.commons.lang.exception.ExceptionUtils" %>
<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>

<%
    try {
        //返回页面
        String redirectPage = "login.jsp";
        String referer = request.getHeader("REFERER");
        if (referer != null) {
            if (referer.indexOf("/site/") >= 0) {
                redirectPage = "site/index.do";
            }
        }
        String remp = "<a href='" + request.getContextPath() + "/" + redirectPage + "' target='_parent' class=\"btn btn-primary m-t\">  <i class=\"fa fa-home\"></i>&nbsp&nbsp返回</a>";

        // 获取错误状态码
        String statusCode = StringUtils.defaultIfEmpty(String.valueOf(request.getAttribute("javax.servlet.error.status_code")), "");

        if (exception != null) {
            //记录日志
            Throwable ex = exception;
            Logger logger = LoggerFactory.getLogger("500.jsp");
            logger.error(ex.getMessage(), ex);
        }
        
        if (statusCode.equals("404")) {
        	String html = "<div class=\"middle-box text-center animated fadeInDown\" style=\"margin-top:50px\">"
        		+ "<h1>404</h1>" 
        	    + "<h3 class=\"font-bold\"> 页面未找到！ </h3>"
        	    + "<div class=\"error-desc\">  抱歉，页面好像去火星了~ ~ <br/> "+remp+"</div>"
        	    + "</div>";
            out.println(html);
            return;
        } else if (statusCode.equals("500")) {
        	String html = "<div class=\"middle-box text-center animated fadeInDown\" style=\"margin-top:50px\">"
        		+ "<h1>500</h1> "
        		+ "<h3 class=\"font-bold\">服务器内部错误！</h3>"
        		+ "<div class=\"error-desc\"> 服务器好像出错了...<br/>您可以返回主页看看<br/>"+remp+"</div>"
        		+ "</div>";
            out.println(html);
        } else {
        	//statusCode==200  ajax 异常，建议上层应用处理掉
            //Throwable ex = (Exception) request.getAttribute("javax.servlet.error.exception");
            if (exception != null && exception instanceof ServletException) {
                out.println("系统处理过程中出现了Servlet异常！");
            } else {
                out.println("系统处理过程中出现了其它异常,请和管理员联系！");
            }
            //out.close();
        }
    } catch (Exception e) {
        out.println("Error页面处理异常！");
        e.printStackTrace();
    }finally{
    	
    }
%>

