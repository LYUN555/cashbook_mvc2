<%@page import="vo.Cash"%>
<%@page import="java.util.List"%>
<%@page import="dao.CashDAO"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cash 목록</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="bg-light">
        <c:import url="/WEB-INF/view/inc/header.jsp"></c:import>
    </div>

    <div class="container mt-5">
        <div class="mb-3">
            <a href="${pageContext.request.contextPath}/cashByMonth" class="btn btn-secondary">월별 목록</a>
            <a href="${pageContext.request.contextPath}/insertCash?cashDate=${cashDate}" class="btn btn-primary">cash 추가</a>
        </div>
        <div>${msg}</div>
        <table class="table table-bordered mt-4">
            <thead>
                <tr>
                    <th>생성날짜</th>
                    <th>수입/지출</th>
                    <th>금액</th>
                    <th>메모</th>
                    <th>수정날짜</th>
                    <th>수정</th>
                    <th>삭제</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="c" items="${list}">
                    <tr>
                        <td>${c.createdate}</td>
                        <td>${c.kind}</td>
                        <td>${c.money}</td>
                        <td>${c.memo}</td>
                        <td>${c.updatedate}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/updateCash?cashNo=${c.cashNo}" class="btn btn-success">수정</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/deleteCash?cashNo=${c.cashNo}&cashDate=${c.cashDate}" class="btn btn-danger">삭제</a>
                        </td>
                    </tr>		
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>