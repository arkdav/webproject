<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>BusinessOrders</title>
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
            <div class="business-orders">
                <c:if test="${!empty businessOrdersList}">
                    <c:forEach items="${businessOrdersList}" var="order">
                        <div class="business-order-${order.orderStatus} col-5">
                            <div class="order-info">
                                <p><tag:message code="businessorders.orderid"/>${order.orderId}.
                                    <tag:message code="businessorders.ordernote"/> ${order.orderNote}.
                                    <tag:message code="businessorders.date"/> ${order.date}</p>
                                <p><tag:message code="businessorders.customer"/> ${order.userDto.login},
                                        ${order.userDto.email}, ${order.userDto.phone}</p>
                            </div>
                            <div class="order-entry col-9">
                                <c:forEach items="${order.businessOrderProductDto}" var="orderEntry">
                                    <p>
                                        <tag:message
                                                code="businessorders.product"/>
                                        <a href="${pageContext.request.contextPath}/productdata/${orderEntry.productDto.productId}">
                                            ${orderEntry.productDto.name}.
                                        </a>
                                        <tag:message code="businessorders.amount"/> ${orderEntry.amount}.
                                        <tag:message code="businessorders.productprice"/> ${orderEntry.price}$
                                    </p>
                                </c:forEach>
                            </div>
                            <div class="order-total">
                                <p><tag:message code="businessorders.totalprice"/> ${order.price}$</p>
                                <p><tag:message code="businessorders.status"/> ${order.orderStatus}</p>
                            </div>
                            <form class="form-inline my-2 my-lg-0" method="get"
                                  action="${pageContext.request.contextPath}/businessorders/update">
                                <input type="hidden" name="orderId" value="${order.orderId}">
                                <button type="submit" class="btn btn-lg btn-primary btn-block">
                                    <tag:message code="businessorders.update"/>
                                </button>
                            </form>
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