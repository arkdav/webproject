<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ProductCreation</title>
    <script src="${pageContext.request.contextPath}/resources/js/http_code.jquery.com_jquery-3.3.1.slim.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_cdnjs.cloudflare.com_ajax_libs_popper.js_1.14.7_umd_popper.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_stackpath.bootstrapcdn.com_bootstrap_4.3.1_js_bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div class="product col-4">
<form:form method="POST" modelAttribute="creationBusinessDataForm" class="form-signin"
           enctype="multipart/form-data"  commandName="creationBusinessDataForm"
           action="${pageContext.request.contextPath}/business/create?${_csrf.parameterName}=${_csrf.token}">
    <div class="form-group">
        <tag:message code="businessdata.name"/>
        <form:input type="text" path="name" class="form-control"/>
        <form:errors path="name"/>
    </div>
    <div class="form-group">
        <form:label path="price"><tag:message code="businessdata.price"/></form:label>
        <form:input type="text" path="price" class="form-control"/>
        <form:errors path="price"/>
    </div>
    <div class="form-group">
        <form:label path="description"><tag:message code="businessdata.description"/></form:label>
        <form:input type="text" path="description" class="form-control"/>
        <form:errors path="description"/>
    </div>
    <div class="form-group">
        <form:label path="image"><tag:message code="businessdata.image"/></form:label>
        <form:input type="file" path="image" class="form-control"/>
            <%--            <form:errors path="image" />--%>
    </div>
    <div class="form-group">
        <tag:message code="businessdata.catalogversion"/>
        <form:select size="1" name="catalogVersion" path="catalogVersion" class="form-control">
            <form:option value="online">Online</form:option>
            <form:option value="offline">Offline</form:option>
        </form:select>
    </div>
    <%--        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
    <form:button class="btn btn-lg btn-primary btn-block" type="submit"><tag:message
            code="businessdata.create"/></form:button>
</form:form>
</div>

<a href="${pageContext.request.contextPath}/businessdata">Return to business page</a>
</body>
</html>
