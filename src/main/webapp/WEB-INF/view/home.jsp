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
	<c:if test="${empty email && empty adminId}">
	<h1>로그인</h1>
		<form method = "post" action="${pageContext.request.contextPath}/login">
			<table border="1">
				<tr>
					<td>이메일</td>
					<td><input type = "text" name="email"></td>
				</tr>		
				<tr>
					<td>비밀번호</td>
					<td><input type = "password" name="pw"></td>
				</tr>		
			</table>
			<button type = "submit">로그인</button>
			<a href="${pageContext.request.contextPath}/insertMember">회원가입</a>
		</form>
	</c:if>
	
	<!-- 로그인 상태 표시 -->
	<c:choose>
		<c:when test="${not empty adminId}">
			<div>${adminId}님 반갑습니다.</div>
			<a href="${pageContext.request.contextPath}/memberList">멤버관리</a>
			<a href="${pageContext.request.contextPath}/adminLogout">로그아웃</a>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${not empty email}">
			<div><a href="${pageContext.request.contextPath}/memberOne">${email}님 반갑습니다.</a></div>
			<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
		</c:when>
	</c:choose>
	
	<!-- notice list 출력 -->
	<h1>공지사항</h1>
	<table border ="1">
		<tr>
			<td>번호</td>
			<td>제목</td>
		</tr>
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
					
	</table>
	<!-- 공지사항 페이징 -->
	    <div>
        <c:if test="${currentPage > 1}">
            <a href="${pageContext.request.contextPath}/home?currentPage=1">처음</a>
            <a href="${pageContext.request.contextPath}/home?currentPage=${currentPage - 1}">이전</a>
        </c:if>
        [${currentPage}]
        <c:if test="${currentPage < lastPage}">
            <a href="${pageContext.request.contextPath}/home?currentPage=${currentPage + 1}">다음</a>
            <a href="${pageContext.request.contextPath}/home?currentPage=${lastPage}">끝</a>
        </c:if>
    </div>
	<div>
		<!-- 관리자로 로그인 되어 있다면 -->
		 <c:if test="${not empty adminId}">
        	<a href="${pageContext.request.contextPath}/insertNotice">공지입력</a>
   		 </c:if>			
	</div>
</body>
</html>