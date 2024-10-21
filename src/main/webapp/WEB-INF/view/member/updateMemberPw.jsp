<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${sessionScope.email}비밀번호 변경</h1>
	<div>${msg}</div>
	<form method = "post" action="${pageContext.request.contextPath}/updateMemberPw">
		<table border="1">
			<tr>
				<td>현재 비밀번호</td>
				<td><input type ="password" name = "prepw"></td>
			</tr>
			<tr>
				<td>새로운 비밀번호</td>
				<td><input type ="password" name = "newpw"></td>
			</tr>
		</table>
		<button type = "submit">비밀번호 변경</button>
	</form>
</body>
</html>