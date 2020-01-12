<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>AdminData</title>
    <script src="${pageContext.request.contextPath}/resources/js/http_code.jquery.com_jquery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_cdnjs.cloudflare.com_ajax_libs_popper.js_1.14.7_umd_popper.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_stackpath.bootstrapcdn.com_bootstrap_4.3.1_js_bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/ajaxfunctions.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<%@include file="header.jsp" %>
<nav class="navbar navbar-light bg-light">
    <a class="navbar-brand" id="navbarBrand"><tag:message code="header.admindata"/></a>
</nav>
<main>
    <div class="main-block">
        <div class="container">
            <div class="row">
                <div class="column-left col-3">
                    <a class="nav-admin-href${role=='' ? '-now' : ''}" href="${pageContext.request.contextPath}/admin?role=&status=${status}"><tag:message
                            code="header.admin.users"/></a>
                    <a class="nav-admin-href${role=='customer' ? '-now' : ''}" href="${pageContext.request.contextPath}/admin?role=customer&status=${status}"><tag:message
                            code="header.admin.customers"/></a>
                    <a class="nav-admin-href${role=='business' ? '-now' : ''}" href="${pageContext.request.contextPath}/admin?role=business&status=${status}"><tag:message
                            code="header.admin.businessusers"/></a>
                    <hr>
                    <a class="nav-admin-href${status=='' ? '-now': ''}" href="${pageContext.request.contextPath}/admin?role=${role}&status=">
                    <tag:message code="admin.status.two"/></a>
                    <a class="nav-admin-href${status=='active' ? '-now' : ''}" href="${pageContext.request.contextPath}/admin?role=${role}&status=active">
                        <tag:message code="admin.status.active"/></a>
                    <a class="nav-admin-href${status=='inactive' ? '-now': ''}" href="${pageContext.request.contextPath}/admin?role=${role}&status=inactive">
                        <tag:message code="admin.status.inactive"/></a>
                </div>
                <div class="column-right col-9">
                    <c:choose>
                    <c:when test="${!empty userList}">
                        <div class="admin-buttons">
                            <button class="btn btn-outline-success admin-button-upper" onclick="selectAllRadios();">
                                <tag:message code="admin.select.all"/></button>
                            <button class="btn btn-outline-success admin-button-upper" onclick="unselectAllRadios();">
                                <tag:message code="admin.unselect.all"/></button>
                            <span class="borders"></span>
                            <button id="button-admin-first" type="button" onclick="getUsersFor('change');"
                                    class="btn btn-outline-success admin-button-upper" data-toggle="modal" data-target="#changeUser">
                                <tag:message code="adminusers.change"/>
                            </button>
                            <div class="modal fade" id="changeUser" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title"><tag:message
                                                    code="adminusers.change"/></h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="loginform">
                                                <div id="change-users">
                                                    <div id="change-for-now"></div>
                                                </div>
                                                <hr>
                                                <button class="btn btn-outline-success"
                                                        onclick="doGetAdmin('${pageContext.request.contextPath}/admin/changestatus');">
                                                    <tag:message code="adminusers.change"/></button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <button id="button-admin-second" type="button" onclick="getUsersFor('delete');" class="btn btn-outline-success admin-button-upper"
                                    data-toggle="modal" data-target="#deleteUser">
                                <tag:message code="adminusers.delete"/>
                            </button>
                            <div class="modal fade" id="deleteUser" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title"><tag:message
                                                    code="adminusers.userdelete"/></h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="loginform">
                                                    <div id="delete-users">
                                                        <div id="delete-for-now"></div>
                                                    </div>
                                                    <hr>
                                                    <button class="btn btn-outline-success"
                                                            onclick="doGetAdmin('${pageContext.request.contextPath}/admin/delete');">
                                                        <tag:message code="adminusers.delete"/></button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <span class="borders"></span>
                            <form method="get" style="display: contents;" action="${pageContext.request.contextPath}/admin/create">
                            <button type="submit"
                                    class="btn btn-outline-success admin-button-upper">
                                <tag:message code="header.admin.create"/>
                            </button>
                            </form>
                        </div>
                    <table>
                        <tr>
                            <th>&nbsp;</th>
                            <th><tag:message code="adminusers.login"/></th>
                            <th><tag:message code="adminusers.name"/></th>
                            <th><tag:message code="adminusers.surname"/></th>
                            <th><tag:message code="adminusers.phone"/></th>
                            <th><tag:message code="adminusers.email"/></th>
                            <th><tag:message code="adminusers.address"/></th>
                            <th><tag:message code="adminusers.status"/></th>
                            <th>&nbsp;</th>
                        </tr>
                        <c:forEach items="${userList}" var="user">
                            <tr>
                                <td><input class="checkbox" type="checkbox" value="${user.login},${user.status}"></td>
                                <td>${user.login}</td>
                                <td>${user.name}</td>
                                <td>${user.surname}</td>
                                <td>${user.phone}</td>
                                <td>${user.email}</td>
                                <td>${user.address}</td>
                                <td style="background-color: ${user.status == "active" ? 'aliceblue' : 'antiquewhite'};">${user.status} </td>
                                <td>
                                    <form method="get" action="${pageContext.request.contextPath}/admin/update">
                                        <input type="hidden" name="user" value="${user.login}" />
                                        <button type="submit" class="btn btn-outline-success">
                                            <tag:message code="adminusers.update"/>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                    <ul class="pagination" id="pagination">
                        <c:if test="${!empty pagesList}">
                            <c:forEach items="${pagesList}" var="mpage">
                                <li class="page-item ${mpage.pageId == page ? 'active' : ''}">
                                    <a class="page-link" href="${pageContext.request.contextPath}/admin?role=${role}&page=${mpage.pageId}&status=${status}">
                                            ${mpage.pageId}
                                    </a>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>
                    </c:when>
                        <c:otherwise>
                            <div class="no-items"><tag:message code="adminusers.nosuch"/></div>
                        </c:otherwise>
                    </c:choose>
                </div>

            </div>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>