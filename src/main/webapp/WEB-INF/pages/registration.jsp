<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <script src="${pageContext.request.contextPath}/resources/js/http_code.jquery.com_jquery-3.3.1.slim.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_stackpath.bootstrapcdn.com_bootstrap_4.3.1_js_bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_ajax.googleapis.com_ajax_libs_jquery_1.11.2_jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_oss.maxcdn.com_html5shiv_3.7.2_html5shiv.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_oss.maxcdn.com_respond_1.4.2_respond.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_cdnjs.cloudflare.com_ajax_libs_popper.js_1.14.7_umd_popper.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<%@include file="header.jsp" %>
<nav class="navbar navbar-light bg-light justify-content-between">
    <a class="navbar-brand" id="navbarBrand"><tag:message code="header.registration"/></a>
</nav>
<main>
    <spring:message code="placeholder.username" var="placeholderUsername"/>
    <spring:message code="placeholder.password" var="placeholderPass"/>
    <spring:message code="placeholder.password.confirm" var="placeholderPassConfirm"/>
    <spring:message code="placeholder.name" var="placeholderName"/>
    <spring:message code="placeholder.surname" var="placeholderSurname"/>
    <spring:message code="placeholder.email" var="placeholderEmail"/>
    <spring:message code="placeholder.phone" var="placeholderPhone"/>
    <spring:message code="placeholder.birthdate" var="placeholderBirthdate"/>
    <spring:message code="placeholder.address" var="placeholderAddress"/>
    <div class="container">
        <div class="loginform">
            <form:form method="POST" modelAttribute="registrationForm" class="form-signin">
                <div class="form-group">
                    <form:input type="text" path="login" class="form-control" placeholder="${placeholderUsername}"/>
                    <form:errors path="login"/>
                </div>
                <div class="form-group">
                    <form:input type="password" path="password" class="form-control" placeholder="${placeholderPass}"/>
                    <form:errors path="password"/>
                </div>
                <div class="form-group">
                    <form:input type="password" path="confirmPassword" class="form-control"
                                placeholder="${placeholderPassConfirm}"/>
                    <form:errors path="confirmPassword"/>
                </div>
                <div class="form-group">
                    <form:input type="name" path="name" class="form-control"
                                placeholder="${placeholderName}"/>
                    <form:errors path="name"/>
                </div>
                <div class="form-group">
                    <form:input type="surname" path="surname" class="form-control"
                                placeholder="${placeholderSurname}"/>
                    <form:errors path="surname"/>
                </div>
                <div class="form-group">
                    <form:input type="email" path="email" class="form-control"
                                placeholder="${placeholderEmail}"/>
                    <form:errors path="email"/>
                </div>
                <div class="form-group ">
                    <form:input type="phone" path="phone" class="form-control"
                                placeholder="${placeholderPhone}"/>
                    <form:errors path="phone"/>
                </div>
                <div class="form-group ">
                    <form:input type="birthdate" path="birthdate" class="form-control"
                                placeholder="${placeholderBirthdate}"/>
                    <form:errors path="birthdate"/>
                </div>
                <div class="form-group ">
                    <form:input type="address" path="address" class="form-control"
                                placeholder="${placeholderAddress}"/>
                    <form:errors path="address"/>
                </div>
                <form:input type="hidden" path="role" value="u"/>
                <button class="btn btn-outline-success btn-block" type="submit"><tag:message
                        code="registration.submit"/></button>
            </form:form>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>