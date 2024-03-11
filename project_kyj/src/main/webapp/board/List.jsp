<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-11-17
  Time: 오전 11:05
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
</head>
<body>
<%@ include file="../layout/Nav.jsp" %>
<%@ include file="../layout/Header.jsp" %>

<main class="container mt-5">
    <div class="mt-5">
        <%--  조회목록 --%>
        <section>
            <div class="row">
                    <table class="table table-hover table-border text-center">
                        <colgroup>
                            <col style="width: 5%">
                            <col style="width: 45%">
                            <col style="width: 15%">
                            <col style="width: 20%">
                            <col style="width: 15%">
                        </colgroup>
                        <thead>
                        <tr>
                            <th>No.</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th>조회수</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${empty baordList}">
                                <tr>
                                    <td colspan="5">등록된 게시물이 없습니다.</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="item" items="${baordList}" varStatus="loop">
                                    <tr>
                                        <td>${item.idx}</td>
                                        <td class="text-start">
                                            <a href="/board/view.do?idx=${item.idx}"
                                               class="text-decoration-none text-dark">${item.title} <strong class="small">[${item.commcnt}]</strong></a>
                                        </td>
                                        <td>${item.name}</td>
                                        <td>${item.postdate}</td>
                                        <td>${item.visitcount}</td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                        </tbody>

                        <tfoot>
                        <tr>
                            <td colspan="5" class="border-bottom-0">
                                <%--                페이지 링크 버튼 --%>
                                <ul class="pagination justify-content-center">
                                    <%--                  이전 페이지 버튼--%>
                                    <c:if test="${startPageNum ne 1}">
                                        <li class="page-item">
                                            <a class="page-link" href="/board/list.do?pageNum=${startPageNum - 1}">&laquo;</a>
                                        </li>
                                    </c:if>
                                    <%--                현재 페이지의 페이지 이동 블록--%>
                                    <c:forEach var="i" begin="${startPageNum}" end="${endPageNum}" step="1">
                                        <c:choose>
                                            <c:when test="${i eq pageNum}">
                                                <li class="page-item active">
                                                    <a class="page-link">${i}</a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item">
                                                    <a class="page-link" href="/board/list.do?pageNum=${i}">${i}</a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    <%--                  다음 페이지 버튼 --%>
                                    <c:if test="${endPageNum lt totalPage}">
                                        <li class="page-item">
                                            <a class="page-link" href="/board/list.do?pageNum=${endPageNum + 1}">&raquo;</a>
                                        </li>
                                    </c:if>
                                </ul>
                            </td>
                        </tr>
                        </tfoot>
                    </table>
                </div>
        </section>
    </div>
</main>

<%@ include file="../layout/Footer.jsp" %>
</body>
</html>
