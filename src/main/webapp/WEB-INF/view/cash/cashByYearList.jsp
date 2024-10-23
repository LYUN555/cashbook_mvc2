<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>${email}님의${year}년 수입/지출 리스트</h1>
		<table border ="1">
			<tr>
				<td>월</td>
				<td>수입/지출</td>
				<td>합계</td>
			</tr>
			<c:forEach var = "m" items ="${list}">
					<tr>
						<td>${m.month}</td>
						<td>${m.kind}</td>
						<td>
							<c:if test="${m.kind == '수입'}">
								+${m.sum}
							</c:if>
							<c:if test="${m.kind == '지출'}">
								-${m.sum}
							</c:if>
									
						</td>
					</tr>
			</c:forEach>
		</table>
	<!-- 페이징 -->
	<div>
	<c:if test="${year > min}">
		<a href="${pageContext.request.contextPath}/cashByYearList?year=${min}">처음년도</a>
		<a href="${pageContext.request.contextPath}/cashByYearList?year=${year-1}">이전</a>
	</c:if>
	<c:if test="${year < max}">
		<a href="${pageContext.request.contextPath}/cashByYearList?year=${year+1}">다음</a>
		<a href="${pageContext.request.contextPath}/cashByYearList?year=${max}">마지막년도</a>
	</c:if>
			
	</div>
</body>
</html>