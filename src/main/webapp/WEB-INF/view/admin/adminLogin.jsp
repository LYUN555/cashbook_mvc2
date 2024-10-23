<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>adminLoginForm</title>
</head>
<body>
	<h1>관리자 로그인</h1>
	<div>
		<a href = "${pageContext.request.contextPath}/home">공지사항</a>
	</div>
	<div>${msg}</div>
	<form method ="post" action="${pageContext.request.contextPath}/adminLogin">
		<table border = "1">
			<tr>
				<td>관리자 ID</td>
				<td><input type ="text" name="adminId"></td>
			</tr>
			<tr>
				<td>관리자 PW</td>
				<td><input type ="password" name="adminPw"></td>
			</tr>
		</table>
		<button type ="submit">로그인</button>
	</form>
</body>
</html>