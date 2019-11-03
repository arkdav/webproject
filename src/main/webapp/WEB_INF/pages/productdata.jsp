<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <h1>Product Details</h1>
        <br>
        <p>Name:  ${productinf.name}</p>
        <p>Type: ${productinf.type}</p>
        <p>Information: ${productinf.information}</p>
        <p>Price: ${productinf.price}</p>
        <br>
            <c:if test="${!empty images}">
                <c:forEach items="${images}" var="image" >
                    <p><img src="../../images/${image.link}"></p>
                </c:forEach>
            </c:if>
        <form class="form-inline my-2 my-lg-0" method="get" action="/basket" >
            <button type="submit" class="btn btn-outline-success my-2 my-sm-0" >Add to basket</button>
        </form>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>
