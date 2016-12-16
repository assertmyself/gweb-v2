    <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
    <%
        String csv = new String(request.getParameter("csv").getBytes("utf-8"),"gbk");
        if(csv!=null && !"".equals(csv)) {
            response.setHeader("Content-type","text/csv");
            response.setHeader("Content-disposition", "attachment;filename=chart.csv");
            response.getWriter().print(csv);
        }
    %>