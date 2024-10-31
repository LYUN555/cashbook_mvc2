<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원 가입</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex justify-content-center align-items-center vh-100 bg-light">
    <div class="card" style="width: 30rem;">
        <div class="card-body">
            <h5 class="card-title text-center">회원 가입</h5>
            <div class="text-danger text-center mt-2">${msg}</div>
            <form method="post" action="${pageContext.request.contextPath}/emailCheck">
                <div class="mb-3">
                    <label for="ckid" class="form-label">이메일</label>
                    <div class="input-group">
                        <input type="text" id="ckid" name="ckid" class="form-control" value="${ckid}">
                        <button type="submit" class="btn btn-primary">중복체크</button>
                    </div>
                </div>
            </form>
            <hr>
            <form method="post" action="${pageContext.request.contextPath}/insertMember">
                <div class="mb-3">
                    <label for="id" class="form-label">이메일</label>
                    <input type="text" id="id" name="id" class="form-control" value="${empty ckid ? '' : ckid}" readonly>
                </div>
                <div class="mb-3">
                    <label for="repw" class="form-label">비밀번호</label>
                    <input type="password" id="repw" name="repw" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="pw" class="form-label">비밀번호 확인</label>
                    <input type="password" id="pw" name="pw" class="form-control">
                </div>
                <div class="mb-3">
                    <label for="birth" class="form-label">생일</label>
                    <input type="date" id="birth" name="birth" class="form-control">
                </div>
                <button type="submit" class="btn btn-success w-100">회원가입</button>
            </form>
        </div>
    </div>
</body>
</html>