<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>수입/지출 리스트</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="bg-light">
        <c:import url="/WEB-INF/view/inc/header.jsp"></c:import>
    </div>

    <div class="container mt-5">
        <h1>${email}님의 ${year}년 수입/지출 리스트</h1>
        <table class="table table-bordered mt-3">
            <thead>
                <tr>
                    <th>월</th>
                    <th>수입/지출</th>
                    <th>합계</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="m" items="${list}">
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
            </tbody>
        </table>

        <!-- 페이징 -->
        <div class="d-flex justify-content-center mt-4">
            <c:if test="${year > min}">
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/cashByYearList?year=${min}">처음년도</a>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/cashByYearList?year=${year-1}">이전</a>
            </c:if>
            <c:if test="${year < max}">
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/cashByYearList?year=${year+1}">다음</a>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/cashByYearList?year=${max}">마지막년도</a>
            </c:if>
        </div>
    </div>
</body>
</html>