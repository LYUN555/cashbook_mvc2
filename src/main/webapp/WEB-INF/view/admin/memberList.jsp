<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>멤버 목록</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="bg-light">
        <c:import url="/WEB-INF/view/inc/header.jsp"></c:import>
    </div>

    <div class="container mt-5">
        <h1 class="text-center">멤버 목록</h1>
        <div class="mb-3">
            <p>관리자 : ${adminId}님</p>
        </div>
        <div class="text-danger text-center">${msg}</div>
        
        <!-- 멤버 리스트 출력 -->
        <table class="table table-bordered mt-3">
            <thead>
                <tr>
                    <th>이메일</th>
                    <th>생일</th>
                    <th>최종수정날짜</th>
                    <th>가입날짜</th>
                    <th>PW변경</th>
                    <th>강퇴</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="m" items="${list}">
                    <tr>
                        <td>${m.email}</td>
                        <td>${m.birth}</td>
                        <td>${m.updatedate}</td>
                        <td>${m.createdate}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/memberPwUpdate?email=${m.email}" class="btn btn-success">변경</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/memberKick?email=${m.email}" class="btn btn-danger">강퇴</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="text-center">
            <c:if test="${currentPage > 1}">
                <a href="${pageContext.request.contextPath}/admin/memberList?currentPage=${1}" class="btn btn-secondary">처음</a>
                <a href="${pageContext.request.contextPath}/admin/memberList?currentPage=${currentPage-1}" class="btn btn-secondary">이전</a>
            </c:if>
            <span>[${currentPage}]</span>
            <c:if test="${currentPage < lastPage}">
                <a href="${pageContext.request.contextPath}/admin/memberList?currentPage=${currentPage+1}" class="btn btn-secondary">다음</a>
                <a href="${pageContext.request.contextPath}/admin/memberList?currentPage=${lastPage}" class="btn btn-secondary">끝</a>
            </c:if>
        </div>

        <div class="text-center mt-3">
            <a href="${pageContext.request.contextPath}/home" class="btn btn-primary">공지사항</a>
        </div>
    </div>
</body>
</html>