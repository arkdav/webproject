<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ProductData</title>
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
        <h1><tag:message code="productdata.title"/></h1>
        <br>
        <p><tag:message code="productdata.name"/>  ${productinf.name}</p>
        <p><tag:message code="productdata.description"/> ${productinf.description}</p>
        <p><tag:message code="productdata.price"/> ${productinf.price}$</p>
        <br>
        <p><img src="<c:url value='/images/${productinf.imageLink}'/>"></p>
        <sec:authorize access="hasRole('ROLE_CUSTOMER')">
            <form class="form-inline my-2 my-lg-0" method="post"
                  action="${pageContext.request.contextPath}/addtobasket?product_id=${productinf.productId}">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-outline-success my-2 my-sm-0"><tag:message
                        code="product.addToBasket"/></button>
            </form>
        </sec:authorize>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>
