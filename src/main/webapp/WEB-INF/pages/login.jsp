<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Log in with your account</title>
    <script src="${pageContext.request.contextPath}/resources/js/http_code.jquery.com_jquery-3.3.1.slim.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_cdnjs.cloudflare.com_ajax_libs_popper.js_1.14.7_umd_popper.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_stackpath.bootstrapcdn.com_bootstrap_4.3.1_js_bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<%@include file="header.jsp" %>
<nav class="navbar navbar-light bg-light justify-content-between">
    <a class="navbar-brand" id="navbarBrand"><tag:message code="header.login"/></a>
</nav>
<main>
    <div class="container">
        <div class="loginform">
            <form method="POST" action="${pageContext.request.contextPath}/login" class="form-signin">
                <div class="form-group ${error != null ? 'has-error' : ''}">
                    <input type="text" name="username" class="form-control"
                           placeholder="<tag:message code="login.username"/>"/>
                    <input type="password" name="password" class="form-control"
                           placeholder="<tag:message code="login.password"/>"/>
                    <c:if test="${!empty error}">
                        <span> <tag:message code="login.error"/> </span>
                    </c:if>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button class="btn btn-outline-success btn-block" type="submit"><tag:message
                            code="header.login"/></button>
                </div>
            </form>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>

<%--<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>--%>

<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%--<%@ taglib prefix="tag" uri="http://www.springframework.org/tags" %>--%>

<%--<body>--%>
<%--<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-hidden="true">--%>
<%--    <div class="modal-dialog modal-dialog-centered" role="document">--%>
<%--        <div class="modal-content">--%>
<%--            <div class="modal-header">--%>
<%--                <h5 class="modal-title" id="exampleModalLongTitle"><tag:message code="header.login"/></h5>--%>
<%--                <button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
<%--                    <span aria-hidden="true">&times;</span>--%>
<%--                </button>--%>
<%--            </div>--%>
<%--            <div class="modal-body">--%>
<%--                <div class="loginform">--%>
<%--                    <form method="POST" id="loginform" action="${pageContext.request.contextPath}/login" class="form-signin"--%>
<%--                          style="width: 40%">--%>
<%--                        <div class="form-group ${error != null ? 'has-error' : ''}">--%>
<%--                            <input type="text" name="username" class="form-control"--%>
<%--                                   placeholder="<tag:message code="login.username"/>"/>--%>
<%--                            <input type="password" name="password" class="form-control"--%>
<%--                                   placeholder="<tag:message code="login.password"/>"/>--%>
<%--                            <c:if test="${!empty error}">--%>
<%--                                <span> <tag:message code="login.error"/> </span>--%>
<%--                            </c:if>--%>
<%--                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
<%--                            <hr>--%>
<%--                            <button class="btn btn-outline-success"--%>
<%--                                    onclick="postLoginData('${pageContext.request.contextPath}/login')"--%>
<%--                            >--%>
<%--                                <tag:message--%>
<%--                                    code="header.login"/></button>--%>
<%--                        </div>--%>
<%--                    </form>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="modal-footer">--%>
<%--                <a href="${pageContext.request.contextPath}/registration" role="button" class="btn btn-secondary">--%>
<%--                    <tag:message code="registration.create"/>--%>
<%--                </a>--%>
<%--                &lt;%&ndash; <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>&ndash;%&gt;--%>
<%--                &lt;%&ndash; <form method="get" action="${pageContext.request.contextPath}/registration">&ndash;%&gt;--%>
<%--                &lt;%&ndash;  <button type="submit" class="btn btn-secondary"><tag:message code="registration.create"/></button>&ndash;%&gt;--%>
<%--                &lt;%&ndash;  </form>&ndash;%&gt;--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>