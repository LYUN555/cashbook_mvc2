<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>${msg}</div>
	<h1>cash 수정</h1>
	<form method ="post" action="${pageContext.request.contextPath}/updateCash">
		<table border="1">
			<tr>
				<td>cashDate</td>
				<td><input type="text" name="cashDate" value="${cash.cashDate }"readonly></td>
			</tr>
			<tr>
				<td>cashNo</td>
				<td><input type="text" name="cashNo" value="${cash.cashNo}" readonly></td>
			</tr>			
			<tr>
				<td>kind</td>
				<td>
					<input type="radio" name="kind" value="수입"> 수입
					<input type="radio" name="kind" value="지출" > 지출
				</td>
			</tr>
			<tr>
				<td>money</td>
				<td><input type="number" name="money" value="${cash.money}"></td>
			</tr>
			<tr>
				<td>memo</td>
				<td>
					<textarea cols="50" rows="3" name="memo">${cash.memo}</textarea>
				</td>
			</tr>
		</table>
		<button type="submit">수정</button>
	</form>
</body>
</html>