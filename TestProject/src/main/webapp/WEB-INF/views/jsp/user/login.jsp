<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<spring:eval expression="@environment.getProperty('img.path')" var="imgPath" />
<spring:eval expression="@environment.getProperty('js.path')" var="jsPath" />
<spring:eval expression="@environment.getProperty('css.path')" var="cssPath" />
<spring:eval expression="@environment.getProperty('asset.path')" var="assetPath" />
 <%
 response.setHeader("pragma","No-cache");
 response.setHeader("Cache-Control","no-cache");
 response.addHeader("Cache-Control","No-store");
 %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="${cssPath}/styles.css"/>
    <link rel="stylesheet" type="text/css" href="${cssPath}/w3.css"/>
    <link rel="stylesheet" type="text/css" href="${cssPath}/example.css"/>
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"></script>
    <script type="text/javascript" src="${jsPath}/member.js"></script>
</head>
<script>
$(document).ready(function(){

	var loginInfo = '<sec:authentication property="principal"/>';
	if(loginInfo.indexOf("PrincipalDetails") != -1){
		location.replace("/");
	}
	
	var rememberId = '${rememberId}';
	if(rememberId != ""){
		$('#username').val(rememberId);
		$('#remember-id').prop("checked", true);
	};
});
</script>
    
    <body class="loginbody">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Login</h3></div>
                                    <div class="card-body">
                                         <form class="md-float-material form-material" id="loginProc" action="/loginProc" method="POST">
                                         	<%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" name="username" id="username" type="text" placeholder="id" required=""">
                                                <label for="inputEmail">ID</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" name="password" id="password" type="password" placeholder="Password" required="">
                                                <label for="inputPassword">Password</label>
                                            </div>
                                       
                                            <div class="form-check mb-3">
                                                <input class="form-check-input" name="remember-id" id="remember-id" type="checkbox" ${checked}/>
                                                <label class="form-check-label" for="remember-id">아이디 저장</label>
											</div>                                              
                                      
                                             <div class="form-check mb-3">
                                                <input class="form-check-input" name="remember-me" id="remember-me" type="checkbox"  />
                                                <label class="form-check-label" for="remember-me">로그인 상태 유지</label>
                                            </div>
                                       
                                            <div  class="d-flex align-items-center justify-content-between mt-4 mb-0">
                                                <input type="submit" class="btn loginbtn" placeholder="로그인" >
                                            </div> 
                                        </form>
                                    </div>
                                    <div class="card-footer text-center py-3">
                                        <div class="small"><a href="/join">Need an account? Sign up!</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
            <div id="layoutAuthentication_footer">
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

    </body>
</html>
