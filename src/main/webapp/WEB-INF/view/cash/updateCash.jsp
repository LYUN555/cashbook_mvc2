<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>cash 수정</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="bg-light">
        <c:import url="/WEB-INF/view/inc/header.jsp"></c:import>
    </div>

    <div class="container mt-5">
        <div>${msg}</div>
        <h1>cash 수정</h1>
        <form method="post" action="${pageContext.request.contextPath}/updateCash">
            <table class="table table-bordered">
                <tr>
                    <td>cashDate</td>
                    <td><input type="text" name="cashDate" value="${cash.cashDate}" readonly class="form-control"></td>
                </tr>
                <tr>
                    <td>cashNo</td>
                    <td><input type="text" name="cashNo" value="${cash.cashNo}" readonly class="form-control"></td>
                </tr>			
                <tr>
                    <td>kind</td>
                    <td>
                    <div>
					    <input type="radio" name="kind" value="수입" id="income" 
					           <c:if test="${cash.kind == '수입'}">checked</c:if>
					    >
					    <label for="income">수입</label>
					</div>
					<div>
					    <input type="radio" name="kind" value="지출" id="expense" 
					           <c:if test="${cash.kind == '지출'}">checked</c:if>
					    >
					    <label for="expense">지출</label>
					</div>
                    </td>
                </tr>
                <tr>
                    <td>money</td>
                    <td><input type="number" name="money" value="${cash.money}" class="form-control"></td>
                </tr>
                <tr>
                    <td>memo</td>
                    <td>
                        <textarea cols="50" rows="3" name="memo" class="form-control">${cash.memo}</textarea>
                    </td>
                </tr>
            </table>
            <button type="submit" class="btn btn-primary">수정</button>
        </form>
    </div>
</body>
</html>
