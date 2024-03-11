<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-11-17
  Time: 오후 2:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<nav class="navbar navbar-expand-sm fixed-top" style="background-color: rgb(238,242,251,0.8);">
    <div class="container container-fluid">
        <a class="/index.jsp" href="/board/list.do">
            <img src="../resources/img/cat_icon.png" alt="" width="30" height="24">
        </a>
        <div class="ms-auto">
            <%
                if (session.getAttribute("userId") == null) {
            %>
            <a href="/login/login.do" class="btn btn-outline-primary">로그인</a>
            <%
            } else {
            %>
            <li class="nav-item dropdown" style="list-style: none">
                <a class="navbar-text text-decoration-none dropdown-toggle me-3" href="#" role="button"
                   data-bs-toggle="dropdown" aria-expanded="false">
                    <%=session.getAttribute("userName").toString()%>
                </a>
                <ul class="dropdown-menu text-center">
                    <li><a class="dropdown-item" href="/board/write.do">글쓰기</a></li>
                    <li><a class="dropdown-item" href="/login/profill.do">프로필</a></li>
                    <li><a class="dropdown-item" href="/login/logout.do">로그아웃</a></li>
                </ul>

            </li>
            <%
                }
            %>
        </div>
    </div>
</nav>