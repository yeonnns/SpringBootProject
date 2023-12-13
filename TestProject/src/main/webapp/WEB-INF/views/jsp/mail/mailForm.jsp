<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="com.test.config.PrincipalDetails"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<spring:eval expression="@environment.getProperty('img.path')" var="imgPath" />
<spring:eval expression="@environment.getProperty('js.path')" var="jsPath" />
<spring:eval expression="@environment.getProperty('css.path')" var="cssPath" />
<spring:eval expression="@environment.getProperty('asset.path')" var="assetPath" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>MailSendForm</title>
    <!-- page css & js -->
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${cssPath}/styles.css"/>
    <link rel="stylesheet" type="text/css" href="${cssPath}/example.css"/>
    <link rel="stylesheet" type="text/css" href="${cssPath}/base.css"/>
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" ></script>
    <script type="text/javascript" src="${jsPath}/message/mailForm.js"></script>
    <!-- 써머노트 -->
	<script src="${jsPath}/summernote-lite.js"></script>
	<script src="${jsPath}/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="${cssPath}/summernote-lite.css">
</head>
<body class="sb-nav-fixed">
    <!-- header tiles -->
	<!-- navi tiles -->

    <div id="layoutSidenav">
        <div id="layoutSidenav_content">
         <main>
           <div class="container-fluid px-4">
           <!-- body start -->
           		  <div class="card mb-4 mgt30">
                        <div class="card-header">
                            <i class="fas fa-mail-forward me-1"></i>
                            MailForm
                        </div>
              <div class="card-body w70p">
                    <div class="setting-div">
						<button class="btn border f10" onclick="mailSend()">보내기</button>
						<input type="hidden" id="depth3" name="depth3" value="<sec:authentication property="principal.depth3"/>">
					</div>
					<!-- 받는 사람 -->
					<div class="setting-div">
						<p class="textarea-name">받는사람</p>
						<div class="txt-title">
							<input id="receiveId" name="receiveId" />
						</div>
					</div>
					<!-- 제목 -->
					<div class="setting-div">
						<p class="textarea-name">제목</p>
						<div class="txt-title">
							<input id="title" name="title" />
						</div>
					</div>
					<!-- 내용 -->
					<div class="setting-div">
						<textarea id="contents" name="contents"></textarea>
					</div>
				</div>
                     </div>
           <!-- body end -->
                </div>
            </main>
            <footer class="py-4 bg-light mt-auto">
                <div class="container-fluid px-4">
                    <div class="d-flex align-items-center justify-content-between small">
                        <div class="text-muted">Copyright &copy; Your Website 2022</div>
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
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" ></script>
    <script type="text/javascript" src="${jsPath}/datatables-simple-demo.js"></script>
</body>
</html>
