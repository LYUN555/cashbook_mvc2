<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>cash 입력</h1>
	<div>${msg}</div>
	<div>
	<a href="${pageContext.request.contextPath}/cashByMonth">
		월별목록
	</a>
	</div>
	<form method="post" action="${pageContext.request.contextPath}/insertCash">
		<table border="1">
			<tr>
				<td>cashDate</td>
				<td><input type="text" name="cashDate" value="${cashDate}" readonly></td>
			</tr>
			<tr>
				<td>kind</td>
				<td>
					<input type = "radio" name="kind" value="수입">수입
					<input type = "radio" name="kind" value="지출">지출
				</td>
			</tr>
			<tr>
				<td>money</td>
				<td><input type="number" name="money"></td>
			</tr>
			<tr>
				<td>memo</td>
				<td><textarea cols="50" rows="3" name="memo"></textarea></td>
			</tr>
		</table>
		<button type="submit">입력</button>
	</form>
</body>
</html>