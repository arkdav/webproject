<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>AdminBusinessUserCreation</title>
    <script src="${pageContext.request.contextPath}/resources/js/http_code.jquery.com_jquery-3.3.1.slim.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_cdnjs.cloudflare.com_ajax_libs_popper.js_1.14.7_umd_popper.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_stackpath.bootstrapcdn.com_bootstrap_4.3.1_js_bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<%@include file="header.jsp" %>
<main>
    <div class="container">
        <form:form method="POST" modelAttribute="businessUserCreationForm" class="form-signin" cssStyle="width:40%">
            <h2 class="form-signin-heading"><tag:message code="admin.businessusercreation"/></h2>
            <div class="form-group">
                <form:input type="text" path="login" class="form-control" placeholder="Username"/>
                <form:errors path="login" />
            </div>
            <div class="form-group">
                <form:input type="password" path="password" class="form-control" placeholder="Password" />
                <form:errors path="password" />
            </div>
            <div class="form-group">
                <form:input type="password" path="confirmPassword" class="form-control"
                            placeholder="Confirm your password" />
                <form:errors path="confirmPassword" />
            </div>
            <div class="form-group">
                <form:input type="name" path="name" class="form-control"
                            placeholder="Name" />
                <form:errors path="name" />
            </div>
            <div class="form-group" >
                <form:input type="surname" path="surname" class="form-control"
                            placeholder="Surname" />
                <form:errors path="surname" />
            </div>
            <div class="form-group">
                <form:input type="email" path="email" class="form-control"
                            placeholder="Email" />
                <form:errors path="email" />
            </div>
            <div class="form-group ">
                <form:input type="phone" path="phone" class="form-control"
                            placeholder="Phone" />
                <form:errors path="phone" />
            </div>

            <div class="form-group ">
                <form:input type="birthdate" path="birthdate" class="form-control"
                            placeholder="Birth date (dd.mm.yyyy)" />
                <form:errors path="birthdate" />
            </div>

            <button class="btn btn-lg btn-primary btn-block" type="submit"><tag:message code="registration.submit"/></button>
        </form:form>
    </div>
    <div>
        <a href="${pageContext.request.contextPath}/adminusers?u=business"><tag:message code="header.admin.businessusers"/></a>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>
