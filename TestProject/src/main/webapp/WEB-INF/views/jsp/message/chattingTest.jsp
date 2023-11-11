<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="com.test.config.PrincipalDetails"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<spring:eval expression="@environment.getProperty('img.path')" var="imgPath" />
<spring:eval expression="@environment.getProperty('js.path')" var="jsPath" />
<spring:eval expression="@environment.getProperty('css.path')" var="cssPath" />
<spring:eval expression="@environment.getProperty('asset.path')" var="assetPath" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${cssPath}/styles.css"/>
<link rel="stylesheet" type="text/css" href="${cssPath}/example.css"/>
<link rel="stylesheet" type="text/css" href="${cssPath}/base.css"/>
<link rel="stylesheet" type="text/css" href="${cssPath}/message/chattingTest.css"/>
<!-- DataTables -->
<link rel="stylesheet" type="text/css" href="${cssPath}/datatables/datatables.min.css"/>
<script type="text/javascript" src="${jsPath}/datatables/datatables.min.js"></script>

<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" ></script>
<script type="text/javascript" src="${jsPath}/message/chattingTest.js"></script>
<!-- autoSize -->
<script type="text/javascript" src="${jsPath}/autosize.min.js"></script>

<sec:authentication property="principal.username" var="username"/>
<script>
	var url = 'wss://${pageContext.request.scheme}:${pageContext.request.serverName}${pageContext.request.serverPort}${requestScope["javax.servlet.forward.request_uri"]}/websocket.do'
	var scheme = '${pageContext.request.scheme}';
	var serverName = '${pageContext.request.serverName}';
	var serverPort = '${pageContext.request.serverPort}';
	var contextPath = '${requestScope["javax.servlet.forward.request_uri"]}';
	var username = '${username}';

	var socket  = null;
	var socketClose = false;
</script>
<title>Chatting</title>
</head>
<body class="sb-nav-fixed">
	 <div id="layoutSidenav">
        <div id="layoutSidenav_content">
         <main>
           <div class="container-fluid px-4 chatOut">
           <!-- body start -->
         	<div class="card mb-4 mgt30">
	            <div class="card-header">
	                 <i class="fas fa-newspaper me-1"></i>
	                ChatList
	            </div>
                     <div id="container" >
                     	<aside>
							<header>
								<input type="text" placeholder="search">
							</header>
							<ul>
							<c:forEach items="${chatRoomList}" var="list">
								<li>
									<img class="proImg" src="/img/salt.png" alt="">
									<div>
										<h2>${list.title}</h2>
										<h3>
											( ${list.first}, ${list.second})
										</h3>
									</div>
								</li>
							</c:forEach>
							</ul>
						</aside>
						<c:if test="${not empty chatList}">
							<main>
								<input type="hidden" id="inRoomNo" name="inRoomNo" value="${room}">
								<input type="hidden" id="second" name="second" value="${chatRDetail.second}">
								<header>
									<img class="proImg" src="/img/salt.png" alt="">
									<div>
									<h2>with test1</h2>
										<h3>already 5 m</h3>
									</div>
								</header>
								<ul id="chat">
									<c:forEach items="${chatList}" var="list">
										<c:if test="${username eq list.sendId}">
											<li class="me">
												<div class="entete">
													<h3>${list.createDate}</h3>
													<h2>${list.sendId}</h2>
													<span class="status blue"></span>
												</div>
												<div class="triangle"></div>
												<div class="message">
													${list.contents}
												</div>
											</li>
										</c:if>
										<c:if test="${username ne list.sendId}">
											<li class="you">
												<div class="entete">
													<h2>${list.sendId}</h2>
													<h3>${list.createDate}</h3>
													<span class="status green"></span>
												</div>
												<div class="triangle"></div>
												<div class="message">
													${list.contents}
												</div>
											</li>
										</c:if>
									</c:forEach>
								</ul>
								<footer>
									<textarea placeholder="Type your message" id="sendContent" name="sendContent"></textarea>
									<img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/1940306/ico_picture.png" alt="">
									<img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/1940306/ico_file.png" alt="">
									<a id="chatSend" class="pointer">Send</a>
								</footer>
							</main>
						</c:if>		
						<c:if test="${ empty chatList}">
							<main></main>
						</c:if>				
					</div>
        	</div>
          <!-- body end -->
          </div>
            </main>
            <footer class="py-4 bg-light mt-auto">
                <div class="container-fluid px-4">
                    <div class="d-flex align-items-center justify-content-between small">
                        <div class="text-muted">Copyright &copy; Your Website 2023</div>
                        <div>
                            <a href="#">Privacy Policy</a>
                            &middot;
                            <a href="#">Terms &amp; Conditions</a>
                        </div>
                    </div>
                </div>
            </footer>
        </div>
    </div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" ></script>
    <script type="text/javascript" src="${jsPath}/scripts.js"></script>
</body>
</html>