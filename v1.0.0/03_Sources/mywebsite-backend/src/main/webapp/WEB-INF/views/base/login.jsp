<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:importAttribute name="layoutScripts" ignore="false"/>
<tiles:importAttribute name="layoutStyles" ignore="false"/>
<c:set var ="root" scope="application" value="${not empty cdnUrl ? cdnUrl : pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
	<head>		
		<!-- Standard Meta -->
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">	
		
		<link rel="shortcut icon" href="${root}/images/favicon.ico" type="image/x-icon"/>
				
		<!-- Java Scripts -->
		<c:forEach var="attr" items="${layoutScripts}">
 			<script type="text/javascript" src="${root}<c:out value='${attr}' />"></script>
		</c:forEach>
		<script>
			function updateInputCheck(){		    
			    if ($("#chkRemember").hasClass("glyphicon-unchecked")) {
					$("#chkRemember").removeClass("glyphicon-unchecked");
					$("#chkRemember").addClass("glyphicon-check");
					$("#remember").prop("checked", true);
			    } else {
					$("#chkRemember").addClass("glyphicon-unchecked");
					$("#chkRemember").removeClass("glyphicon-check");
					$("#remember").prop("checked", false);			
			    }
			}
		</script>
			
		<!-- CSS -->
		<c:forEach var="attr" items="${layoutStyles}">
			<link href="${root}<c:out value='${attr}' />" rel="stylesheet">
		</c:forEach>
		<style type="text/css">
			@font-face {
			    font-family: t3k_app_normal;
			    src: url(./fonts/Roboto-Regular.ttf);
			}		
			body{
			   	background-color: #323942 !important;
			   	font-family : t3k_app_normal !important;
        	}
        	.vertical-center {
				min-height: 100%;  
				min-height: 100vh;				
				display: flex;
				align-items: center;
			}
			.panel-title{
				color: #26a69a !important;
				font-size: 19px !important;
				display: block;
				text-align: center;
			}
			.sign-in{
				font-size: 11px !important;
				padding-top: 5px;
				color: #6d6d6d !important;
				text-align: center;
			}
			.alert {
				padding: 5px 10px !important;
				margin-bottom: 15px !important;
				font-size: 14px !important;
			}
			.message{
				font-size: 11px !important;
			}	
			.close{
				font-size: 13px !important;
			}	
			.divCheck{
				text-align: left;
				margin-bottom: 5px;
				cursor: pointer;				
				-moz-user-select: none; /* Firefox */
				-ms-user-select: none; /* Internet Explorer */
				-khtml-user-select: none; /* KHTML browsers (e.g. Konqueror) */
				-webkit-user-select: none; /* Chrome, Safari, and Opera */
				-webkit-touch-callout: none;  /* Disable Android and iOS callouts*/
			}
			.input-check{
				color: #26A69A;
			}
			.remember{
				font-family: t3k_app_normal;
				font-size: 11px !important;
				color: #696969;
				margin-left: 2px;
				position: relative;
				bottom: 1px;
			}
			.btn{
				color: #fff;
				background-color: #26a69a !important;
				font-size: 14px !important;
			}
			.btn:hover,
			.btn:focus{
			    background-color: #fff !important;
			    color: #26a69a !important;
			    border-color: #26a69a;
			}
		</style>

		<title>Fixed Asset Management System</title>	
	</head> 
 	<body>	
		<div class="container">
		    <div class="row vertical-center text-center">
		    	<div class="col-xs-10 col-xs-offset-1 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4">
		    		<div class="panel panel-default">
		    		
					  	<div class="panel-heading">
					    	<div class="panel-title">Fixed Asset Management System</div>
					    	<div class="sign-in">Please log in to your account.</div>
					    </div>
					    
					  	<div class="panel-body">
					    	<form action="login" method="post">
					    	
					    		<c:if test="${not empty pageMessage}">
							    	<div class="alert ${messageStyle} fade in">
										<a class="close" data-dismiss="alert" aria-label="close">&times;</a>
										<div class="message">${pageMessage}</div>
									</div>
								</c:if>
								
								<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
					    		
					    		<div class="form-group">
						    		   <input id="loginId" class="form-control input-sm" placeholder="Login ID" name="loginId" type="text">		
					    		</div>

					    		<div class="form-group">
					    			<input id="password" class="form-control input-sm" placeholder="Password" name="password" type="password">
					    		</div>
					    		
				    		    <div class="divCheck" onClick="updateInputCheck()">
				    		    	<span id="chkRemember" class="glyphicon glyphicon-unchecked input-check"></span>
				    		    	<span class="remember">Remember Me</span>			    		    	
                                </div>
                                
		                        <input id="btnSubmit" class="btn btn-sm btn-block" type="submit" value="Log In"/>
		                        
		                        <input id="remember" type="checkbox" name="remember-me" style="display: none;"/>						    
						    </form>
					    </div>			    
					</div>
				</div>
			</div>
		</div>
	</body>
</html>