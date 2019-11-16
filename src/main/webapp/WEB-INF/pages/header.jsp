<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags"%>

<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand"><img src="../../images/logo.png" alt=""></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item ">
                    <a class="nav-link" href="/home"><tag:message code="header.home"/></a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="/catalog"><tag:message code="header.catalog"/></a>
                </li>
                <sec:authorize access="hasRole('ROLE_USER')">
                    <li class="nav-item ">
                        <a class="nav-link" href="/basket"><tag:message code="header.basket"/></a>
                    </li>
                    <li class="nav-item ">
                        <a class="nav-link" href="/userdata"><tag:message code="header.userdata"/> </a>
                    </li>
                    <li class="nav-item ">
                        <a class="nav-link" href="/userorders"><tag:message code="header.userorders"/> </a>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li class="nav-item ">
                    <a class="nav-link" href="/admindata"><tag:message code="header.admindata"/></a>
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
            <ul class="navbar-nav">
                <li class="nav-item ">
                    <div class="my-lg-0">
                        <a class="nav-link" href="/login"><tag:message code="header.login"/> </a>
                        <a class="nav-link" href="/registration"><tag:message code="registration.create"/></a>
                    </div>
                </li>
            </ul>
            </sec:authorize>
        </div>
    </nav>
</header>

