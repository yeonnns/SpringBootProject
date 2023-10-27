<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Register - SB Admin</title>
                <link rel="stylesheet" type="text/css" href="${cssPath}/styles.css"/>
        <link rel="stylesheet" type="text/css" href="${cssPath}/w3.css"/>
        <link rel="stylesheet" type="text/css" href="${cssPath}/example.css"/>
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"></script>
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" ></script>
    </head>
<script>

$(document).ready(function(){
	   // 로그인
	   $('#join').click(function(){
			$('#joinProc').submit();
	   });
	   
});
</script>
    <body class="loginbody">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-7">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Create Account</h3></div>
                                    <div class="card-body">
                                        <form id="joinProc" action="/joinProc" method="POST">
                                            <div class="row mb-3">
												<!--
                                                <div class="col-md-4">
                                                     <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputDepth1" type="text" placeholder="Enter your first name" />
                                                        <label for="inputFirstName">depth1</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class="form-floating">
                                                        <input class="form-control" id="inputDepth2" type="text" placeholder="Enter your last name" />
                                                        <label for="inputLastName">depth2</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class="form-floating">
                                                        <input class="form-control" id="inputDepth3" type="text" placeholder="Enter your last name" />
                                                        <label for="inputLastName">depth3</label>
                                                    </div>
                                                </div> -->
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control"  name="username"  id="username" type="text" placeholder="ID" />
                                                <label for="inputEmail">ID</label>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" name="password" id="password" type="password" placeholder="Create a password" />
                                                        <label for="inputPassword">Password</label>
                                                    </div>
                                                </div>
												<!--                                                 
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputPasswordConfirm" type="password" placeholder="Confirm password" />
                                                        <label for="inputPasswordConfirm">Confirm Password</label>
                                                    </div>
                                                </div>
                                                 -->
                                            </div>
                                            <div class="mt-4 mb-0">
                                                <div id="join" class="d-grid"><a class="btn loginbtn btn-block">Create Account</a></div>
                                            </div>
                                        </form>
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script type="text/javascript" src="${jsPath}/scripts.js"></script>
    </body>
</html>
