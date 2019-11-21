<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Log in with your account</title>
    <script src="${pageContext.request.contextPath}/libs/http_code.jquery.com_jquery-3.3.1.slim.js"></script>
    <script src="${pageContext.request.contextPath}/libs/http_cdnjs.cloudflare.com_ajax_libs_popper.js_1.14.7_umd_popper.js"></script>
    <script src="${pageContext.request.contextPath}/libs/http_stackpath.bootstrapcdn.com_bootstrap_4.3.1_js_bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css">
</head>
<body>
<div class="container">
    <form method="POST" action="${pageContext.request.contextPath}/login" class="form-signin" style="width: 40%">
        <h2 class="form-heading"></h2>
        <div class="form-group ${error != null ? 'has-error' : ''}">
            <input type="text" name="username" class="form-control" placeholder="<tag:message code="login.username"/>"/>
            <input type="password" name="password" class="form-control" placeholder="<tag:message code="login.password"/>"/>
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button class="btn btn-lg btn-primary btn-block" type="submit"><tag:message code="header.login"/></button>
        </div>
    </form>
</div>
</body>
</html>