<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval expression="@environment.getProperty('img.path')" var="imgPath" />
<spring:eval expression="@environment.getProperty('js.path')" var="jsPath" />
<spring:eval expression="@environment.getProperty('css.path')" var="cssPath" />
<spring:eval expression="@environment.getProperty('year')" var="year" />
<spring:eval expression="@environment.getProperty('page.path')" var="pagePath" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 급여파일등록</title>
<link rel="stylesheet" type="text/css" href="${cssPath}/reportUpload.css"/>

<!-- page css & js -->
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${cssPath}/styles.css"/>
<link rel="stylesheet" type="text/css" href="${cssPath}/example.css"/>
<link rel="stylesheet" type="text/css" href="${cssPath}/base.css"/>
<link rel="stylesheet" type="text/css" href="${cssPath}/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${cssPath}/calendar.css"/>
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" ></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" ></script>
<script type="text/javascript" src="${jsPath}/scripts.js"></script>
<script type="text/javascript" src="${jsPath}/hire/salaryList.js"></script>

<!-- DataTables -->
<link rel="stylesheet" type="text/css" href="${cssPath}/datatables/datatables.min.css"/>
<script type="text/javascript" src="${jsPath}/datatables/datatables.min.js"></script>

<!-- DateRangePicker -->
<script type="text/javascript" src="${jsPath}/daterangepicker/moment.min.js"></script>
<script type="text/javascript" src="${jsPath}/jquery.ui.monthpicker.js"></script>


</head>
<body class="sb-nav-fixed">
    <!-- header tiles -->
	<!-- navi tiles -->
	<!-- 내용 영역 start -->
	<div id="layoutSidenav">
		<div id="layoutSidenav_content">
			<main>
			<div class="container-fluid px-4">
				<div class="card mb-4 mgt30">
				<div class="card-header">
                	<i class="fas fa-table me-1"></i>
                	${fn:substring(nowMonth, 0, 4)}년 ${fn:substring(nowMonth, 5, 7)}월 급여리스트
               	</div>
				<div class="card-body">
				
				<div class="row">	
					<div class="w250">
						<div  class="table-range-left input-group">
 								<input readonly="readonly" type="month" class="form-control ft8" id="totalDate" value="${nowMonth}">
							<label style="margin-bottom: 0px;" for="totalDate"><span class="input-group-addon"><i class="fa fa-calendar" ></i></span></label>
						</div>
					</div>
					<div class=" right w350">
						<form name="excelUpForm" id="excelUpForm" enctype="multipart/form-data" method="POST" accept-charset="UTF-8">
							<div class="input-group file-upload " style="margin-bottom: 0;">
								<input class="excelUp" type="file" id="excelFile" name="excleFile" value="엑셀 업로드" />
								<span class="input-group-btn" style="margin-top:0px">
									<input class="btn bd file-upload" type="button" value="완료" onclick="salSubmit()">
								</span>
							</div>
						</form>
					</div>
				</div>	
				<table id="datatablesSimple" >
								<thead>
									<tr >
										<th>No.</th>
										<th>ID</th>
										<th>중분류</th>
										<th>소분류</th>
										<th>기본급</th>
										<th>grade</th>
										<th>인센티브</th>
										<th>프로모션</th>
										<th>직책수당</th>
										<th>스마일캐시</th>
										<th>국민연금</th>
										<th>건강보험</th>
										<th>고용보험</th>
										<th>장기요양보험료</th>
										<th>소득세</th>
										<th>지방소득세</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${not empty salaryList}">
										<c:forEach items="${salaryList}" var="list" varStatus="status">
											<tr class="salaTr">
												<td>${status.count}</td>
												<td>${list.username}</td>
												<td>${list.depth2}</td>
												<td>${list.depth3}</td>
												<td>${list.salary}</td>
												<td>${list.grade}</td>
												<td>${list.incentive}</td>
												<td>${list.promotion}</td>
												<td>${list.posPlus}</td>
												<td>${list.smileCash}</td>
												<td>${list.ension}</td>
												<td>${list.healthIns}</td>
												<td>${list.employIns}</td>
												<td>${list.longTermIns}</td>
												<td>${list.incomeTax}</td>
												<td>${list.loincomeTax}</td>
											</tr>
										</c:forEach>
									</c:if>
									<c:if test="${empty salaryList}">
										<tr>
											<td colspan="16" style="padding: 15px; text-align: center;">데이터가 없습니다.</td>
										</tr>
									</c:if>
								</tbody>
							</table>											
						</div>
					</div>
				</div>
			<!-- content end -->
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
</body>
</html>