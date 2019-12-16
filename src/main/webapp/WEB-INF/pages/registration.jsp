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
    <script src="${pageContext.request.contextPath}/resources/js/http_stackpath.bootstrapcdn.com_bootstrap_4.3.1_js_bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_ajax.googleapis.com_ajax_libs_jquery_1.11.2_jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_oss.maxcdn.com_html5shiv_3.7.2_html5shiv.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_oss.maxcdn.com_respond_1.4.2_respond.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<%@include file="header.jsp" %>
<main>
    <div class="container">
        <div class="loginform">
            <form:form method="POST" modelAttribute="userForm" class="form-signin" cssStyle="width:40%">
                <h2 class="form-signin-heading"><tag:message code="header.registration"/></h2>
                <div class="form-group">
                    <form:input type="text" path="login" class="form-control" placeholder="Username"/>
                    <form:errors path="login"/>
                </div>
                <div class="form-group">
                    <form:input type="password" path="password" class="form-control" placeholder="Password"/>
                    <form:errors path="password"/>
                </div>
                <div class="form-group">
                    <form:input type="password" path="confirmPassword" class="form-control"
                                placeholder="Confirm your password"/>
                    <form:errors path="confirmPassword"/>
                </div>
                <div class="form-group">
                    <form:input type="name" path="name" class="form-control"
                                placeholder="Name"/>
                    <form:errors path="name"/>
                </div>
                <div class="form-group">
                    <form:input type="surname" path="surname" class="form-control"
                                placeholder="Surname"/>
                    <form:errors path="surname"/>
                </div>
                <div class="form-group">
                    <form:input type="email" path="email" class="form-control"
                                placeholder="Email"/>
                    <form:errors path="email"/>
                </div>
                <div class="form-group ">
                    <form:input type="phone" path="phone" class="form-control"
                                placeholder="Phone"/>
                    <form:errors path="phone"/>
                </div>
                <div class="form-group ">
                    <form:input type="birthdate" path="birthdate" class="form-control"
                                placeholder="Birth date (dd.mm.yyyy)"/>
                    <form:errors path="birthdate"/>
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit"><tag:message
                        code="registration.submit"/></button>
            </form:form>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>