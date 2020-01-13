<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>AdminUserCreation</title>
    <script src="${pageContext.request.contextPath}/resources/js/http_code.jquery.com_jquery-3.3.1.slim.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_cdnjs.cloudflare.com_ajax_libs_popper.js_1.14.7_umd_popper.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_stackpath.bootstrapcdn.com_bootstrap_4.3.1_js_bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<%@include file="header.jsp" %>
<nav class="navbar navbar-light bg-light justify-content-between">
    <a class="navbar-brand" id="navbarBrand"><tag:message code="admin.usercreation"/></a>
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
        <div class="buser-create-form">
            <form:form method="POST" modelAttribute="userCreationForm" class="form-signin">
                <div class="form-group buser">
                    <form:input type="text" path="login" class="form-control" placeholder="${placeholderUsername}"/>
                    <form:errors path="login"/>
                </div>
                <div class="form-group buser">
                    <form:input type="password" path="password" class="form-control" placeholder="${placeholderPass}"/>
                    <form:errors path="password"/>
                </div>
                <div class="form-group buser">
                    <form:input type="password" path="confirmPassword" class="form-control"
                                placeholder="${placeholderPassConfirm}"/>
                    <form:errors path="confirmPassword"/>
                </div>
                <div class="form-group buser">
                    <form:input type="name" path="name" class="form-control"
                                placeholder="${placeholderName}"/>
                    <form:errors path="name"/>
                </div>
                <div class="form-group buser">
                    <form:input type="surname" path="surname" class="form-control"
                                placeholder="${placeholderSurname}"/>
                    <form:errors path="surname"/>
                </div>
                <div class="form-group buser">
                    <form:input type="email" path="email" class="form-control"
                                placeholder="${placeholderEmail}"/>
                    <form:errors path="email"/>
                </div>
                <div class="form-group buser">
                    <form:input type="phone" path="phone" class="form-control"
                                placeholder="${placeholderPhone}"/>
                    <form:errors path="phone"/>
                </div>
                <div class="form-group buser">
                    <form:input type="birthdate" path="birthdate" class="form-control"
                                placeholder="${placeholderBirthdate}"/>
                    <form:errors path="birthdate"/>
                </div>
                <div class="form-group buser">
                    <form:input type="address" path="address" class="form-control"
                                placeholder="${placeholderAddress}"/>
                    <form:errors path="address"/>
                </div>
                <div class="form-group buser">
                    <form:radiobutton path="role" value="b" checked="checked"/>
                    <tag:message code="user.create.businessuser"/>
                    <form:radiobutton path="role" value="u"/>
                    <tag:message code="user.create.customer"/>
                </div>
                <button class="btn btn-outline-success btn-block" type="submit"><tag:message
                        code="registration.submit"/></button>
            </form:form>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>
