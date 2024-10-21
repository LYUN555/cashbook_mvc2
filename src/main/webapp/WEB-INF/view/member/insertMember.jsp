<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
	<h1>회원 가입</h1>
	<div>${msg}</div>
	<form method="post" action="${pageContext.request.contextPath}/emailCheck">
	<table border = "1">
			<tr>
				<td>이메일</td>
				<td><input type="text" name ="ckid" value="${ckid}"></td>
				<td><button type="submit">중복체크</button>
			</tr>
	</table>
	</form>
	<hr>
	<form method= "post" action="${pageContext.request.contextPath}/insertMember">
		<table border = "1">
			<tr>
				<td>이메일</td>
				<td><input type="text" name="id" value="${empty ckid ? '' : ckid}" readonly></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name ="repw"></td>
			</tr>
			<tr>
				<td>비밀번호확인</td>
				<td><input type="password" name ="pw"></td>
			</tr>
			<tr>
				<td>생일</td>
				<td><input type="date" name ="birth"></td>
			</tr>
		</table>
		<button type="submit">회원가입</button>
	</form>
</body>
</html>