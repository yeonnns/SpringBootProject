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
<meta charset="UTF-8">
</head>
	<nav id="layoutSidenav"> 
          <div id="layoutSidenav_nav">
                <div class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Core</div>
                            <a class="nav-link" href="/">
                                <div class="sb-nav-link-icon">
	                                <i class="fas fa-tachometer-alt">
	                                </i>
                                </div>
                                Dashboard
                            </a>
                            <div class="sb-sidenav-menu-heading">Function</div>
                           
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#messenger" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon">
	                                <i class="fas fa-columns">
	                                </i>
                                </div>
                                	Messenger
                                <div class="sb-sidenav-collapse-arrow">
	                                <i class="fas fa-angle-down">
	                                </i>
                                </div>
                            </a>
                            <div class="collapse" id="messenger" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="/mailForm">mailForm</a>
                                </nav>
                            </div>
                            <div class="collapse" id="messenger" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="/message/sendmsg">message</a>
                                </nav>
                            </div>
                            <div class="collapse" id="messenger" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="/message/chattingTest">chatting</a>
                                </nav>
                            </div>
                            
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#crawling" aria-expanded="false" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon">
	                                <i class="fas fa-book-open">
	                                </i>
                                </div>
                                	 Crawling
                                <div class="sb-sidenav-collapse-arrow">
                                	<i class="fas fa-angle-down">
                                	</i>
                                </div>
                            </a>
                            <div class="collapse" id="crawling" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link " href="/crawling/stockCrawl" >
                                    	stockCrawling
                                    </a>
                                </nav>
                            </div>
                            
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#weather" aria-expanded="false" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon">
	                                <i class="fas fa-book-open">
	                                </i>
                                </div>
                                	 Weather
                                <div class="sb-sidenav-collapse-arrow">
                                	<i class="fas fa-angle-down">
                                	</i>
                                </div>
                            </a>
                            <div class="collapse" id="weather" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link " href="/weather/fineDust" >
                                    	FineDust
                                    </a>
                                </nav>
                            </div>
                            
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#hire" aria-expanded="false" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon">
	                                <i class="fas fa-table">
	                                </i>
                                </div>
                                	 Hire
                                <div class="sb-sidenav-collapse-arrow">
                                	<i class="fas fa-angle-down">
                                	</i>
                                </div>
                            </a>
                            <div class="collapse" id="hire" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link " href="/user/salaryList" >
                                    	salary
                                    </a>
                                </nav>
                            </div>
                        </div>
                    </div>
 				</div>
            </div>
 	</nav>
</body>
</html>