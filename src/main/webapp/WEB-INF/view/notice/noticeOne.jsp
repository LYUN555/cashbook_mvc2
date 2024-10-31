<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>공지사항 상세보기</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="bg-light">
        <c:import url="/WEB-INF/view/inc/header.jsp"></c:import>
    </div>

    <div class="container mt-5">
        <h1 class="text-center">공지사항</h1>
        <table class="table table-bordered text-center">
            <tr>
                <th>공지번호</th>
                <td>${notice.noticeNo}</td>
            </tr>
            <tr>
                <th>작성자</th>
                <td>${notice.adminId}</td>
            </tr>
            <tr>
                <th>제목</th>
                <td>${notice.noticeTitle}</td>
            </tr>
            <tr>
                <th>내용</th>
                <td>${notice.noticeContent}</td>
            </tr>
            <tr>
                <th>생성날짜</th>
                <td>${notice.createdate}</td>
            </tr>
            <tr>
                <th>수정날짜</th>
                <td>${notice.updatedate}</td>
            </tr>
        </table>

        <div class="text-center">
            <!-- 공지글 수정 삭제 : 관리자에게만 노출 -->
            <c:if test="${adminId != null}">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/updateNotice?noticeNo=${notice.noticeNo}">수정</a>
                <a class="btn btn-danger" href="${pageContext.request.contextPath}/deleteNotice?noticeNo=${notice.noticeNo}">삭제</a>
            </c:if>			
        </div>

        <br>

        <div>
            <div>${msg}</div>
            <!-- 댓글 입력 폼 -->
            <form method="post" action="${pageContext.request.contextPath}/insertComment">
                <input type="hidden" name="noticeNo" value="${notice.noticeNo}">
                <div class="mb-3">
                    <label class="form-label">작성자</label>
                    <input type="text" name="commentWriter" value="${empty adminId ? email : adminId }" readonly class="form-control">
                </div>
                <div class="mb-3">
                    <label class="form-label">댓글을 입력하세요</label>
                    <textarea rows="3" cols="50" name="commentContent" class="form-control"></textarea>
                </div>
                <button type="submit" class="btn btn-primary">댓글 입력</button>
            </form>
        </div>

        <div>
            <!-- 댓글 리스트 -->
            <h2 class="mt-4">댓글</h2>
            <table class="table table-bordered text-center">
                <thead>
                    <tr>
                        <th>작성자</th>
                        <th>내용</th>
                        <th>날짜</th>
                        <th>삭제</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="c" items="${commentList}">
                        <tr>
                            <td>${c.commentWriter}</td>
                            <td>${c.commentContent}</td>
                            <td>${c.createdate}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${c.commentWriter.equals(email) || adminId != null}">
                                        <a href="${pageContext.request.contextPath}/deleteComment?noticeNo=${c.noticeNo}&commentNo=${c.commentNo}">X</a>
                                    </c:when>
                                    <c:otherwise>
                                        X
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>			
                </tbody>
            </table>

            <!-- 댓글 페이징 -->
            <div class="d-flex justify-content-center">
                <c:if test="${currentPage > 1}">
                    <a class="btn btn-secondary me-1" href="${pageContext.request.contextPath}/noticeOne?noticeNo=${noticeNo}&currentPage=1">처음</a>
                    <a class="btn btn-secondary me-1" href="${pageContext.request.contextPath}/noticeOne?noticeNo=${noticeNo}&currentPage=${currentPage-1}">이전</a>
                </c:if>
                <span class="align-self-center">[${currentPage}]</span>
                <c:if test="${currentPage < lastPage}">		
                    <a class="btn btn-secondary ms-1" href="${pageContext.request.contextPath}/noticeOne?noticeNo=${noticeNo}&currentPage=${currentPage+1}">다음</a>
                    <a class="btn btn-secondary ms-1" href="${pageContext.request.contextPath}/noticeOne?noticeNo=${noticeNo}&currentPage=${lastPage}">끝</a>
                </c:if>				
            </div>
        </div>
    </div>
</body>
</html>