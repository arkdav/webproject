<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <script src="${pageContext.request.contextPath}/resources/js/http_code.jquery.com_jquery-3.3.1.slim.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_cdnjs.cloudflare.com_ajax_libs_popper.js_1.14.7_umd_popper.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_stackpath.bootstrapcdn.com_bootstrap_4.3.1_js_bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<%@include file="header.jsp" %>
<nav class="navbar navbar-light bg-light justify-content-between">
    <a class="navbar-brand" id="navbarBrand"><tag:message code="header.cart"/></a>
</nav>
<main>
    <div class="main-block">
        <div class="container-fluid">
            <div class="row" id="user-row">
                <c:if test="${!empty cartList}">
                    <c:choose>
                        <c:when test="${!empty cartList.products}">
                        <div class="left-content col-12">
                            <c:forEach items="${cartList.products}" var="product">
                                <div class="cart-product">
                                    <div class="row">
                                        <div class="cart-product-img col-1">
                                            <img src="<c:url value='/images/${product.productDto.imageLink}'/>" alt="">
                                        </div>
                                        <div class="col-4">
                                            <div class="cart-product-name"><a
                                                    href="${pageContext.request.contextPath}/productdata/${product.productDto.productId}">
                                                <h5>${product.productDto.name}</h5></a></div>
                                            <div><tag:message code="cart.productprice"/> ${product.productDto.price}$
                                            </div>
                                        </div>
                                        <div class="col-3 cart-product-amount">
                                            <form class="" method="get"
                                                  action="${pageContext.request.contextPath}/cartamount">
                                                <label for="productAmount"><tag:message code="cart.amount"/></label>
                                                <input type="hidden" name="product_id"
                                                       value="${product.productDto.productId}">
                                                <input type="text" id="productAmount" name="productAmount"
                                                       placeholder="${product.amount}" min="0" max="10" step="1" onblur="this.parentNode.submit()"/>
                                            </form>
                                        </div>
                                        <div class="col-3">
                                            <form class="form-inline my-2 my-lg-0" method="post"
                                                  action="${pageContext.request.contextPath}/cart?product_id=${product.productDto.productId}">
                                                <input type="hidden" name="${_csrf.parameterName}"
                                                       value="${_csrf.token}"/>
                                                <button type="submit" class="btn btn-outline-success my-2 my-sm-0">
                                                    <tag:message code="product.removeFromCart"/></button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="right-content col-12">
                        <div class="right-content-text">
                            <p><tag:message code="cart.totalprice"/> ${cartList.cartPrice}$</p>
                        </div>
                        <div class="right-content-button">
                            <form method="get" action="${pageContext.request.contextPath}/order">
                                <button type="submit"
                                        class="btn btn-outline-success my-2 my-sm-0"><tag:message
                                        code="cart.checkout"/></button>
                            </form>
                        </div>
                        </c:when>
                        <c:otherwise>
                            <div class="no-items"><tag:message code="cart.noproducts"/></div>
                            <br>
                            <p><tag:message code="cart.tocatalog"/>
                            <a href="${pageContext.request.contextPath}/catalog"><tag:message
                                    code="clickhere"/></a>
                        </c:otherwise>
                    </c:choose>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>