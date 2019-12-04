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
                        <div><tag:message code="basket.productprice"/> ${product.productDto.price}$</div>
                    </div>
                    <div>
                        <p> ${product.amount}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div>
        <div>
            <p><tag:message code="basket.totalprice"/> ${orderList.cartPrice}$</p>
        </div>
        <div>
            <form method="post" action="${pageContext.request.contextPath}/order">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit"
                        class="btn btn-outline-success my-2 my-sm-0"><tag:message
                        code="basket.checkout"/>
                </button>
            </form>
            <form method="get" action="${pageContext.request.contextPath}/basket">
                <button type="submit"
                        class="btn btn-outline-success my-2 my-sm-0"><tag:message
                        code="basket.cancel"/>
                </button>
            </form>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>
