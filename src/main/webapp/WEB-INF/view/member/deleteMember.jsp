<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${sessionScope.email} 회원 탈퇴</h1>
	<div>${msg}</div>
	<form method ="post" action="${pageContext.request.contextPath}/deleteMember">
		<table border="1">
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" value="${sessionScope.email}" readonly></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pw"></td>
			</tr>
		</table>
		<button type ="submit">탈퇴</button>
	</form>
	
</body>
</html>