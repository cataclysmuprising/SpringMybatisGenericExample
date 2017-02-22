<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<c:set var ="root" scope="application" value="${not empty cdnUrl ? cdnUrl : pageContext.request.contextPath}"/>
<tiles:importAttribute name="layoutScripts" ignore="false"/>
<tiles:importAttribute name="layoutStyles" ignore="false"/>

<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- Standard Meta -->
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
		<meta name="_csrf" content="${_csrf.token}"/>
		<meta name="_csrf_header" content="${_csrf.headerName}"/>
		<title>Error !</title>
		<c:forEach var="attr" items="${layoutStyles}">
			<link href="${root}<c:out value='${attr}' />" rel="stylesheet">
		</c:forEach>
	</head>
	<body>
		<tiles:insertAttribute name="content" />
	</body>
	<footer>
	<!-- For Development -->
<%-- 		<jsp:useBean id="now" class="java.util.Date" />
	 	<c:forEach var="attr" items="${layoutScripts}">
	 		<script type="text/javascript" src="${root}<c:out value='${attr}' />?${now.time}"></script>
		</c:forEach> --%>
	 	<c:forEach var="attr" items="${layoutScripts}">
	 		<script type="text/javascript" src="${root}<c:out value='${attr}' />?v=${projectVersion}"></script>
		</c:forEach>			
	</footer>
</html>