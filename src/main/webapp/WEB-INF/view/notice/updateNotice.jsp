<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateNoticeForm</title>
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath}/updateNotice">
		<table border="1">
			<tr>
				<td>공지번호</td>
				<td><input type ="text" name = "noticeNo" value="${notice.noticeNo}" readonly></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type ="text" name = "adminId" value="${notice.adminId}" readonly></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type ="text" name = "noticeTitle" value="${notice.noticeTitle}"></td>
			</tr>		
			<tr>
				<td>내용</td>
				<td><textarea rows="5" cols="50" name="noticeContent">${notice.noticeContent}</textarea></td>
			</tr>	
		</table>
		<button type="submit">수정</button>
	</form>
</body>
</html>