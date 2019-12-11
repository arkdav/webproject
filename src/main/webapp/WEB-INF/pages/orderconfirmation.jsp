<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order Confirm</title>
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
        <div class="container-fluid">
            <div class="row">
                <div class="left-content col-9">
                    <c:forEach items="${orderList.products}" var="product">
                        <div class="cart-product">
                            <div class="row">
                                <div class="cart-product-img col-1">
                                    <img src="<c:url value='/images/${product.productDto.imageLink}'/>" alt="">
                                </div>
                                <div class="col-4">
                                    <div class="cart-product-name">
                                        <h5>${product.productDto.name}</h5>
                                    </div>
                                    <div><tag:message code="cart.productprice"/> ${product.productDto.price}$</div>
                                </div>
                                <div>
                                    <p> ${product.amount}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <form:form modelAttribute="order" method="post" action="${pageContext.request.contextPath}/order">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <div class="form-group">
                            <form:label path="text"><tag:message code="order.checkout"/></form:label>
                            <form:input type="text" maxlength="220" path="text"/>
                        </div>
                        <br>
                        <p><tag:message code="cart.totalprice"/> ${orderList.cartPrice}$</p>
                        <form:button class="btn btn-outline-success my-2 my-sm-0" type="submit"><tag:message
                                code="cart.checkout"/></form:button>
                    </form:form>
                </div>
                <div class="right-content col-3">
                    <div class="right-content-button">
                        <form method="get" action="${pageContext.request.contextPath}/cart">
                            <button type="submit"
                                    class="btn btn-outline-success my-2 my-sm-0"><tag:message
                                    code="cart.cancel"/>
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>
