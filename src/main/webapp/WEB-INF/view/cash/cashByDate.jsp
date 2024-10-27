<%@page import="vo.Cash"%>
<%@page import="java.util.List"%>
<%@page import="dao.CashDAO"%>
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
	<h1>${email}님 ${cashDate} cash 목록</h1>
	<div>
	<a href="${pageContext.request.contextPath}/cashByMonth">
		월별목록
	</a>
	<div>
	<a href="${pageContext.request.contextPath}/insertCash?cashDate=${cashDate}">
		cash 추가
	</a>
	</div>
	</div>
	<div>${msg}</div>
	<table border="1">
		<tr>
			<!-- 컬럼명 -->
			<th>생성날짜</th>
			<th>수입/지출</th>
			<th>money</th>
			<th>메모</th>
			<th>수정날짜</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>
		<c:forEach var ="c" items="${list}">
			<tr>
				<td>${c.createdate}</td>
				<td>${c.kind}</td>
				<td>${c.money}</td>
				<td>${c.memo}</td>
				<td>${c.updatedate}</td>
				<td>
					<a href="${pageContext.request.contextPath}/updateCash?cashNo=${c.cashNo}">수정</a>
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/deleteCash?cashNo=${c.cashNo}&cashDate=${c.cashDate}">삭제</a>
				</td>
			</tr>		
		</c:forEach>
					
		
	</table>
</body>
</html>