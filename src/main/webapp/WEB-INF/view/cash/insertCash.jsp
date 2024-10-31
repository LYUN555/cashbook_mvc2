<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cash 입력</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="bg-light">
        <c:import url="/WEB-INF/view/inc/header.jsp"></c:import>
    </div>

    <div class="container mt-5">
        <h1 class="text-center">Cash 입력</h1>
        <div class="text-danger text-center">${msg}</div>
        <div class="mb-3">
            <a href="${pageContext.request.contextPath}/cashByMonth" class="btn btn-secondary">월별 목록</a>
        </div>
        <form method="post" action="${pageContext.request.contextPath}/insertCash">
            <table class="table table-bordered">
                <tr>
                    <td>cashDate</td>
                    <td><input type="text" name="cashDate" value="${cashDate}" readonly class="form-control"></td>
                </tr>
                <tr>
                    <td>kind</td>
                    <td>
                        <input type="radio" name="kind" value="수입" id="income">
                        <label for="income">수입</label>
                        <input type="radio" name="kind" value="지출" id="expense">
                        <label for="expense">지출</label>
                    </td>
                </tr>
                <tr>
                    <td>money</td>
                    <td><input type="number" name="money" class="form-control"></td>
                </tr>
                <tr>
                    <td>memo</td>
                    <td><textarea cols="50" rows="3" name="memo" class="form-control"></textarea></td>
                </tr>
            </table>
            <button type="submit" class="btn btn-primary w-100">입력</button>
        </form>
    </div>
</body>
</html>