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
<nav class="navbar navbar-light bg-light justify-content-between">
    <a class="navbar-brand" id="navbarBrand"><tag:message code="header.catalog"/></a>
    <div class="btn-group">
        <button id="sortdrop" class="btn btn-outline-success dropdown-toggle" type="button"
                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" title="<tag:message code="catalog.sort"/>">
            <tag:message code="catalog.sort.alphabet.az"/>
        </button>
        <div class="dropdown-menu">
            <a class="dropdown-item"
               onclick="getData('${pageContext.request.contextPath}/catalog?sort=alpaz', '${pageContext.request.contextPath}')"
            ><tag:message code="catalog.sort.alphabet.az"/></a>
            <a class="dropdown-item"
               onclick="getData('${pageContext.request.contextPath}/catalog?sort=alpza', '${pageContext.request.contextPath}')"
            ><tag:message code="catalog.sort.alphabet.za"/></a>
            <a class="dropdown-item"
               onclick="getData('${pageContext.request.contextPath}/catalog?sort=priceaz', '${pageContext.request.contextPath}')"
            ><tag:message code="catalog.sort.price.az"/></a>
            <a class="dropdown-item"
               onclick="getData('${pageContext.request.contextPath}/catalog?sort=priceza', '${pageContext.request.contextPath}')"
            ><tag:message code="catalog.sort.price.za"/></a>
        </div>
    </div>
    <form class="form-inline" id="searchform">
        <input class="form-control" id="searchString" type="text" name="searchString"
               placeholder="<tag:message code="catalog.search.button"/>">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button id="searchButton" type="button" class="btn btn-outline-success"
                onclick="getData('${pageContext.request.contextPath}/catalog', '${pageContext.request.contextPath}')">
            <img src="${pageContext.request.contextPath}/resources/images/search.png"></button>
    </form>
</nav>
<main>
    <div class="main-block">
        <div class="container-fluid">
            <div class="row">
                <div class="catalog col-12" id="catalog">
                    <ul class="pagination" id="pagination1">
                        <c:if test="${!empty pagesList}">
                            <li class="page-item disabled">
                                <a class="page-link"
                                   onclick="getData('${pageContext.request.contextPath}/catalog?pageid=p', '${pageContext.request.contextPath}')">
                                    <tag:message code="catalog.pagination.previous"/>
                                </a>
                            </li>
                            <c:forEach items="${pagesList}" var="mpage">
                                <li class="page-item ${mpage.pageId == 1 ? 'active' : ''}">
                                    <a class="page-link"
                                       onclick="getData('${pageContext.request.contextPath}/catalog?pageid=${mpage.pageId}', '${pageContext.request.contextPath}')">
                                            ${mpage.pageId}
                                    </a>
                                </li>
                            </c:forEach>
                            <li class="page-item">
                                <a class="page-link"
                                   onclick="getData('${pageContext.request.contextPath}/catalog?pageid=n', '${pageContext.request.contextPath}')">
                                    <tag:message code="catalog.pagination.next"/>
                                </a>
                            </li>
                        </c:if>
                        <li class="perpage">
                            <div class="btn-group">
                                <button id="perpagedrop1" class="btn btn-outline-success dropdown-toggle" type="button"
                                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                                        title="<tag:message code="product.amountperpage"/>">
                                    6
                                </button>
                                <div class="dropdown-menu">
                                    <a class="dropdown-item"
                                       onclick="getData('${pageContext.request.contextPath}/catalog?perpage=6&pageid=1', '${pageContext.request.contextPath}')"
                                    >6</a>
                                    <a class="dropdown-item"
                                       onclick="getData('${pageContext.request.contextPath}/catalog?perpage=12&pageid=1', '${pageContext.request.contextPath}')"
                                    >12</a>
                                    <a class="dropdown-item"
                                       onclick="getData('${pageContext.request.contextPath}/catalog?perpage=18&pageid=1', '${pageContext.request.contextPath}')"
                                    >18</a>
                                </div>
                            </div>
                        </li>
                    </ul>
                    <div class="row" id="row">
                        <c:if test="${!empty productsList}">
                            <c:forEach items="${productsList}" var="product">
                                <div class="product col-4" id="product"
                                     onclick="location.href='${pageContext.request.contextPath}/productdata/${product.productId}';">
                                    <div class="product-img" id="pr_image">
                                        <img src="<c:url value='/images/${product.imageLink}'/>" alt="Image not found"/>
                                    </div>
                                    <div>
                                        <h2 class="product-name" id="pr_name">${product.name}</h2>
                                        <h4 class="product-price" id="pr_price">${product.price}$</h4>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                    <ul class="pagination" id="pagination">
                        <c:if test="${!empty pagesList}">
                            <li class="page-item disabled">
                                <a class="page-link"
                                   onclick="getData('${pageContext.request.contextPath}/catalog?pageid=p', '${pageContext.request.contextPath}')">
                                    <tag:message code="catalog.pagination.previous"/>
                                </a>
                            </li>
                            <c:forEach items="${pagesList}" var="mpage">
                                <li class="page-item ${mpage.pageId == 1 ? 'active' : ''}">
                                    <a class="page-link"
                                       onclick="getData('${pageContext.request.contextPath}/catalog?pageid=${mpage.pageId}', '${pageContext.request.contextPath}')">
                                            ${mpage.pageId}
                                    </a>
                                </li>
                            </c:forEach>
                            <li class="page-item">
                                <a class="page-link"
                                    onclick="getData('${pageContext.request.contextPath}/catalog?pageid=n', '${pageContext.request.contextPath}')">
                                    <tag:message code="catalog.pagination.next"/>
                                </a>
                            </li>
                        </c:if>
                        <li class="perpage">
                            <div class="btn-group">
                                <button id="perpagedrop" class="btn btn-outline-success dropdown-toggle" type="button"
                                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                                        title="<tag:message code="product.amountperpage"/>">
                                    6
                                </button>
                                <div class="dropdown-menu">
                                    <a class="dropdown-item"
                                       onclick="getData('${pageContext.request.contextPath}/catalog?perpage=6&pageid=1', '${pageContext.request.contextPath}')"
                                    >6</a>
                                    <a class="dropdown-item"
                                       onclick="getData('${pageContext.request.contextPath}/catalog?perpage=12&pageid=1', '${pageContext.request.contextPath}')"
                                    >12</a>
                                    <a class="dropdown-item"
                                       onclick="getData('${pageContext.request.contextPath}/catalog?perpage=18&pageid=1', '${pageContext.request.contextPath}')"
                                    >18</a>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>
