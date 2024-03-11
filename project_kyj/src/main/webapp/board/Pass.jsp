<%@ page import="com.bitc.project_kyj.utils.JSFunction" %><%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-11-14
  Time: 오후 2:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <script src="/resources/js/bootstrap.bundle.js"></script>
</head>
<body>
<c:import url="../layout/Header.jsp"></c:import>

<main class="container mt-5">
    <section>
        <div class="row">
            <div class="col-sm">
                <form action="/board/pass.do" method="post">
                    <div class="row mt-3">
                        <div class="col-sm-4 mx-auto">
                            <div class="input-group">
                                <div class="form-floating">
                                    <input type="password" class="form-control" id="pass" name="pass" >
                                    <label for="pass">패스워드</label>
                                </div>
                                <button type="submit" class="btn btn-primary">확인</button>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" name="mode" value="${mode}">
                    <input type="hidden" name="idx" value="${idx}">
                </form>
            </div>
        </div>
    </section>
</main>

<%@ include file="../layout/Footer.jsp" %>
</body>
</html>
