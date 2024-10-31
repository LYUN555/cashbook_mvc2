<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>관리자 로그인</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="bg-light">
        <c:import url="/WEB-INF/view/inc/header.jsp"></c:import>
    </div>

    <div class="container mt-5">
        <h1 class="text-center">관리자 로그인</h1>
        <div class="alert alert-warning" role="alert">
            ${msg}
        </div>

        <form method="post" action="${pageContext.request.contextPath}/adminLogin">
            <div class="card mt-4">
                <div class="card-body">
                    <table class="table">
                        <tr>
                            <td>관리자 ID</td>
                            <td><input type="text" name="adminId" class="form-control"></td>
                        </tr>
                        <tr>
                            <td>관리자 PW</td>
                            <td><input type="password" name="adminPw" class="form-control"></td>
                        </tr>
                    </table>
                    <button type="submit" class="btn btn-primary w-100">로그인</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>