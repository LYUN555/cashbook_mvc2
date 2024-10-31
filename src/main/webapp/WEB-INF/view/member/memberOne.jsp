<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>메인 페이지</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="bg-light">
        <c:import url="/WEB-INF/view/inc/header.jsp"></c:import>
    </div>

    <div class="container mt-5">
        <h1 class="text-center">${email}님 반갑습니다!</h1>
        
        <div class="card mt-4">
            <div class="card-body">
                <h5 class="card-title">메뉴</h5>
                <ul class="list-group">
                    <li class="list-group-item">
                        <a class="btn btn-dark w-100" href="${pageContext.request.contextPath}/home">공지사항</a>
                    </li>
                    <li class="list-group-item">
                        <a class="btn btn-primary w-100" href="${pageContext.request.contextPath}/cashByMonth">수입/지출 리스트</a>
                    </li>
                    <li class="list-group-item">
                        <a class="btn btn-primary w-100" href="${pageContext.request.contextPath}/cashByYearList">연간 수입/지출 리스트</a>
                    </li>
                    <li class="list-group-item">
                        <a class="btn btn-success w-100" href="${pageContext.request.contextPath}/updateMemberPw">비밀번호 변경</a>
                    </li>
                    <li class="list-group-item">
                        <a class="btn btn-danger w-100" href="${pageContext.request.contextPath}/logout">로그아웃</a>
                    </li>
                    <li class="list-group-item">
                        <a class="btn btn-danger w-100" href="${pageContext.request.contextPath}/deleteMember">회원 탈퇴</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>
