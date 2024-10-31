<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>공지 입력</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="bg-light">
        <c:import url="/WEB-INF/view/inc/header.jsp"></c:import>
    </div>

    <div class="container mt-5">
        <h1 class="text-center">공지 입력</h1>
        
        <form method="post" action="${pageContext.request.contextPath}/insertNotice">
            <div class="card mt-4">
                <div class="card-body">
                    <table class="table">
                        <tr>
                            <td>작성자</td>
                            <td><input type="text" name="text" value="${adminId}" class="form-control" readonly></td>
                        </tr>        
                        <tr>
                            <td>제목</td>
                            <td><input type="text" name="noticeTitle" class="form-control"></td>
                        </tr>        
                        <tr>
                            <td>내용</td>
                            <td><textarea rows="5" name="noticeContent" class="form-control"></textarea></td>
                        </tr>        
                    </table>
                    <button type="submit" class="btn btn-primary w-100">작성</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>