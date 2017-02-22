<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:set var ="root" scope="application" value="${pageContext.request.contextPath}"/>

<tiles:importAttribute name="layoutScripts" ignore="false"/>
<tiles:importAttribute name="pageScripts" ignore="false"/>
<tiles:importAttribute name="pageStyle" ignore="false"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<meta content="IE=edge" http-equiv="X-UA-Compatible">
	<meta content="width=device-width, initial-scale=1" name="viewport">
	<meta content="" name="description">
	<meta content="" name="author">
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>	
	<link rel="shortcut icon" href="${root}/images/favicon.ico" type="image/x-icon"/>	
	<title>My Website</title>
	<link href="${root}<c:out value='${pageStyle}' />?v=${projectVersion}" rel="stylesheet/less">
	<script>
	  less = {
	    env: "development",
	    async: false,
	    fileAsync: false,
	    poll: 1000,
	    functions: {},
	    dumpLineNumbers: "comments",
	    relativeUrls: false,
	  };
	</script>	
	<script  type="text/javascript" src="${root}/js/common/less.min.js"></script>	
    <script>
        window.paceOptions = {
            ajax: {
                trackMethods: ['GET', 'POST']
            }
        };
    </script>	
</head>

<body>
	<header>
		<a class="scrollToTop"><i class="glyphicon glyphicon-chevron-up"></i></a>
		<div class="container">
			<tiles:insertAttribute name="menu" />
		</div>
		<c:if test="${not empty errorList}">
			<div id="errorList">
				<c:forEach items="${errorList}" var="item">
					<span class="validation-error" element-id="${item.key}" code="${item.value.errorCode}" params="${item.value.params}" style="display:none;"/>
				</c:forEach>		
			</div>
		</c:if>	
	</header>
	<!-- Preloader -->
	<div id="preloader">
	  <div id="status"><img src="${root}/images/gears.gif"></img></div>
	</div>	
	<section class="ui main container">
		<tiles:insertAttribute name="content" />
	</section>
	<footer class="container-fluid text-center">
	 	<p>Copyright © 2016 <a href="#">My Company Co., Ltd.</a> All rights reserved.</p> 
		<c:forEach var="attr" items="${layoutScripts}">
	 		<script type="text/javascript" src="${root}<c:out value='${attr}' />?v=${projectVersion}"></script>
		</c:forEach>
	 	<c:forEach var="attr" items="${pageScripts}">
	 		<script type="text/javascript" src="${root}<c:out value='${attr}' />?v=${projectVersion}"></script>
		</c:forEach>	
	</footer>
</body>