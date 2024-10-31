<%@page import="java.util.List"%>
<%@page import="dao.CashDAO"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <h1>${email}님 수입/지출 리스트</h1>
        <h2>${targetYear}년 ${targetMonth}월</h2>
        <div>
            <a href="${pageContext.request.contextPath}/memberOne" class="btn btn-secondary">상세정보</a>
        </div>
        <div class="mt-3">
            <a href="${pageContext.request.contextPath}/cashByMonth?targetYear=${targetYear}&targetMonth=${targetMonth-1}" class="btn btn-primary">이전달</a>
            <a href="${pageContext.request.contextPath}/cashByMonth?targetYear=${targetYear}&targetMonth=${targetMonth+1}" class="btn btn-primary">다음달</a>
        </div>  

        <table class="table table-bordered mt-4">
            <thead>
                <tr>
                    <th>일</th>
                    <th>윌</th>
                    <th>화</th>
                    <th>수</th>
                    <th>목</th>
                    <th>금</th>
                    <th>토</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <c:forEach var="i" begin="1" end="${totalCell}">
                        <td>
                            <c:choose>
                                <c:when test="${i < beginBlank + 1}">
                                    &nbsp;
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${(i - beginBlank) % 7 == 0}">
                                            <div style="color: #FF0000;">
                                                <a href="${pageContext.request.contextPath}/cashByDate?y=${targetYear}&m=${targetMonth}&d=${i - beginBlank}">
                                                    ${i - beginBlank}
                                                </a>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="${pageContext.request.contextPath}/cashByDate?y=${targetYear}&m=${targetMonth}&d=${i - beginBlank}">
                                                ${i - beginBlank}
                                            </a>
                                        </c:otherwise>
                                    </c:choose>
                                    <div>
                                        <c:forEach var="c" items="${list}">
                                            <c:set var="d" value="${c.cashDate.substring(8)}" />
                                            <c:if test="${d == (i - beginBlank)}">
                                                <c:choose>
                                                    <c:when test="${c.kind == '수입'}">
                                                        <div style="color: #0000FF;">
                                                            [${c.kind}] ${c.money}
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${c.kind == '지출'}">
                                                        <div style="color: #FF0000;">
                                                            [${c.kind}] ${c.money}
                                                        </div>
                                                    </c:when>
                                                </c:choose>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <c:if test="${i % 7 == 0}">
                            </tr><tr>
                        </c:if>
                    </c:forEach>
                </tr>
            </tbody>
        </table>
        
        <br>
        
        <h2>${targetMonth}월 수입/지출</h2>
        <table class="table table-bordered">
            <tbody>
                <tr>
                    <td>총 수입</td>
                    <td>${totalIncome} 원</td>
                </tr>
                <tr>
                    <td>총 지출</td>
                    <td>${totalExpense} 원</td>
                </tr>
                <tr>
                    <td>순수익</td>
                    <td>${totalIncome - totalExpense} 원</td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>