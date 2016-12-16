<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%
    //    System.out.println("................url = " + request.getAttribute("url"));
    String url = StringUtils.defaultIfEmpty((String) request.getAttribute("url"), "/mainPage/index.do");
    response.sendRedirect(request.getContextPath() + url);
%>
