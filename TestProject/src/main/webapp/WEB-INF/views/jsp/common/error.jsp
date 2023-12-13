<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="com.test.config.PrincipalDetails"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<spring:eval expression="@environment.getProperty('css.path')" var="cssPath" />
<spring:eval expression="@environment.getProperty('img.path')" var="imgPath" />
<spring:eval expression="@environment.getProperty('js.path')" var="jsPath" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ERROR</title>
</head>
<script>
	$(document).ready(function(){
		var code = "${code }";
		var type = "${type }";
		
		if(code=="403" && type=="Forbidden"){
			alert("접근 권한이 없습니다.");
			$(location).attr('href', '/stockCrawl');
			//location.href="/";
		}
	});
</script>
<style>
	@font-face {
	    font-family: 'NEXON Lv2 Gothic';
	    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv2 Gothic.woff') format('woff');
	    font-weight: normal;
	    font-style: normal;
	}
	#errorWrap{
		padding-bottom: 50px;
		padding-top: 20px;
		border-radius: 10px;
		width: 100%;
		height: 100%;
		text-align: center;
		background-color: #fff;
		box-shadow: 0 2px 6px rgb(0 0 0 / 8%);
	}
	.error_contents{
		padding: 50px;
		padding-top: 0;
		margin: 0 auto;
		text-align: center;
	}
	
	.error_text{
		font-family: NEXON Lv2 Gothic;
		font-size: 1.2em;
		font-weight: bolder;
		display: inline-block;
		padding: 3px;
	}
	
	.error_title{
		font-size: 2em;
		font-weight: 800;
	}
	.font_red{
		color: red;
	}
	.font_violet{
		color: #3F0099;
	}
</style>
<body>
<!-- 내용 영역 start -->
<div class="pcoded-content">
	  <!-- 컨텐츠-header start -->
      <div class="pcoded-inner-content">
          <!-- Main-body start -->
          <div class="main-body">
              <div class="page-wrapper p-b-0">
                  <!-- Page-body start -->
                  <div class="page-body">
                      <div class="row">
                      <!-- 여기 개발 -->
                      		<div id="errorWrap">
								<div class="error_contents">
									<br>
										<span class="error_text">${code} ${type}</span>
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