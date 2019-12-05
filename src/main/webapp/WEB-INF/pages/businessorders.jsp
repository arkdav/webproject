<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>BusinessOrders</title>
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
        <div class="container">
            <div class="row">
                <c:if test="${!empty businessOrdersList}">
                    <c:forEach items="${businessOrdersList}" var="orderEntry">
                        <div>
                            <p>Название товара: ${orderEntry.productDto.name}</p>
                            <p>Запись к заказу: ${orderEntry.orderNote}</p>
                            <p>Дата заказа: ${orderEntry.date}</p>
                            <p>Количество: ${orderEntry.amount}</p>
                            <p>Стоимость: ${orderEntry.price}</p>
                            <p>Логин заказчика: ${orderEntry.userDto.login}</p>
                            <p>Email заказчика: ${orderEntry.userDto.email}</p>
                            <p>Телефон заказчика: ${orderEntry.userDto.phone}</p>
                            <form class="form-inline my-2 my-lg-0" method="get"
                                  action="${pageContext.request.contextPath}/businessordersupdate?entryId=${orderEntry.orderEntryId}">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button type="submit" class="btn btn-lg btn-primary btn-block">
                                    поменять
                                </button>
                            </form>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>