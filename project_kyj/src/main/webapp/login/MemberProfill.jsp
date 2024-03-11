<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-11-20
  Time: 오전 10:31
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
    <link rel="stylesheet" href="/resources/css/Profill.css">
</head>
<body>
<%@ include file="../layout/Nav.jsp" %>

<main class="container mt-5">
    <section>
        <div class="container rounded bg-white mt-5 mb-5">
            <div class="row">
                <div class="col-md-5 border-right">
                    <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                        <div class="mt-lg-5">
                            <img src="../resources/img/catprofill.jpg">
                        </div>
                        <div class="mt-3">
                            <h4 class="font-weight-bold">${member.name}</h4>
                            <p class="text-black-50">${member.email}</p>
                        </div>
                    </div>
                </div>
                <div class="col-md border-right">
                    <div class="p-2 py-5">
                        <div class="d-flex justify-content-between align-items-center mb-4">
                            <h4 class="text-right">${member.name} 프로필</h4>
                        </div>
                        <div class="row mt-3">
                            <div class="col-md-12">
                                <label for="user-name" class="labels">이름</label>
                                <input id="user-name" name="userName" type="text" class="form-control"
                                       placeholder="이름을 입력하세요" value="${member.name}" readonly>
                            </div>
                        </div>
                        <div class="row mt-3">
                            <form action="/login/profill.do" method="post">
                                <div class="col-md-12">
                                    <label for="user-id" class="labels">아이디</label>
                                    <input id="user-id" name="userId" type="text" class="form-control"
                                           placeholder="아이디를 입력하세요" value="${member.id}" readonly></div>
                                <div class="col-md-12 mt-3">
                                    <label for="user-pass" class="labels">비밀번호</label>
                                    <input id="user-pass" name="userPass" type="password" class="form-control"
                                           placeholder="비밀번호를 입력하세요" value="${member.pass}" readonly>
                                </div>
                                <div class="col-md-12 mt-3">
                                    <label for="user-email" class="labels">이메일</label>
                                    <input id="user-email" name="userEmail" type="text" class="form-control"
                                           placeholder="이메일을 입력하세요" value="${member.email}">
                                </div>
                                <div class="col-md-12 mt-3">
                                    <label for="user-phone" class="labels">전화번호 [하이픈(-) 입력하시지 마시오]</label>
                                    <input id="user-phone" name="userPhone" type="text" class="form-control"
                                           placeholder="전화번호를 입력하세요"
                                           value="${member.phone}">
                                </div>
                                <div class="col-md-12 mt-3">
                                    <label for="user-addr" class="labels">주소</label>
                                    <input id="user-addr" name="userAddr" type="text" class="form-control"
                                           placeholder="주소를 입력하세요"
                                           value="${member.addr}">
                                </div>
                                <div class="col-md-12 mt-3">
                                    <label for="user-level" class="labels">등급</label>
                                    <input id="user-level" name="userLevel" type="text" class="form-control" placeholder="기본 등급입니다."
                                           value="${member.level}" readonly>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-sm">
                                        <button class="btn btn-primary profile-button" type="submit">수정</button>
                                    </div>
                                    <div class="col-sm d-flex justify-content-end">
                                        <button class="btn btn-secondary me-2" type="reset">리셋</button>
                                        <a href="/board/list.do" class="btn btn-primary">목록</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="text-center"><a href="/login/memberdelete.do"
                                        class="text-decoration-none text-secondary">-탈퇴-</a></div>
        </div>
    </section>
</main>


</body>
</html>
