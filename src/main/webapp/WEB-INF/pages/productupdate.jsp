<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ProductUpdate</title>
    <script src="${pageContext.request.contextPath}/resources/js/http_code.jquery.com_jquery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_cdnjs.cloudflare.com_ajax_libs_popper.js_1.14.7_umd_popper.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_stackpath.bootstrapcdn.com_bootstrap_4.3.1_js_bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<%@include file="header.jsp" %>
<nav class="navbar navbar-light bg-light justify-content-between">
    <a class="navbar-brand" id="navbarBrand"><tag:message code="businessdata.change"/></a>
</nav>
<main>
    <div class="container">
        <p class="product-id"><tag:message code="businessdata.id"/>${businessProduct.productId}</p>
        <div class="product-update-form">
            <form:form method="POST" modelAttribute="businessDataForm" class="form-signin"
                       action="${pageContext.request.contextPath}/businessdata/update">
                <div class="form-gr">
                    <form:label path="name"><tag:message code="businessdata.name"/></form:label>
                    <form:input type="text" path="name" class="form-control"
                                placeholder="${businessProduct.name}"/>
                    <form:errors path="name"/>
                </div>
                <div class="form-gr">
                    <form:label path="price"><tag:message code="businessdata.price"/></form:label>
                    <form:input type="text" path="price" class="form-control"
                                placeholder="${businessProduct.price}$"/>
                    <form:errors path="price"/>
                </div>
                <div class="form-gr">
                    <form:label path="description"><tag:message code="businessdata.description"/></form:label>
                    <form:input type="text" path="description" class="form-control"
                                value="${businessProduct.description}"/>
                    <form:errors path="description"/>
                </div>
                <div class="form-gr">
                    <form:label path="catalogVersion"><tag:message
                            code="businessdata.catalogversion"/>${businessProduct.catalogVersion}</form:label>
                    <form:select size="1" name="catalogVersion" path="catalogVersion" class="form-control">
                        <form:option value="online">Online</form:option>
                        <form:option value="offline">Offline</form:option>
                    </form:select>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <form:input type="hidden" path="productId" name="productId" value="${businessProduct.productId}"/>
                <form:button class="btn btn-outline-success btn-block" type="submit"><tag:message
                        code="businessdata.change"/></form:button>
            </form:form>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>
