<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="../../style/style.css">
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
                                <c:forEach items="${productsList}" var="product" >
                                    <div class="product col-4" >
                                        <div class="product-img">
                                        <%--    <img src="<c:url value='../../images/${product.link}'/>"/>--%>
                                            <img src="../../images/${product.link}">
                                        </div>
                                        <div>
                                            <h2 class="product-name"><a href="/productdata/${product.product_id}"/>${product.name}</h2>
                                            <h4 class="product-price">${product.price}</h4>
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
                    <c:choose>
                        <c:when test="${search!=null}">
                            <c:if test="${!empty productsList}">
                                <c:forEach items="${pagesList}" var="mpage">
                                    <a href="/catalog?pageid=${mpage.page_id}&searchString=${search}">${mpage.page_id}</a>
                                </c:forEach>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${!empty productsList}">
                                <c:forEach items="${pagesList}" var="mpage">
                                    <a href="/catalog?pageid=${mpage.page_id}">${mpage.page_id}</a>
                                </c:forEach>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="sort col-2">
                   <!--
                   <form method="post" action="">
                        <h4><b>Параметры товара</b></h4>
                        <h5>Тип</h5>
                        <p><input type="checkbox" name="option1" value="a1" >Кисть<Br>
                            <input type="checkbox" name="option2" value="a2">Альбом<Br>
                            <input type="checkbox" name="option3" value="a3">Маркер<Br>
                            <input type="checkbox" name="option4" value="a4">Карандаш<Br>
                        </p>
                        <p><input type="submit" value="применить"></p>
                    </form>
                    -->
                    <c:choose>
                        <c:when test="${search==null}">
                            <form class="form-inline my-2 my-lg-0" method="get" >
                                  <%--action="/catalog/search">--%>
                                <input class="form-control mr-sm-2" type="search" name="searchString" aria-label="Search">
                                <button type="submit" class="btn btn-outline-success my-2 my-sm-0" >Search</button>
                            </form>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>
