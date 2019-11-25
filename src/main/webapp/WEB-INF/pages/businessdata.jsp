<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>BusinessData</title>
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
        <div class="row">
            <div>
                <a href="${pageContext.request.contextPath}/business/create">
                    <tag:message code="businessdata.create"/>
                </a>
            </div>
            <form method="get" class="form-signin" action="${pageContext.request.contextPath}/businessdata">
                <label for="productId"><tag:message code="businessdata.getProduct"/></label>
                <select size="1" id="productId" name="productId" class="form-control">
                    <c:if test="${!empty productIdList}">
                        <c:forEach items="${productIdList}" var="productId">
                            <option value="${productId}">${productId}</option>
                        </c:forEach>
                    </c:if>
                </select>
                <button type="submit" class="btn btn-lg btn-primary btn-block">
                    <tag:message code="businessdata.submit"/>
                </button>
            </form>
            <c:if test="${businessProduct!=null}">
                <div class="product col-3">
                    <h><tag:message code="businessdata.id"/>${businessProduct.productId}</h>
                    <form:form method="POST" modelAttribute="businessDataForm" class="form-signin"
                               action="${pageContext.request.contextPath}/business/update">
                        <div class="form-group">
                            <form:label path="name"><tag:message code="businessdata.name"/></form:label>
                            <form:input type="text" path="name" class="form-control"
                                        placeholder="${businessProduct.name}"/>
                            <form:errors path="name"/>
                        </div>
                        <div class="form-group">
                            <form:label path="price"><tag:message code="businessdata.price"/></form:label>
                            <form:input type="text" path="price" class="form-control"
                                        placeholder="${businessProduct.price}$"/>
                            <form:errors path="price"/>
                        </div>
                        <div class="form-group">
                            <form:label path="description"><tag:message code="businessdata.description"/></form:label>
                            <form:input type="text" path="description" class="form-control"
                                        placeholder="${businessProduct.description}"/>
                            <form:errors path="description"/>
                        </div>
                        <div class="form-group">
                            <form:label path="catalogVersion"><tag:message
                                    code="businessdata.catalogversion"/>${businessProduct.catalogVersion}</form:label>
                            <form:select size="1" name="catalogVersion" path="catalogVersion" class="form-control">
                                <form:option value="online">Online</form:option>
                                <form:option value="offline">Offline</form:option>
                            </form:select>
                        </div>
                        <form:input type="hidden" path="productId" name="productId" value="${businessProduct.productId}"/>
                        <form:button class="btn btn-lg btn-primary btn-block" type="submit"><tag:message
                                code="businessdata.change"/></form:button>
                    </form:form>
                    <form class="form-inline my-2 my-lg-0" method="post"
                          action="${pageContext.request.contextPath}/business/delete?productId=${businessProduct.productId}">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-lg btn-primary btn-block"><tag:message
                                code="businessdata.delete"/></button>
                    </form>
                </div>
            </c:if>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>