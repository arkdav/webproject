<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>UserData</title>
    <script src="${pageContext.request.contextPath}/libs/http_code.jquery.com_jquery-3.3.1.slim.js"></script>
    <script src="${pageContext.request.contextPath}/libs/http_cdnjs.cloudflare.com_ajax_libs_popper.js_1.14.7_umd_popper.js"></script>
    <script src="${pageContext.request.contextPath}/libs/http_stackpath.bootstrapcdn.com_bootstrap_4.3.1_js_bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css">
</head>
<body>
<%@include file="header.jsp" %>
<main>
    <div class="main-block">
        <h2><tag:message code="userdata.title"/></h2>
        <p><tag:message code="userdata.login"/> ${currentUser.login}</p>
        <form:form method="POST" modelAttribute="userDataForm" class="form-signin" action="${pageContext.request.contextPath}/userdata">
            <label for="password"><tag:message code="userdata.password"/></label>
        <div class="form-group">
            <form:input type="password" id="password" path="password" class="form-control" placeholder="" />
            <form:errors path="password" />
        </div>
        <label for="name"><tag:message code="userdata.name"/></label>
        <div class="form-group">
            <form:input type="name" id="name" path="name" class="form-control"
                        placeholder="${currentUser.name}" />
            <form:errors path="name" />
        </div>
            <label for="surname"><tag:message code="userdata.surname"/></label>
            <div class="form-group">
            <form:input type="surname" id="surname" path="surname" class="form-control"
                        placeholder="${currentUser.surname}" />
            <form:errors path="surname" />
        </div>
            <label for="email"><tag:message code="userdata.email"/></label>
            <div class="form-group">
            <form:input type="email" id="email" path="email" class="form-control"
                        placeholder="${currentUser.email}" />
            <form:errors path="email" />
        </div>
            <label for="phone"><tag:message code="userdata.phone"/></label>
        <div class="form-group">
            <form:input type="phone" id="phone" path="phone" class="form-control"
                        placeholder="${currentUser.phone}" />
            <form:errors path="phone" />
        </div>
            <label for="birthdate"><tag:message code="userdata.birthdate"/></label>
            <div class="form-group" >
            <form:input type="birthdate" id="birthdate" path="birthdate" class="form-control"
                        placeholder="${currentUser.birthdate}" />
            <form:errors path="birthdate" />
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><tag:message code="userdata.change"/></button>
        </form:form>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>
