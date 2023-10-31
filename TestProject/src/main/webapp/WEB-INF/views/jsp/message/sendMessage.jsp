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
<link rel="stylesheet" type="text/css" href="${cssPath}/message/message.css"/>


<!-- DataTables -->
<link rel="stylesheet" type="text/css" href="${cssPath}/datatables/datatables.min.css"/>
<script type="text/javascript" src="${jsPath}/datatables/datatables.min.js"></script>
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" ></script>
<script type="text/javascript" src="${jsPath}/message/sendMessage.js"></script>
<!-- autoSize -->
<script type="text/javascript" src="${jsPath}/autosize.min.js"></script>
<script src="${jsPath}/socket/stomp.js"></script>
<script src="${jsPath}/socket/sockjs.min.js"></script>

<sec:authentication property="principal.depth3" var="depth3"/>
<sec:authentication property="principal.username" var="username"/>
<script>
	var url = 'wss://${pageContext.request.scheme}:${pageContext.request.serverName}${pageContext.request.serverPort}${requestScope["javax.servlet.forward.request_uri"]}/websocket.do'
	var scheme = '${pageContext.request.scheme}';
	var serverName = '${pageContext.request.serverName}';
	var serverPort = '${pageContext.request.serverPort}';
	var contextPath = '${requestScope["javax.servlet.forward.request_uri"]}';
	var userNmae = '${username}';

	var socket  = null;
	var socketClose = false;
</script>
<title>sendMessage</title>
</head>
<body class="sb-nav-fixed">
	 <div id="layoutSidenav">
        <div id="layoutSidenav_content">
         <main>
           <div class="container-fluid px-4">
           <!-- body start -->
         	<div class="card mb-4 mgt30">
	            <div class="card-header">
	                <i class="fas fa-bell me-1"></i>
	                sendMessage
	            </div>
                <div class="center mg20 h500 w85p">
                     	<div class="border third card-body inblock left h100p f10 overA" >
                    	<div class="mgb10 einsDiv">
                    		<i class="fas fa-plus einsck" onclick="depth1Ck()"></i>
					        <label class="bold" for="einsck">
					        	 TestOffice
					        </label>
					    </div>
					    <div class="depth1">
					    </div>
                   	</div>
                   	<div class="w11p inblock h100p">
                   		<div class="btn border f10 add" onclick="addButton()"> 추가 </div>
                   		<div class="btn border f10 del" onclick="delButton()"> 삭제 </div>
                     	</div>
                     	<div class="border card-body half inblock rfloat2 h100p" >
                   		<div class="setting-div">
						<p class="textarea-name">보내는사람</p>
							<div class="txt-title">
								<input id="sendId" class="w70p" name="sendeId" value="${username}"/>
							</div>
						</div>
                   		<div class="setting-div">
						<p class="textarea-name">받는사람</p>
							<div class="txt-title">
								<input id="recNameId" class="w70p" name="recNameId" readonly />
								<input type="hidden"id="receiveId"name="receiveId" />
								<input type="hidden"id="depth3"name="depth3" />
							</div>
						</div>
						<div class="setting-div">
							<p class="textarea-name">제목</p>
							<div class="txt-title">
								<input id="title" class="w70p" name="title" />
							</div>
						</div>
						<div class="setting-div">
							<p class="textarea-name">내용</p>
							<div class="txt-title">
								<textarea maxlength="500" id="content" name="content" maxlength="300" rows="10" wrap="hard" ></textarea>
							</div>
							<span id="text-cnt" class="rfloat3">(0/500)</span>
						</div>
						 <div class="inblock rfloat mgt10">
							<button class=" btn border f10 " id="msgSendBtn">보내기</button>
						</div>
                   	</div>
				</div>
        	</div>
          <!-- body end -->
          </div>
          <div class="container-fluid px-4">
         	<div class="card mb-4 mgt30">
	            <div class="card-header">
	                <i class="fas fa-table me-1"></i>
	                receiveMessage
	            </div>
                <div class="center mg20 mh300 bd relative w81p">
                	<div class="delBtn point z100" onclick="msgDelBtn()">삭제</div>
                	<div class="readBtn point z100" onclick="msgReadBtn()">읽음</div>
              		<table id="dataTableMessage" class="w100p pda10">
              			<thead>
              				<tr class="topTr">	
              					<td ><input id="totalInput" value="totalInput" type="checkbox" onclick="totalInput()"></td>
              					<td>부서</td>
              					<td>직책</td>
              					<td>보낸사람</td>
              					<td>제목</td>
              				 	<td>내용</td> 
              					<td>발신시간</td>
              				</tr>
              			</thead>
              			<tbody>
							<c:forEach var="list" items="${receiveList}">
							<tr id="${list.no}"class=" point
								<c:if test="${list.isRead eq 'N'}"> bold </c:if>
							 ${list.no}">
								<td><input type="checkbox" value="${list.no}"></td>
								<td>${list.depth2}</td>
								<td>${list.position}</td>
								<td>${list.sendId}</td>
								<td class="lfloat3">${list.title}</td>
								<td class="lfloat3">
									<div class="textbox">
										<textarea id="contentArea${list.no}" class="contentArea" readonly>${list.content}</textarea>
										<span id="openMessage${list.no}" onclick="openMessage('${list.no}')" class="openMessage">더보기</span>
									</div>
								</td>
								<td>${list.createDate}</td>
							</tr>
							</c:forEach>
              			</tbody>
              		</table>
                </div>
       		</div>
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