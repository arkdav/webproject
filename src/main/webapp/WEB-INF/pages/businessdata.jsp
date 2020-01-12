<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>BusinessData</title>
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
    <a class="navbar-brand" id="navbarBrand"><tag:message code="header.businessdata.catalog"/></a>
    <button class="btn btn-outline-success" onclick="selectAllRadios();">
        <tag:message code="admin.select.all"/></button>
    <button class="btn btn-outline-success" onclick="unselectAllRadios();">
        <tag:message code="admin.unselect.all"/></button>
    <span class="borders"></span>
    <button type="button" onclick="getProductsForBusinessUser('change');" class="btn btn-outline-success" data-toggle="modal" data-target="#changeStatusProduct">
        <tag:message code="businessdata.change.status"/>
    </button>
    <div class="modal fade" id="changeStatusProduct" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title"><tag:message
                            code="adminusers.change"/></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="loginform">
                        <div id="change-products">
                            <div id="change-for-now"></div>
                        </div>
                        <hr>
                        <button class="btn btn-outline-success"
                                onclick="doGetBusiness('${pageContext.request.contextPath}/businessdata/changeversion');">
                            <tag:message code="adminusers.change"/></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <button type="submit" class="btn btn-outline-success" data-toggle="modal"
            data-target="#deleteProducts" onclick="getProductsForBusinessUser('delete');">
        <tag:message code="businessdata.delete"/>
    </button>
    <div class="modal fade" id="deleteProducts" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title"><tag:message
                            code="businessdata.delete"/></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="loginform">
                        <div id="delete-products">
                            <div id="delete-for-now"></div>
                        </div>
                        <hr>
                        <button class="btn btn-outline-success"
                                onclick="doGetBusiness('${pageContext.request.contextPath}/businessdata/delete');">
                            <tag:message code="businessdata.delete"/></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <span class="borders"></span>
    <form class="form-inline" name="get" action="${pageContext.request.contextPath}/businessdata/create">
        <button type="submit" class="btn btn-outline-success">
            <tag:message code="header.businessdata.create"/>
        </button>
    </form>
    <span class="borders"></span>
    <div class="btn-group">
        <button class="btn btn-outline-success dropdown-toggle" type="button"
                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <c:choose>
            <c:when test="${version==''}">
                <tag:message code="businessdata.sort.version"/>
            </c:when>
                <c:otherwise>
                    ${version}
                </c:otherwise>
            </c:choose>
        </button>
        <div class="dropdown-menu">
            <a class="dropdown-item"
               href="${pageContext.request.contextPath}/businessdata?search=${search}&version=online">
                <tag:message code="businessdata.sort.version.on"/></a>
            <a class="dropdown-item"
               href="${pageContext.request.contextPath}/businessdata?search=${search}&version=offline">
                <tag:message code="businessdata.sort.version.off"/></a>
            <a class="dropdown-item"
               href="${pageContext.request.contextPath}/businessdata?search=${search}">
                <tag:message code="businessdata.sort.version.two"/></a>
        </div>
    </div>
    <form class="form-inline" method="get" id="searchform">
        <input class="form-control" style="width: 200px;" id="search" type="text" name="search"
               placeholder="<tag:message code="catalog.search.button"/>" value="${search}">
        <button id="searchButton" type="submit" class="btn btn-outline-success">
            <img src="${pageContext.request.contextPath}/resources/images/search.png"></button>
    </form>
</nav>
<main>
    <div class="main-block">
        <div class="container">
            <c:choose>
                <c:when test="${!empty businessProductsList}">
                    <div class="row">
                        <c:forEach items="${businessProductsList}" var="businessProduct">
                            <div class="business-product col-2" id="business-product"
                                 style="background-color: ${businessProduct.catalogVersion == "online" ? 'aliceblue' : 'antiquewhite'};">
                                <input class="checkbox" type="checkbox"
                                       value="${businessProduct.productId},${businessProduct.name},${businessProduct.catalogVersion}">
                                <div class="businessproduct-img">
                                    <img src="<c:url value='/images/${businessProduct.imageLink}'/>"/>
                                </div>
                                <p><tag:message code="businessdata.id"/> ${businessProduct.productId}</p>
                                <p><tag:message code="businessdata.name"/> ${businessProduct.name}</p>
                                <p><tag:message code="businessdata.price"/> ${businessProduct.price}$</p>
                                <p>
                                    <div class="description-scroll">
                                        <tag:message code="businessdata.description"/> ${businessProduct.description}
                                    </div>
                                </p>
                                <p><tag:message
                                        code="businessdata.catalogversion"/> ${businessProduct.catalogVersion}</p>
                                <form method="get" action="${pageContext.request.contextPath}/businessdata/update">
                                    <input type="hidden" name="productId" value="${businessProduct.productId}">
                                    <button type="submit" class="btn btn-outline-success">
                                        <tag:message code="businessdata.change"/>
                                    </button>
                                </form>
                            </div>
                        </c:forEach>
                    </div>
                    <c:if test="${!empty pagesList}">
                        <ul class="pagination">
                            <c:forEach items="${pagesList}" var="mpage">
                                <li class="page-item ${mpage.pageId == page ? 'active' : ''}">
                                    <a class="page-link"
                                       href="${pageContext.request.contextPath}/businessdata?search=${search}&page=${mpage.pageId}&version=${version}">
                                            ${mpage.pageId}
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <div class="no-items"><tag:message code="businessdata.nosuch"/></div>
                </c:otherwise>
            </c:choose>
        </div>

    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>