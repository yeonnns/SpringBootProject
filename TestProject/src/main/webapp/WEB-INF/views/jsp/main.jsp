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
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Main</title>
        <!-- page css & js -->
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link rel="stylesheet" type="text/css" href="${cssPath}/styles.css"/>
        <link rel="stylesheet" type="text/css" href="${cssPath}/example.css"/>
        <script src="${jsPath}/main.js" ></script>
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" ></script>
    </head>
    <body class="sb-nav-fixed">
        <!-- header tiles -->
        <!-- navi tiles -->
        
        <div id="layoutSidenav">
            <div id="layoutSidenav_content">
             <main>
               <div class="container-fluid px-4">
                   <h3 class="mt-3">Dashboard</h3>
                   	<div class="row">
                <c:if test ="${not empty latestJoin}">
                    <c:forEach items="${latestJoin}" var="latest" varStatus="color">
                          <div class="col-xl-3 col-md-6">
                              <div class="card  color${color.count} text-white mb-4">
                                  <div class="card-body">${latest.depth3}</div>
                                  <div class="card-footer d-flex align-items-center justify-content-between">
                                      <a class="small text-white stretched-link" href="#">${latest.createDate}</a>
                                      <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                  </div>
                              </div>
                          </div>
                    </c:forEach>
                </c:if>
                	</div>
                        <div class="row">
                   <!--      
                        <button  style="width:100px; height:100px; background-color: pink"onclick="startBatch()">버튼</button>
                    -->      
                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-area me-1"></i>
                                        Total Day Sales  
                                    </div>
                                    <div class="card-body"><canvas id="myAreaChart" width="100%" height="40"></canvas></div>
                                </div>
                            </div>
                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-bar me-1"></i>
                                        Total Month Sales  
                                    </div>
                                    <div class="card-body"><canvas id="myBarChart" width="100%" height="40"></canvas></div>
                                </div>
                            </div>
                        </div>

                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                Data Ranking
                            </div>
                            <div class="card-body">
                            	<!-- 검색, orderBy, 개수별 조회 -->
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Position</th>
                                            <th>sellerShop</th>
                                            <th>channel</th>
                                            <th>date</th>
                                            <th>price</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                <c:if test ="${not empty sales}">
                                    <c:forEach items="${sales}" var="sales">
                                        <tr>
                                         	<td>${sales.depth3}</td>
                                         	<td>${sales.depth2}</td>
                                         	<td>${sales.sellershop}</td>
                                         	<td>${sales.channel}</td>
                                         	<td>${sales.createDate}</td>
                                         	<td>${sales.price}</td>
                                        </tr>
                                    </c:forEach>
                              </c:if>
                                    </tbody>
                                </table>
                            </div>
                        </div>
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
		<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" ></script>
        <script type="text/javascript" src="${assetPath}/chart-area-demo.js"></script>
        <script type="text/javascript" src="${assetPath}/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" ></script>
		<script type="text/javascript" src="${jsPath}/datatables/datatables-simple-demo.js"></script>
     
    </body>
</html>
