<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>공지사항</title>
</head>
<body>
    <div class="bg-light">
        <c:import url="/WEB-INF/view/inc/header.jsp"></c:import>
    </div>
    
    <div class="container mt-5">
        <!-- notice list 출력 -->
        <h1 class="text-center">공지사항</h1>
        <table class="table table-bordered text-center">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="n" items="${noticeList}">
                    <tr>
                        <td>${n.noticeNo}</td>
                        <td>
                            <c:choose>
                                <c:when test="${empty email && empty adminId}">
                                    ${n.noticeTitle}
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/noticeOne?noticeNo=${n.noticeNo}">
                                        ${n.noticeTitle}
                                    </a>
                                </c:otherwise>
                            </c:choose>
                            <!-- 0 이상일때 댓글 수 표시 -->
                            <c:if test="${commentCountList[n.noticeNo] > 0}">
                                <span>(${commentCountList[n.noticeNo]})</span>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <!-- 공지사항 페이징 -->
        <div class="d-flex justify-content-center">
            <c:if test="${currentPage > 1}">
                <a class="btn btn-secondary me-2" href="${pageContext.request.contextPath}/home?currentPage=1">처음</a>
                <a class="btn btn-secondary me-2" href="${pageContext.request.contextPath}/home?currentPage=${currentPage - 1}">이전</a>
            </c:if>
            <span class="align-self-center">[${currentPage}]</span>
            <c:if test="${currentPage < lastPage}">
                <a class="btn btn-secondary me-2" href="${pageContext.request.contextPath}/home?currentPage=${currentPage + 1}">다음</a>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/home?currentPage=${lastPage}">끝</a>
            </c:if>
        </div>
        
        <div class="text-center mt-3">
            <!-- 관리자로 로그인 되어 있을때만 입력가능 -->
            <c:if test="${not empty adminId}">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/insertNotice">공지입력</a>
            </c:if>			
        </div>
    </div>
</body>
</html>