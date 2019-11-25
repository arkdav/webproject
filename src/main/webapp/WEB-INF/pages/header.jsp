<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand"><img src="<c:url value='/resources/images/logo.png' />" alt=""></a>
<%--        <a class="navbar-brand"><img src="${pageContext.request.contextPath}/images/logo.png" alt=""></a>--%>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item ">
                    <a class="nav-link" href="${pageContext.request.contextPath}/home"><tag:message code="header.home"/></a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="${pageContext.request.contextPath}/catalog"><tag:message code="header.catalog"/></a>
                </li>
                <sec:authorize access="hasRole('ROLE_CUSTOMER')">
                    <li class="nav-item ">
                        <a class="nav-link" href="${pageContext.request.contextPath}/basket"><tag:message code="header.basket"/></a>
                    </li>
                    <li class="nav-item ">
                        <a class="nav-link" href="${pageContext.request.contextPath}/userorders"><tag:message code="header.userorders"/> </a>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasAnyRole('ROLE_CUSTOMER', 'ROLE_BUSINESS_USER')">
                <li class="nav-item ">
                    <a class="nav-link" href="${pageContext.request.contextPath}/userdata"><tag:message code="header.userdata"/> </a>
                </li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li class="nav-item ">
                    <a class="nav-link" href="${pageContext.request.contextPath}/admindata"><tag:message code="header.admindata"/></a>
                </li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_BUSINESS_USER')">
                    <li class="nav-item ">
                        <a class="nav-link" href="${pageContext.request.contextPath}/businessdata"><tag:message
                                code="header.businessdata"/></a>
                    </li>
                </sec:authorize>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <tag:message code="header.languages"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="?lang=en"><tag:message code="header.languages.english"/></a>
                        <a class="dropdown-item" href="?lang=ru"><tag:message code="header.languages.russian"/> </a>
                    </div>
                </li>
            </ul>
            <sec:authorize access="isAnonymous()">
                <div class="my-lg-0">
                    <ul class="navbar-nav">
                        <li class="nav-item ">
                            <a class="nav-link" href="${pageContext.request.contextPath}/login"><tag:message code="header.login"/> </a>
                        </li>
                        <li>
                            <a class="nav-link" href="${pageContext.request.contextPath}/registration"><tag:message code="registration.create"/></a>
                        </li>
                    </ul>
                </div>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <div class="my-lg-0">
                    <ul class="navbar-nav">
                        <li class="nav-item ">
                            <form method="POST" action="${pageContext.request.contextPath}/logout" class="form-signin">
                                <div class="form-group">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    <button class="btn btn-lg btn-primary btn-block" type="submit"><tag:message code="userdata.logout"/></button>
                                </div>
                            </form>
                        </li>
                    </ul>
                </div>
            </sec:authorize>
        </div>
    </nav>
</header>

