<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Catalog</title>
    <script src="${pageContext.request.contextPath}/libs/http_code.jquery.com_jquery-3.3.1.slim.js"></script>
    <script src="${pageContext.request.contextPath}/libs/http_cdnjs.cloudflare.com_ajax_libs_popper.js_1.14.7_umd_popper.js"></script>
    <script src="${pageContext.request.contextPath}/libs/http_stackpath.bootstrapcdn.com_bootstrap_4.3.1_js_bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css">
</head>
<body>
<%@include file="header.jsp" %>
<main>
    <div class="main-block">
        <div class="container-fluid">
            <div class="row">
                <div class="catalog col-9">
                    <div class="row">
                        <c:choose>
                            <c:when test="${!empty productsList}">
                                <c:forEach items="${productsList}" var="product">
                                    <div class="product col-4">
                                        <div class="product-img">
                                            <img src="${pageContext.request.contextPath}/images/${product.imageLink}">
                                        </div>
                                        <div>
                                            <h2 class="product-name"><a
                                                    href="${pageContext.request.contextPath}/productdata/${product.productId}">${product.name}</a>
                                            </h2>
                                            <h4 class="product-price">${product.price}$</h4>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${search!=null}">
                                    <p>No products of this type. Please check the entered string.</p>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <ul class="pagination">
                        <c:choose>
                            <c:when test="${search!=null}">
                                <c:if test="${!empty productsList}">
                                    <c:forEach items="${pagesList}" var="mpage">
                                        <li class="page-item">
                                            <a class="page-link"
                                               href="${pageContext.request.contextPath}/catalog?pageid=${mpage.pageId}&searchString=${search}">
                                                    ${mpage.pageId}
                                            </a>
                                        </li>
                                    </c:forEach>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${!empty productsList}">
                                    <c:forEach items="${pagesList}" var="mpage">
                                        <li class="page-item">
                                            <a class="page-link"
                                               href="${pageContext.request.contextPath}/catalog?pageid=${mpage.pageId}">
                                                    ${mpage.pageId}
                                            </a>
                                        </li>
                                    </c:forEach>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
                <div class="sort col-2">
                    <form class="search form-inline my-2 my-lg-0" method="get">
                        <input class="form-control" type="search" name="searchString" aria-label="Search">
                        <button type="submit" class="btn btn-outline-success my-2 my-sm-0"><tag:message
                                code="product.search"/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>
