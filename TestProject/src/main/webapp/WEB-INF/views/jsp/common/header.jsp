<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="com.test.config.PrincipalDetails"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${cssPath}/css/base.css"/>
<!-- webSocket -->
<!-- sockJS -->
<script type="text/javascript" src="${jsPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${jsPath}/js/header.js"></script>
<script src="${jsPath}/js/socket/stomp.js"></script>
<script src="${jsPath}/js/socket/sockjs.min.js"></script>
<script>
var socket  = null;
var socketClose = false;

</script>
</head>
       <!-- header start-->
<body>      
       <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark msgAfter">
            <a class="navbar-brand ps-3" href="/">ProjectMain</a>
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
			<sec:authorize access="isAuthenticated()">
				<p class="ps-3 sb-nav-link-icon whiteCol mgb8">depth1 : <sec:authentication property="principal.depth1"/></p>
				<p class="ps-3 sb-nav-link-icon whiteCol mgb8">depth2 : <sec:authentication property="principal.depth2"/></p>
        		<p class="ps-3 sb-nav-link-icon whiteCol mgb8">depth3 : <sec:authentication property="principal.depth3"/></p>
			</sec:authorize>
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class=" nav-item dropdown newImage">
                    <a class="nav-link dropdown-toggle " id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                    
                    	<sec:authorize access="isAnonymous()">
	                        <li><a class="dropdown-item" href="/login">login</a></li>
                        	<li><hr class="dropdown-divider" /></li>
						</sec:authorize>
						<sec:authorize access="isAuthenticated()">
                        	<li><a class="dropdown-item" href="/logout">Logout</a></li>
                        	<li><a class="dropdown-item" href="/mailForm">MailForm</a></li>
                        	<li><a class="dropdown-item" href="/message/sendmsg">Message </a></li> 
                        	<!-- <span class="downred f10 nw"> new</span> -->
                        </sec:authorize>
                    </ul>
                </li>
            </ul>
        </nav>
       <!--  <div class="newMsg blinking">message 도착</div> -->
       <!-- header end-->
</body>
</html>