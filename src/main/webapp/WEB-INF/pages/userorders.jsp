<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>UserOrders</title>
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
        <div class="row">
        <c:if test="${!empty orders}">
                <c:forEach items="${orders}" var="order">
                    <div class="product col-3">
                        <div><tag:message code="order.date"/> ${order.date}</div>
                        <c:forEach items="${order.products}" var="orderEntry">
                            <div><a href="/productdata/${orderEntry.productDto.productId}"><tag:message code="order.product"/> ${orderEntry.productDto.name}<a/></div>
                            <div><tag:message code="order.amount"/> ${orderEntry.amount}</div>
                            <div><tag:message code="order.productprice"/> ${orderEntry.productDto.price}$</div>
                        </c:forEach>
                        <div><tag:message code="order.totalprice"/> ${order.orderPrice}$</div>
                    </div>
                </c:forEach>
        </c:if>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>
