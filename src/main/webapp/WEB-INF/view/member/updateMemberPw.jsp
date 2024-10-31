<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>비밀번호 변경</title>
</head>
<body class="d-flex justify-content-center align-items-center vh-100 bg-light">
    <div class="card" style="width: 30rem;">
        <div class="card-body">
            <h5 class="card-title text-center">${email}님의 비밀번호 변경</h5>
            <div class="text-danger text-center mt-2">${msg}</div>
            <form method="post" action="${pageContext.request.contextPath}/updateMemberPw">
                <div class="mb-3">
                    <label for="prepw" class="form-label">현재 비밀번호</label>
                    <input type="password" class="form-control" id="prepw" name="prepw" required>
                </div>
                <div class="mb-3">
                    <label for="newpw" class="form-label">새로운 비밀번호</label>
                    <input type="password" class="form-control" id="newpw" name="newpw" required>
                </div>
                <button type="submit" class="btn btn-primary w-100">비밀번호 변경</button>
            </form>
            <hr class="my-4">
            <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary w-100">홈으로</a>
        </div>
    </div>
</body>
</html>