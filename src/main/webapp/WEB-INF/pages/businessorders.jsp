<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>BusinessOrders</title>
    <script src="${pageContext.request.contextPath}/resources/js/http_code.jquery.com_jquery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_cdnjs.cloudflare.com_ajax_libs_popper.js_1.14.7_umd_popper.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_stackpath.bootstrapcdn.com_bootstrap_4.3.1_js_bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<%@include file="header.jsp" %>
<nav class="navbar navbar-light bg-light justify-content-between">
    <a class="navbar-brand" id="navbarBrand"><tag:message code="header.businessdata.orders"/></a>
</nav>
<main>
    <div class="main-block">
        <div class="container">
            <div class="row">
                <div class="column-left col-3">
                    <a class="nav-admin-href${sort=='' ? '-now' : ''}"
                       href="${pageContext.request.contextPath}/businessorders"><tag:message
                            code="business.orders"/></a>
                    <a class="nav-admin-href${sort=='col' ? '-now' : ''}"
                       href="${pageContext.request.contextPath}/businessorders?sort=col"><tag:message
                            code="business.orders.status.col"/></a>
                    <a class="nav-admin-href${sort=='proc' ? '-now' : ''}"
                       href="${pageContext.request.contextPath}/businessorders?sort=proc"><tag:message
                            code="business.orders.status.proc"/></a>
                </div>
                <div class="column-right col-9">
                    <c:choose>
                    <c:when test="${!empty businessOrdersList}">
                    <div class="business-orders">
                            <c:forEach items="${businessOrdersList}" var="order">
                                <div class="business-order">
                                    <div class="order-info">
                                        <p><tag:message code="businessorders.orderid"/>${order.orderId}.
                                            <c:if test="${!empty order.orderNote}">
                                                <tag:message code="businessorders.ordernote"/> ${order.orderNote}.
                                            </c:if>
                                            <tag:message code="businessorders.date"/> ${order.date}</p>
                                        <p><tag:message code="businessorders.customer"/> ${order.userDto.login},
                                                ${order.userDto.email}, ${order.userDto.phone}. ${order.userDto.address}</p>
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
                                        <c:if test="${order.orderStatus=='processing'}">
                                        <form class="form-inline my-2 my-lg-0" method="get"
                                              action="${pageContext.request.contextPath}/businessorders/update">
                                            <input type="hidden" name="orderId" value="${order.orderId}">
                                            <button type="submit" class="btn btn-outline-success">
                                                <tag:message code="businessorders.update"/>
                                            </button>
                                        </form>
                                        </c:if>
                                    </div>
                                </div>
                            </c:forEach>
                    </div>
                    <ul class="pagination">
                        <c:if test="${!empty pagesList}">
                            <c:forEach items="${pagesList}" var="mpage">
                                <li class="page-item ${mpage.pageId == page ? 'active' : ''}">
                                    <a class="page-link"
                                       href="${pageContext.request.contextPath}/businessorders?sort=${sort}&page=${page}">
                                            ${mpage.pageId}
                                    </a>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>
                    </c:when>
                    <c:otherwise>
                        <div class="no-items"><tag:message code="order.nosuch"/></div>
                    </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>