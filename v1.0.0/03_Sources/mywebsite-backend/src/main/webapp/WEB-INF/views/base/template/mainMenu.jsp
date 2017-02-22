<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<c:forEach var="access" items="${accessments}">
	<input type="hidden" id="${access.key}" value="${access.value}">
</c:forEach>
<nav role="navigation" class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button data-target="#bs-example-navbar-collapse-1" data-toggle="collapse" class="navbar-toggle" type="button">
				<span class="sr-only">Toggle navigation</span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<spring:url value="/users" var="homeUrl" scope="application" />
			<a href="${homeUrl}" class="navbar-brand">
				My Website
			</a>
		</div>
		<div id="bs-example-navbar-collapse-1" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<sec:authorize access="hasAnyAuthority(${userList})">
					<li class="${page eq 'user' ? 'active' : '' }">
						<spring:url value="/users" var="userHomeUrl" scope="application" /> 
						<a href="${userHomeUrl}">User</a>
					</li>				
				</sec:authorize>
				<sec:authorize access="hasAnyAuthority(${roleList})">
					<li class="${page eq 'role' ? 'active' : '' }">
						<spring:url value="/roles" var="roleHomeUrl" scope="application" /> 
						<a href="${roleHomeUrl}">Role</a>
					</li>
				</sec:authorize>
				<sec:authorize access="hasAnyAuthority(${settingList})">
					<li class="${page eq 'setting' ? 'active' : ''}">
						<spring:url value="/settings" var="settingHomeUrl" scope="application" /> 
						<a href="${settingHomeUrl}">Setting</a>
					</li>
				</sec:authorize>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="pull-left">
		     		<c:choose>
  				 		<c:when test="${empty loginUserId}">
       						<img class="img-circle"  src="${root}/images/avatar/guest.jpg">
   						</c:when>    
   						<c:otherwise>
     						<img class="img-responsive img-circle profile-image" src="${root}/files/${contentId}">
   						</c:otherwise>
					</c:choose>
			   	</li>
				<li class="dropdown pull-left">
					<a data-toggle="dropdown" class="dropdown-toggle"> 
						<c:out value="${loginUserName}" default="Guest" /> 
						<b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<sec:authorize access="hasAnyAuthority(${userProfile})">
							<li>
								<spring:url value="/users/profile" var="profileUrl" scope="application" />
								<a href="${profileUrl}" class="item">
									Profile
								</a>					
							</li>
						</sec:authorize>					
						<li class="divider"></li>					
						<li>
							<c:if test="${not empty loginUserId}">
								<input id="loginUserId" type="hidden" value="${loginUserId}" />
							</c:if> 
							<spring:url value="${not empty loginUserName ? '/logout': '/login'}" var="authenticationUrl" /> 
							<a href="${authenticationUrl}">
								${not empty loginUserName ? 'Logout': 'Login'}
							</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>