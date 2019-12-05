<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>BusinessData</title>
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
                <c:if test="${!empty userList}">
                    <c:forEach items="${userList}" var="user">
                        <div class="business-product col-3">
                            <p><tag:message code="adminusers.login"/> ${user.login}</p>
                            <p><tag:message code="adminusers.name"/> ${user.name}</p>
                            <p><tag:message code="adminusers.surname"/> ${user.surname}</p>
                            <p><tag:message code="adminusers.phone"/> ${user.phone}</p>
                            <p><tag:message code="adminusers.email"/> ${user.email}</p>
                            <p><tag:message code="adminusers.status"/> ${user.status}</p>
                            <form class="form-inline my-2 my-lg-0" method="get"
                                  action="${pageContext.request.contextPath}/adminusers/update">
                                <input type="hidden" name="user" value="${user.login}">
                                <input type="hidden" name="u" value="${role}">
                                <button type="submit" class="btn btn-lg btn-primary btn-block">
                                    <tag:message code="adminusers.update"/>
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