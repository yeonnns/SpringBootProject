<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:eval expression="@environment.getProperty('css.path')" var="cssPath" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${cssPath}/common.css"/>
</head>
<body>
	<div class="wrap-loading-box display-none">
		<div class="custom-loader"></div>
		<div class="custom-loading">
			<span>s</span><span>o</span><span>y</span><span>e</span><span>o</span><span>n</span>
		</div>
	</div>
</body>
</html>