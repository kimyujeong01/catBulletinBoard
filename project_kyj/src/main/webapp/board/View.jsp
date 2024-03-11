<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-11-17
  Time: 오후 2:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <script src="/resources/js/jquery-3.7.1.js"></script>
    <script src="/resources/js/bootstrap.bundle.js"></script>

    <script>
        $(document).ready(function () {
            $("#card").hide();

            $("#btn-list").on("click", function () {
                location.href = "/board/list.do";
            });

            $("#btn-delete").on("click", function () {
                location.href = "/board/pass.do?mode=delete&idx=${board.idx}&id=${board.id}";
            });

            $("#btn-update").on("click", function () {
                location.href = "/board/pass.do?mode=update&idx=${board.idx}&id=${board.id}";
            });

            $("#dropbtton").on("click", function () {
                $("#card").toggle("show");
            })

        });
    </script>
</head>
<body>
<%@ include file="../layout/Nav.jsp" %>
<%@ include file="../layout/Header.jsp" %>

<main class="container mt-5">
    <section>
        <div class="row">
            <div class="col-sm">
                <c:if test="${not empty board.sfile}">
                    <div class="row mt-3">
                        <div style="text-align: center">
                            <img src="../resources/upload/${board.sfile}">
                        </div>
                    </div>
                </c:if>
                <div class="row mt-3">
                    <div class="col-sm">
                        <input type="text" class="form-control-plaintext text-center fs-1" id="title" name="title"
                               value="${board.title}" readonly>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-sm">
                        <textarea class="form-control-plaintext" id="content" name="content" rows="15"
                                  readonly>${board.content}</textarea>
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-sm justify-content-start">
                        <button class="btn btn-link text-decoration-none dropdown-toggle" type="button" id="dropbtton">
                            댓글
                        </button>
                    </div>
                </div>

                <div class="row mt-3 card mb-2" id="card">
                    <form action="/comm/commwrite.do?idx=${board.idx}" method="post" style="margin: 0;padding: 0;">
                        <div class="card-header bg-light">
                            <i class="fa fa-comment fa"></i> 코멘트
                        </div>
                        <div class="card-body">
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">
                                    <textarea class="form-control" id="commcontent" name="commcontent" placeholder="내용" rows="3"></textarea>
                                    <button type="submit" class="btn btn-dark mt-3">입력</button>
                                </li>
                            </ul>
                        </div>
                    </form>
                </div>

                <div class="card row mt-3">
                    <div class="card-header">댓글 리스트</div>
                    <c:choose>
                        <c:when test="${empty commentList}">
                            <div>댓글이 없습니다.</div>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="comm" items="${commentList}" varStatus="loop">
                                <ul class="list-group">
                                    <li class="list-group-item d-flex justify-content-between">
                                        <div>${comm.content}</div>
                                        <div class="d-flex">
                                            <div class="me-3">${comm.name}</div>
                                            <div class="me-3">${comm.postdate}</div>
                                            <a href="/comm/commdelete.do?idx=${comm.idx}&id=${comm.id}&boardIdx=${board.idx}" class="badge text-decoration-none" style="background-color: red" id="comm-delete">삭제</a>
                                        </div>
                                    </li>
                                </ul>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>


                <div class="row mt-4">
                    <div class="col-sm">
                        <button type="button" class="btn btn-secondary" id="btn-list">목록</button>
                    </div>
                    <div class="col-sm d-flex justify-content-end">
                        <button type="button" class="btn btn-danger me-2" id="btn-delete">삭제</button>
                        <button type="button" class="btn btn-warning" id="btn-update">수정</button>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>


<%@ include file="../layout/Footer.jsp" %>
</body>
</html>
