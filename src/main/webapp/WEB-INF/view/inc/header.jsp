<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <div class="navbar-brand">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/home">홈으로</a>
        </div>
        <div class="d-flex">
            <c:if test="${not empty adminId}">
                <a class="btn btn-secondary me-3" href="${pageContext.request.contextPath}/memberList">멤버관리</a>
           		<a href="${pageContext.request.contextPath}/adminLogout" class="btn btn-danger">로그아웃</a>
            </c:if>

            <c:if test="${not empty email}">
                <div class="me-3">${email}님 반갑습니다.</div>
                <a class="btn btn-primary me-3" href="${pageContext.request.contextPath}/memberOne">상세정보</a>
                <a class="btn btn-danger" href="${pageContext.request.contextPath}/logout">로그아웃</a>
            </c:if>

            <c:if test="${empty email and empty adminId}">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/login">로그인</a>
            </c:if>
        </div>
    </div>
</nav>