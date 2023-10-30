<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
<title>TestOffice - 급여파일등록</title>
<link rel="stylesheet" type="text/css" href="${cssPath}/reportUpload.css"/>

<!-- yearpicker -->
<script type="text/javascript" src="${jsPath}/daterangepicker/moment.min.js"></script>
<script type="text/javascript" src="${jsPath}/jquery.ui.monthpicker.js"></script>
<link rel="stylesheet" href="${jsPath}/yearpicker/yearpicker.css">

<script>
$(document).ready(function() {
	//$('.wrap-loading-box').removeClass('display-none');

		/* monthpicker */
		//$("#totalDate").val(endDate.substring(0,7));
		$("#totalDate").monthpicker({
			startYear: 1995,
	        pattern: 'yyyy-mm',
	        dateFormat: 'yy-mm',
			monthNamesShort :  ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
		});
});

function salSubmit() {
	if (fileCheck() == false) {
		$("#excelFile").attr("type", "text");
		$("#excelFile").attr("type", "file");
		return false;
	}
	var form = $("#excelUpForm")[0];

	var data = new FormData(form);
	$.ajax({
		enctype : "multipart/form-data",
		method : "POST",
		url : './salaryFileUpload',
		processData : false,
		cache : false,
		contentType : false,
		data : data,
		success : function(result) {
			alert("파일업로드 완료");
			location.reload();
		}
		,beforeSend:function(){
			$('.wrap-loading-box').removeClass('display-none');
		}
		,complete:function(){
			$('.wrap-loading-box').addClass('display-none');
		}
		,error:function(e){
			alert("파일업로드 실패하였습니다.");
			$('.wrap-loading-box').addClass('display-none');
		}
	});
}
function fileCheck() {
	var filePath = document.getElementById('excelFile').value.split("\\");
	if(filePath.length == 0) {
		alert("파일을 선택해 주세요.");
		return false;
	}
	var thumbext = document.getElementById('excelFile').value; //파일을 추가한 input 박스의 값
	thumbext = thumbext.slice(-3, thumbext.length);
	if (thumbext != "csv") { 
		alert('csv파일만 등록가능합니다.');
		return false;
	}
}	
</script>
</head>
<body>
<!-- 내용 영역 start -->
<div class="pcoded-content">
	<div class="pcoded-inner-content">
		<!-- Main-body start -->
		<div class="main-body">
			<div class="page-wrapper">
				<div class="page-body">
					<!-- title start -->
					<div class="page-header-title">
						<h5 id="content_title" class="m-b-15">급여파일등록</h5>
					</div>
					<!-- title end -->
					<div class="row">
						<div class="col-lg-12">
							<div class="card card-margin">
								<div class="card-header card-header-title">
									<h5 class="title-font">급여리스트</h5>
								</div>
								<!-- content start -->
								<div class="row">
									<div class="col-xl-12">
										<div class="card-block">
											<div class="row">	
												<div class="col-lg-12 col-xl-7">
													<div  class="table-range-left input-group">
														<input readonly="readonly" type="text" class="form-control readonly " id="totalDate" value="${pagination.startDate}">
														<label for="totalDate"><span class="input-group-addon"><i class="fa fa-calendar" ></i></span></label>
													</div>
												</div>
												<div class="col-lg-12 col-xl-5">
													<form name="excelUpForm" id="excelUpForm" enctype="multipart/form-data" method="POST" accept-charset="UTF-8">
														<div class="input-group file-upload" style="margin-bottom: 0;">
															<input class="form-control file-upload" type="file" id="excelFile" name="excleFile" value="엑셀 업로드" />
															<span class="input-group-btn" style="margin-top:0px">
																<input class="btn btn-primary file-upload" type="button" value="완료" onclick="salSubmit()">
															</span>
														</div>
													</form>
												</div>
											</div>	
										
								<table id="table" class="table table-hover" style="table-layout: fixed;">
												<colgroup>
													<col class="col-border-right" width="3%" />
													<col class="col-border-right" width="7%" /> 
													<col class="col-border-right" width="7%" />
													<col class="col-border-right" width="6%" />
													<col class="col-border-right" width="4%" />
													<col class="col-border-right" width="6%" />
													<col class="col-border-right" width="6%" /> 
													<col class="col-border-right" width="6%" />
													<col class="col-border-right" width="6%" />
													<col class="col-border-right" width="6%" />
													<col class="col-border-right" width="6%" />
													<col class="col-border-right" width="6%" />
													<col class="col-border-right" width="6%" />
													<col class="col-border-right" width="6%" />
													<col class="col-border-right" width="7%" />
													<col class="col-border-right" width="6%" />
													<col class="col-border-right" width="6%" />
												</colgroup>
												<thead>
													<tr class="table-th total-summary">
														<td>No.</td>
														<td>ID</td>
														<td>대분류</td>
														<td>중분류</td>
														<td>소분류</td>
														<td>기본급</td>
														<td>grade2</td>
														<td>인센티브</td>
														<td>프로모션</td>
														<td>직책수당</td>
														<td>스마일캐시</td>
														<td>국민연금</td>
														<td>건강보험</td>
														<td>고용보험</td>
														<td>장기요양보험료</td>
														<td>소득세</td>
														<td>지방소득세</td>
													</tr>
												</thead>
												<tbody>
													<c:if test="${not empty salaryList}">
														<c:forEach items="${salaryList}" var="list" varStatus="status">
															<tr>
																<td>${status.count}</td>
																<td>${list.username}</td>
																<td>${list.depth1}</td>
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
															<td colspan="17" style="padding: 15px; text-align: center;">데이터가 없습니다.</td>
														</tr>
													</c:if>
												</tbody>
											</table>											
										</div>
									</div>
								</div>
								<!-- content end -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>