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
            <h5 class="card-title text-center">${email}님의 비밀번호 변경(관리자 모드)</h5>
            <div class="text-danger text-center mt-2">${msg}</div>
            <form method="post" action="${pageContext.request.contextPath}/memberPwUpdate">
                <div class="mb-3">
                    <label for="email" class="form-label">이메일</label>
                    <input type="text" class="form-control" id="email" name="email" value="${email}" readonly>
                </div>
                <div class="mb-3">
                    <label for="pw" class="form-label">새 비밀번호</label>
                    <input type="password" class="form-control" id="pw" name="pw" required>
                </div>
                <button type="submit" class="btn btn-primary w-100">변경</button>
            </form>
        </div>
    </div>
</body>
</html>