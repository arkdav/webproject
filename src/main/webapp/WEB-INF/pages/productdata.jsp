<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ProductData</title>
    <script src="${pageContext.request.contextPath}/resources/js/http_code.jquery.com_jquery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_cdnjs.cloudflare.com_ajax_libs_popper.js_1.14.7_umd_popper.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_stackpath.bootstrapcdn.com_bootstrap_4.3.1_js_bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/ajaxfunctions.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<%@include file="header.jsp" %>
<nav class="navbar navbar-light bg-light justify-content-between">
    <a class="navbar-brand" id="navbarBrand"><tag:message code="productdata.title"/></a>
</nav>
<main>
    <div class="main-block" id="main-block-productdata">
        <div class="row">
            <div class="productdata-image col-4">
                <img src="<c:url value='/images/${productinf.imageLink}'/>"></div>
            <div class="productdata-block col-7">
            <h2><tag:message code="productdata.name"/> ${productinf.name}</h2>
            <p><tag:message code="productdata.description"/> ${productinf.description}</p>
            <p><tag:message code="productdata.price"/> ${productinf.price}$</p>
            <br>
            <sec:authorize access="hasRole('ROLE_CUSTOMER')">
                    <button type="button" class="btn btn-outline-success" data-toggle="modal"
                            data-target="#toCartModal">
                        <tag:message code="product.addToCart"/>
                    </button>
                <div class="modal fade" id="toCartModal" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLongTitle"><tag:message
                                        code="addtocart.want"/></h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="loginform" id="tocart">
                                        <form class="form-signin" id="addToCartForm">
                                            <p><tag:message code="productdata.name"/> ${productinf.name}</p>
                                            <p><tag:message
                                                    code="productdata.description"/> ${productinf.description}</p>
                                            <p><tag:message code="productdata.price"/> ${productinf.price}$</p>
                                            <input type="hidden" name="product_id" value="${productinf.productId}">
                                            <p><tag:message code="addtocart.amount"/>
                                                <input type="number" name="amount" min="1" max="10" step="1" value="1">
                                            </p>
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                            <hr>
                                            <button id="add-to-cart-btn" class="btn btn-outline-success" type="button"
                                            onclick="addToCart('${pageContext.request.contextPath}/addtocart','${pageContext.request.contextPath}');"><tag:message code="addtocart.submit"/></button>
                                        </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </sec:authorize>
                <a href="${pageContext.request.contextPath}/catalog"><tag:message
                    code="productdata.tocatalog"/></a>
            </div>
        </div>

    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>
