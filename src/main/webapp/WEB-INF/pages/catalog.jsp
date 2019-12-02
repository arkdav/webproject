<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Catalog</title>
    <script src="${pageContext.request.contextPath}/resources/js/http_code.jquery.com_jquery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_cdnjs.cloudflare.com_ajax_libs_popper.js_1.14.7_umd_popper.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/http_stackpath.bootstrapcdn.com_bootstrap_4.3.1_js_bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/ajaxfunctions.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<%@include file="header.jsp" %>
<main>
    <div class="main-block">
        <div class="container-fluid">
            <div class="row">
                <div class="catalog col-9" id="catalog">
                    <div class="row" id="row">
                        <c:if test="${!empty productsList}">
                            <c:forEach items="${productsList}" var="product">
                                <div class="product col-4" id="product">
                                    <div class="product-img" id="pr_image">
                                        <img src="<c:url value='/images/${product.imageLink}'/>"/>
                                    </div>
                                    <div>
                                        <h2 class="product-name">
                                            <a id="pr_name"
                                               href="${pageContext.request.contextPath}/productdata/${product.productId}">
                                                    ${product.name}</a>
                                        </h2>
                                        <h4 id="pr_price" class="product-price">${product.price}$</h4>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                    <ul class="pagination" id="pagination">
                        <c:if test="${!empty pagesList}">
                            <c:forEach items="${pagesList}" var="mpage">
                                <li class="page-item">
                                    <a class="page-link"
                                       onclick="getData('${pageContext.request.contextPath}/catalog?pageid=${mpage.pageId}', '${pageContext.request.contextPath}')">
                                            ${mpage.pageId}
                                    </a>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>
                </div>
                <div class="sort col-3">
                    <div class="catalog-search">
                        <form class="search form-inline my-2 my-lg-0" id="searchform">
                            <input class="form-control" id="searchString" type="text" name="searchString"
                                   aria-label="Search">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input id="search-button" class="btn btn-outline-success"
                                   onclick="getData('${pageContext.request.contextPath}/catalog', '${pageContext.request.contextPath}')"
                                value="<tag:message code="product.search" />"/>
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
