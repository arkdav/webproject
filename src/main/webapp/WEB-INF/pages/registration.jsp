<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="../../style/style.css">
</head>
<body>
<div class="container">
    <form:form method="POST" modelAttribute="userForm" class="form-signin" cssStyle="width:40%">
        <h2 class="form-signin-heading"><tag:message code="header.registration"/></h2>
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="login" class="form-control" placeholder="Username" />
                <form:errors path="login" />
            </div>
            <div class="form-group" ${status.error ? 'has-error' : ''}>
                <form:input type="password" path="password" class="form-control" placeholder="Password" />
                <form:errors path="password" />
            </div>
            <div class="form-group " ${status.error ? 'has-error' : ''}>
                <form:input type="password" path="confirmPassword" class="form-control"
                            placeholder="Confirm your password" />
                <form:errors path="confirmPassword" />
            </div>
            <div class="form-group " ${status.error ? 'has-error' : ''}>
                <form:input type="name" path="name" class="form-control"
                            placeholder="Name" />
                <form:errors path="name" />
            </div>
            <div class="form-group " ${status.error ? 'has-error' : ''}>
                <form:input type="surname" path="surname" class="form-control"
                            placeholder="Surname" />
                <form:errors path="surname" />
            </div>
            <div class="form-group " ${status.error ? 'has-error' : ''}>
                <form:input type="email" path="email" class="form-control"
                            placeholder="Email" />
                <form:errors path="email" />
            </div>
            <div class="form-group " ${status.error ? 'has-error' : ''}>
                <form:input type="phone" path="phone" class="form-control"
                            placeholder="Phone" />
                <form:errors path="phone" />
            </div>

            <div class="form-group " ${status.error ? 'has-error' : ''}>
                <form:input type="birthdate" path="birthdate" class="form-control"
                            placeholder="Birth date (dd.mm.yyyy)" />
                <form:errors path="birthdate" />
            </div>

        <button class="btn btn-lg btn-primary btn-block" type="submit"><tag:message code="registration.submit"/></button>
    </form:form>
</div>
</body>
</html>