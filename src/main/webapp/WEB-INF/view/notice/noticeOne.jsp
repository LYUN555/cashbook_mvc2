<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>공지</h1>
	<div><a href="${pageContext.request.contextPath}/home">처음 화면으로</a></div>
	<table border = "1">
		<tr>
			<td>공지번호</td>
			<td>${notice.noticeNo}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${notice.adminId}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${notice.noticeTitle}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${notice.noticeContent}</td>
		</tr>
		<tr>
			<td>생성날짜</td>
			<td>${notice.createdate}</td>
		</tr>
		<tr>
			<td>수정날짜</td>
			<td>${notice.updatedate}</td>
		</tr>
	</table>
	<div>
		<!-- 공지글 수정 삭제 : 관리자에게만 노출 -->
		<c:if test="${adminId != null}">
			<a href="${pageContext.request.contextPath}/updateNotice?noticeNo=${notice.noticeNo}">수정</a>
			<a href="${pageContext.request.contextPath}/deleteNotice?noticeNo=${notice.noticeNo}">삭제</a>
		</c:if>			
	</div>
	
	<br>
	
	<div>
	<div>${msg}</div>
		<!-- 이 공지글의 댓글 입력 폼 -->
		<form method="post" action="${pageContext.request.contextPath}/insertComment">
			<input type ="hidden" name="noticeNo" value="${notice.noticeNo}">
			<table border="1">
				<tr>
					 <td>
    				    <input type="text" name="commentWriter" value="${empty adminId ? email : adminId }" readonly>
   					 </td>
				</tr>
				<tr>
					<td>댓글을 입력하세요</td>
					<td><textarea rows="3" cols="50" name="commentContent"></textarea> </td>
				</tr>
						
			</table>
			<button type="submit">댓글입력</button>
		</form>
	</div>
	<div>
		<!-- 이 공지글의 댓글 리스트 -->
		<h2>댓글</h2>
		<table border="1">
			<tr>	
				<td>작성자</td>
				<td>내용</td>
				<td>날짜</td>
				<td>삭제</td>
			</tr>
			<c:forEach var="c" items="${commentList}">
				<tr>
					<td>${c.commentWriter}</td>
					<td>${c.commentContent}</td>
					<td>${c.createdate}</td>
					<td>
					    <c:choose>
					        <c:when test="${c.commentWriter.equals(email) || adminId != null}">
					            <a href="${pageContext.request.contextPath}/deleteComment?noticeNo=${c.noticeNo}&commentNo=${c.commentNo}">삭제</a>
					        </c:when>
					        <c:otherwise>
					            삭제
					        </c:otherwise>
					    </c:choose>
					</td>
				</tr>
			</c:forEach>			
		</table>
		<!-- 댓글 페이징 -->
		<div>
		<c:if test="${currentPage > 1}">
			<a href="${pageContext.request.contextPath}/noticeOne?noticeNo=${noticeNo}&currentPage=1">처음</a>
			<a href="${pageContext.request.contextPath}/noticeOne?noticeNo=${noticeNo}&currentPage=${currentPage-1}">이전</a>
		</c:if>
		[${currentPage}]
		<c:if test="${currentPage < lastPage}">		
			<a href="${pageContext.request.contextPath}/noticeOne?noticeNo=${noticeNo}&currentPage=${currentPage+1}">다음</a>
			<a href="${pageContext.request.contextPath}/noticeOne?noticeNo=${noticeNo}&currentPage=${lastPage}">끝</a>
		</c:if>				
	</div>
	</div>
</body>
</html>