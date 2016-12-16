<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Employees</title>
</head>
<body>
<table border=1>
	<thead><tr>
		<th>ID</th>
		<th>Name</th>
		<th>Email</th>
	</tr></thead>
	<%--<c:forEach var="employee" items="${object}">
	--%><tr>
		<td>${object.id}</td>
		<td>${object.name}</td>
		<td>${object.email}</td>
	</tr>
	<%--</c:forEach>
--%></table>
</body>
</html>