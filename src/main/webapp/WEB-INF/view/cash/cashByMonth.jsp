<%@page import="vo.Cash"%>
<%@page import="java.util.List"%>
<%@page import="dao.CashDAO"%>
<%@page import="java.util.Calendar"%>
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
	<h1>${email}님 수입/지출 리스트</h1>
	<h2>${targetYear}년${targetMonth}월</h2>
	<div><a href="${pageContext.request.contextPath}/memberOne">상세정보</a></div>
	<div>
		<a href="${pageContext.request.contextPath}/cashByMonth?targetYear=${targetYear}&targetMonth=${targetMonth-1}">이전달</a>
		<a href="${pageContext.request.contextPath}/cashByMonth?targetYear=${targetYear}&targetMonth=${targetMonth+1}">다음달</a>
	</div>	
	
	<table border="1">
		<tr>
			<td>일</td><td>윌</td><td>화</td><td>수</td><td>목</td><td>금</td><td>토</td>
		</tr>
		 <tr>
        <c:forEach var="i" begin="1" end="${totalCell}">
            <td>
                <c:choose>
                    <c:when test="${i < beginBlank + 1}">
                        &nbsp;
                    </c:when>
	                    <c:otherwise>
	                        <a href="${pageContext.request.contextPath}/cashByDate?y=${targetYear}&m=${targetMonth}&d=${i - beginBlank}">
	                            ${i - beginBlank}
	                        </a>
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
    </table>
    
	<br>
	
	<h2>${targetMonth}월 수입/지출</h2>
	<table border ="1">
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
	</table>
</body>
</html>