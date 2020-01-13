<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
<nav class="navbar navbar-light bg-light justify-content-between">
    <a class="navbar-brand" id="navbarBrand"><tag:message code="header.userorders"/></a>
</nav>
<main>
    <div class="main-block">
        <div class="container">
            <div class="row">
                <div class="column-left col-3" style="padding: 10px;">
                    <jsp:useBean id="now" class="java.util.Date"/>
                    <form class="form-signin" id="date-form" method="get"
                          action="${pageContext.request.contextPath}/userorders">
                        <c:choose>
                            <c:when test="${dateFrom==''}">
                                <p><input type="date" name="dateFrom" value="2019-10-01"
                                          min="2015-01-01" max="2021-01-01"></p>
                            </c:when>
                            <c:otherwise>
                                <p><input type="date" name="dateFrom" value="${dateFrom}"
                                          min="2015-01-01" max="2021-01-01"></p>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${dateTo==''}">
                                <p><input type="date" name="dateTo"
                                          value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>"
                                          min="2015-01-01" max="2021-01-01"></p>
                            </c:when>
                            <c:otherwise>
                                <p><input type="date" name="dateTo" value="${dateTo}"
                                          min="2015-01-01" max="2021-01-01"></p>
                            </c:otherwise>
                        </c:choose>

                        <button class="btn btn-outline-success" type="submit"><tag:message
                                code="businessdata.submit"/></button>
                    </form>
                </div>
                <div class="column-right col-9">
                    <c:choose>
                        <c:when test="${!empty orders}">
                            <div class="userorders">
                                <c:forEach items="${orders}" var="order">
                                    <div class="user-order" >
                                        <table>
                                            <tr>
                                                <td><tag:message code="order.date"/> ${order.date}</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <c:forEach items="${order.products}" var="orderEntry">
                                                <tr>
                                                <td>&nbsp;</td>
                                                <td><tag:message code="order.product"/>
                                                    <a href="${pageContext.request.contextPath}/productdata/${orderEntry.productDto.productId}"> ${orderEntry.productDto.name}</a>
                                                    x ${orderEntry.amount}
                                                </td>
                                                <td>&nbsp;</td>
                                                </tr>
                                            </c:forEach>
                                            <tr style="background-color: ${order.status =='collected' ? 'aliceblue' : 'antiquewhite'}">
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td><tag:message code="order.status"/> ${order.status}</td>
                                            </tr>
                                            <c:if test="${!empty order.orderNote}">
                                                <tr>
                                                    <td>&nbsp;</td>
                                                    <td>&nbsp;</td>
                                                    <td><tag:message code="order.note"/> ${order.orderNote}</td>
                                                </tr>
                                            </c:if>
                                            <tr>
                                                <td colspan="3"><tag:message
                                                        code="order.totalprice"/> ${order.orderPrice}$
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </c:forEach>
                            </div>
                            <ul class="pagination">
                                <c:if test="${!empty pagesList}">
                                    <c:forEach items="${pagesList}" var="mpage">
                                        <li class="page-item ${mpage.pageId == page ? 'active' : ''}">
                                            <a class="page-link"
                                               href="${pageContext.request.contextPath}/userorders?page=${page}">
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
</main>
<%@include file="footer.jsp" %>
</body>
</html>
