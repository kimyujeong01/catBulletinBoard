<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-11-17
  Time: 오후 3:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <script src="/resources/js/bootstrap.bundle.js"></script>
    <link rel="stylesheet" href="/resources/css/Login.css">
</head>
<body>
<main class="container mt-5">
    <section>
        <div class="login-wrap">
            <div class="login-html">
                <input id="tab-1" type="radio" name="tab" class="sign-in" checked>
                <label for="tab-1" class="tab">로그인</label>
                <input id="tab-2" type="radio" name="tab" class="sign-up">
                <label for="tab-2" class="tab">회원가입</label>

                <div class="login-form">
                    <form action="/login/login.do" method="post">
                        <div class="sign-in-htm">
                            <div class="group">
                                <label for="user-id" class="label">ID</label>
                                <input id="user-id" name="userId" type="text" class="input">
                            </div>
                            <div class="group">
                                <label for="user-pass" class="label">Password</label>
                                <input id="user-pass" name="userPass" type="password" class="input" data-type="password">
                            </div>
                            <div class="group mt-5">
                                <input type="submit" class="button" value="Sign In">
                            </div>
                            <div class="hr"></div>
                            <div class="foot-lnk">
                                <a href="/board/list.do">돌아가기</a>
                            </div>
                        </div>
                    </form>

                    <form action="/login/membership.do" method="post">
                        <div class="sign-up-htm">
                            <div class="group">
                                <label for="name" class="label">Name</label>
                                <input id="name" name="Name" type="text" class="input">
                            </div>
                            <div class="group">
                                <label for="id" class="label">ID</label>
                                <input id="id" name="Id" type="text" class="input">
                            </div>
                            <div class="group">
                                <label for="pass" class="label">Password</label>
                                <input id="pass"  name="Pass" type="password" class="input" data-type="password">
                            </div>
                            <div class="group">
                                <label for="email" class="label">Email</label>
                                <input id="email" name="Email" type="text" class="input">
                            </div>
                            <div class="group">
                                <label for="phone" class="label">Phone</label>
                                <input id="phone" name="Phone" type="text" class="input">
                            </div>
                            <div class="group">
                                <label for="addr" class="label">address</label>
                                <input id="addr" name="Addr" type="text" class="input">
                            </div>
                            <div class="group mt-5">
                                <input type="submit" class="button" value="Sign Up">
                            </div>
                            <div class="hr"></div>
                            <div class="foot-lnk">
                                <label for="tab-1">이미 회원입니까?</label>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </section>
</main>
</body>
</html>
