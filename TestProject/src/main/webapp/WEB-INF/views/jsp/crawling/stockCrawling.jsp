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
<html>
<head>
<meta charset="UTF-8">
<title>stockCrawling</title>
<!-- page css & js -->
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${cssPath}/styles.css"/>
<link rel="stylesheet" type="text/css" href="${cssPath}/example.css"/>
<link rel="stylesheet" type="text/css" href="${cssPath}/base.css"/>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" ></script>
<script type="text/javascript" src="${jsPath}/crawling.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" ></script>

<!-- DataTables -->
<link rel="stylesheet" type="text/css" href="${cssPath}/datatables/datatables.min.css"/>
<script type="text/javascript" src="${jsPath}/datatables/datatables.min.js"></script>

<!-- AirDatePicker -->
<link rel="stylesheet" type="text/css" href="${cssPath}/datepicker/datepicker.min.css"/>
<!-- Air datepicker css -->
<script type="text/javascript" src="${jsPath}/datepicker/datepicker.js"></script> 
<script type="text/javascript" src="${jsPath}/datepicker/datepicker.ko.js"></script> 

<style>
	#datatablesSimple_filter{
		position : relative;
		bottom : 35px;
	}
	.excel{
		position : relative;
		bottom : 35px;
	}
</style>
</head>
<script>
test();
</script>
<body class="sb-nav-fixed">
        <!-- header tiles -->
		<!-- navi tiles -->
		
        <div id="layoutSidenav">
            <div id="layoutSidenav_content">
             <main>
             	<div class="container-fluid px-4">
             <button class="mgt20 btn startC stockC" onclick="stockCrawling()">수동</button>
             <button class="mgt20 btn startC stockC test" onclick="test()">test</button>
                		 <div class="row">
                            <div class="col-xl-6">
                                <div class="card mb-4 ">
                                    <div class="card-header">
                                        <i class="fas fa-chart-area me-1"></i>
                                        Week High Stock  
                                    </div>
                                    <div class="card-body hi">
                                    	<canvas id="weekHigh" width="100%" height="40"></canvas>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header ">
                                        <i class="fas fa-chart-bar me-1"></i>
                                        Week Low Stock  
                                    </div>
                                    <div class="card-body low">
                                    	<canvas id="weekLow" width="100%" height="40"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>
                		<div class="card mb-4 mgt30">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                               stockReport
                            </div> 
                            <div class="card-body relative" >
                            	<div class=" w250 " style="margin : 0px">
									<div  class="table-range-left input-group ">
										<input readonly="readonly" type="text" id="datepicker" class="center f12 mgtp4" value="">
										<input  type="hidden" id="selectDate" value="${latestDate}">
										<label style="margin-bottom: 0px;" for="datepicker"><span class="input-group-addon"><i class="fa fa-calendar" ></i></span></label>
									</div>
								</div>
									
                            	<!-- stock data -->
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>종목명</th>
                                            <th>현재가</th>
                                            <th>전일비</th>
                                            <th>등락률</th>
                                            <th>액면가</th>
                                            <th>거래량</th>
                                            <th>고가</th>
                                            <th>저가</th>
                                            <th>시가총액</th>
                                            <th>ROE</th>
                                            <th>ROA</th>
                                            <th>등록일</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                             <c:if test ="${not empty stockList}">
                                    <c:forEach items="${stockList}" var="sList"> 
                                        <tr>
                                         	<td class="center">${sList.stockName}</td>
                                         	<td class="right"><fmt:formatNumber value="${sList.presentPrice}" pattern="#,###"/></td>
                                       	<c:if test = "${fn:contains(sList.fluctuationRate, '-')}">
											<td class="downred right">${sList.prevDayDiff}</td>
											<td class="downred right">${sList.fluctuationRate}</td>
										</c:if>
                                        	<c:if test = "${fn:contains(sList.fluctuationRate, '+')}">
											<td class="upblue right">${sList.prevDayDiff}</td>
											<td class="upblue right">${sList.fluctuationRate}</td>
										</c:if>
                                         	<td class="right"><fmt:formatNumber value="${sList.farValue}" pattern="#,###"/></td>
                                         	<td class="right"><fmt:formatNumber value="${sList.tradingValue}" pattern="#,###"/></td>
                                         	<td class="right"><fmt:formatNumber value="${sList.highPrice}" pattern="#,###"/></td>
                                         	<td class="right"><fmt:formatNumber value="${sList.lowPrice}" pattern="#,###"/></td>
                                         	<td class="right"><fmt:formatNumber value="${sList.marketCap}" pattern="#,###"/></td>
                                         	<td class="right">${sList.roe}</td>
                                         	<td class="right">${sList.roe}</td>
                                         	<td>${sList.createDate}</td>
                                        </tr>
                                
                                   </c:forEach>
                             </c:if> 
                                    </tbody>
                                </table>
                              	<div class=" w300 rfloat mg0 excel ">
									<div class="rfloat cgrey f8 "><br> 
		                                ※ ROE 자기자본수익률 (당기순이익 / 자기자본)<br>  
		                                ※ ROA 총자산수익률 (당기순이익 / 자기자본 + 부채)
		                            </div>
		                            <div>
										<button class="btn excelC " onclick="stockExcelDown()">excel Down</button>
		                                <button class="btn excelC " onclick="stockCsvlDown()">csv Down</button>
		                            </div>
								</div>
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