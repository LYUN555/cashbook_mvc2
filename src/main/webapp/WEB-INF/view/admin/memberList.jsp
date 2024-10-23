<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>멤버 목록</h1>
	<div>
		관리자 : ${adminId}님
	</div>
	<div>
		<a href="${pageContext.request.contextPath}/adminLogout">관리자 로그아웃</a>
	</div>
	<div>${msg}</div>
	<!-- 멤버 리스트 출력 -->
	<table border="1">
		<tr>
			<th>이메일</th>
			<th>생일</th>
			<th>최종수정날짜</th>
			<th>가입날짜</th>
			<th>PW변경</th>
			<th>강퇴</th>
		</tr>
		<c:forEach var="m" items="${list}">
			<tr>
				<td>${m.email}</td>
				<td>${m.birth}</td>
				<td>${m.updatedate}</td>
				<td>${m.createdate}</td>
				<td><a href ="${pageContext.request.contextPath}/memberPwUpdate?email=${m.email}">변경</a></td>
				<td><a href ="${pageContext.request.contextPath}/memberKick?email=${m.email}">강퇴</a></td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<c:if test="${currentPage > 1}">
			<a href="${pageContext.request.contextPath}/admin/memberList?currentPage=${1}">처음</a>
			<a href="${pageContext.request.contextPath}/admin/memberList?currentPage=${currentPage-1}">이전</a>
		</c:if>
		[${currentPage}]
		<c:if test="${currentPage < lastPage}">
			<a href="${pageContext.request.contextPath}/admin/memberList?currentPage=${currentPage+1}">다음</a>
			<a href="${pageContext.request.contextPath}/admin/memberList?currentPage=${lastPage}">끝</a>
		</c:if>
	</div>
	<a href="${pageContext.request.contextPath}/home">공지사항</a><br>
</body>
</html>