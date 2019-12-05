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
    <div class="main-block">
        <div class="container">
            <div class="row">
                <c:if test="${!empty businessProductsList}">
                    <c:forEach items="${businessProductsList}" var="businessProduct">
                        <div class="business-product col-3" id="business-product">
                            <div class="product-img">
                                <img src="<c:url value='/images/${businessProduct.imageLink}'/>"/>
                            </div>
                            <p><tag:message code="businessdata.id"/> ${businessProduct.productId}</p>
                            <p><tag:message code="businessdata.name"/> ${businessProduct.name}</p>
                            <p><tag:message code="businessdata.price"/> ${businessProduct.price}$</p>
                            <p><tag:message code="businessdata.description"/> ${businessProduct.description}</p>
                            <p><tag:message code="businessdata.catalogversion"/> ${businessProduct.catalogVersion}</p>
                            <form class="form-inline my-2 my-lg-0" method="post"
                                  action="${pageContext.request.contextPath}/businessdata/delete?productId=${businessProduct.productId}">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button type="submit" class="btn btn-lg btn-primary btn-block"><tag:message
                                        code="businessdata.delete"/></button>
                            </form>
                            <a href="${pageContext.request.contextPath}/businessdata/update?productId=${businessProduct.productId}">
                                <tag:message code="businessdata.change"/>
                            </a>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>