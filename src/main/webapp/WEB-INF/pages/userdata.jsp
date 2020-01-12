<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>UserData</title>
    <script src="${pageContext.request.contextPath}/resources/js/http_code.jquery.com_jquery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_cdnjs.cloudflare.com_ajax_libs_popper.js_1.14.7_umd_popper.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_stackpath.bootstrapcdn.com_bootstrap_4.3.1_js_bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<%@include file="header.jsp" %>
<nav class="navbar navbar-light bg-light">
    <a class="navbar-brand" id="navbarBrand"><tag:message code="header.userinformation"/></a>
</nav>
<main>
    <div class="main-block">
        <div class="container">
            <h3 class="centered-name"><tag:message code="userdata.login"/> ${currentUser.login}</h3>
            <div class="loginform">
           <form:form method="POST" modelAttribute="userDataForm" class="form-user"
                       action="${pageContext.request.contextPath}/userdata">
                <div class="form-gr">
                    <p><tag:message code="userdata.password"/></p>
                    <form:input type="password" id="password" path="password"/>
                    <form:errors path="password"/>
                </div>
               <div class="form-gr">
                   <p><tag:message code="userdata.confirmpassword"/></p>
                   <form:input type="password" path="confirmPassword"/>
                   <form:errors path="confirmPassword"/>
               </div>
                <div class="form-gr">
                    <p><tag:message code="userdata.name"/></p>
                    <form:input type="name" id="name" path="name" value="${currentUser.name}"/>
                    <form:errors path="name"/>
                </div>
                <div class="form-gr">
                    <p><tag:message code="userdata.surname"/></p>
                    <form:input type="surname" id="surname" path="surname" value="${currentUser.surname}"/>
                    <form:errors path="surname"/>
                </div>
                <div class="form-gr">
                    <p><tag:message code="userdata.email"/></p>
                    <form:input type="email" id="email" path="email" value="${currentUser.email}"/>
                    <form:errors path="email"/>
                </div>
                <div class="form-gr">
                    <p><tag:message code="userdata.phone"/></p>
                    <form:input type="phone" id="phone" path="phone" value="${currentUser.phone}"/>
                    <form:errors path="phone"/>
                </div>
                <div class="form-gr">
                    <p><tag:message code="userdata.birthdate"/></p>
                    <form:input type="birthdate" id="birthdate" path="birthdate" value="${currentUser.birthdate}"/>
                    <form:errors path="birthdate"/>
                </div>
               <div class="form-gr">
                   <p><tag:message code="userdata.address"/></p>
                   <form:input type="address" id="address" path="address" value="${currentUser.address}"/>
                   <form:errors path="address"/>
               </div>
               <div class="form-gr">
                   <button class="btn btn-outline-success btn-block" type="submit"><tag:message
                        code="userdata.change"/></button>
               </div>
            </form:form>
            </div>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>
