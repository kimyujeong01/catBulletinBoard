<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-11-20
  Time: 오후 2:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <script src="/resources/js/bootstrap.bundle.js"></script>
</head>
<body>
<%@ include file="../layout/Nav.jsp" %>
<%@ include file="../layout/Header.jsp" %>

<main class="container mt-5">
    <section>
        <div class="row">
            <div class="col-sm">
                <form action="/board/edit.do" method="post" enctype="multipart/form-data">
                    <div class="row mt-3">
                        <div class="col-sm">
                            <div class="form-floating">
                                <input type="text" class="form-control" id="title" name="title" value="${board.title}"
                                       placeholder="제목을 입력하세요">
                                <label for="title">제목</label>
                            </div>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-sm">
                            <div class="form-floating">
                                <textarea type="text" class="form-control" id="content" name="content"
                                          style="height: 300px;" placeholder="내용을 입력하세요">${board.content}</textarea>
                                <label for="title">내용</label>
                            </div>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-sm">
                            <input type="file" class="form-control" id="uploadfiles" name="uploadfiles">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-sm">
                            <button type="button" class="btn btn-secondary" id="btn-list">목록</button>
                        </div>
                        <div class="col-sm d-flex justify-content-end">
                            <button type="reset" class="btn btn-danger me-2">RESET</button>
                            <button type="submit" class="btn btn-warning">수정</button>
                        </div>
                    </div>
                    <input type="hidden" id="idx" name="idx" value="${board.idx}">
                    <input type="hidden" id="oldSaveFile" name="oldSaveFile" value="${board.sfile}">
                </form>
            </div>
        </div>
    </section>
</main>

<%@ include file="../layout/Footer.jsp" %>
</body>
</html>
