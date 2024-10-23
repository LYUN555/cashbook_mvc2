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
	<h1>${email}님 반갑습니다</h1>
	<a href="${pageContext.request.contextPath}/home">공지사항</a><br>
	<a href="${pageContext.request.contextPath}/cashByMonth">수입/지출 리스트</a><br>
	<a href="${pageContext.request.contextPath}/cashByYearList">연간 수입/지출 리스트</a><br>
	<a href="${pageContext.request.contextPath}/logout">로그아웃</a><br>
	<a href="${pageContext.request.contextPath}/updateMemberPw">비밀번호변경</a><br>
	<a href="${pageContext.request.contextPath}/deleteMember">회원탈퇴</a><br>
</body>
</html>