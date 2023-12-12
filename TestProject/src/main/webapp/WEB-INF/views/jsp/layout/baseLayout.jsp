<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
</script>
<style>
</style>
</head>
<body>
<div>
   	<tiles:insertAttribute name="header" />
 	<tiles:insertAttribute name="left" />
	<tiles:insertAttribute name="loading" />
	<tiles:insertAttribute name="body" />
</div>
</body>
</html>