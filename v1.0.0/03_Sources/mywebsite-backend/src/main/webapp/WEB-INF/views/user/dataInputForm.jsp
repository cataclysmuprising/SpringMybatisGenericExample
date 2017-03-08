<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<div class="row crumbContainer">
	<c:if test="${pageMode eq 'EDIT'}">
		<c:set var="submitUrl" value="/config/users/${user.id}/edit" />
		<c:if test="${user.id eq loginUserId}">
			<c:set var="submitUrl" value="/config/users/profile" />
		</c:if>
	</c:if>
	<spring:url value="${submitUrl}" var="userSubmitUrl" />
	<div class="crumb flat">
		<a href="${configHomeUrl}"><i class="glyphicon glyphicon-home"></i></a>
		<a href="${userHomeUrl}">User</a>
		<c:choose>
			<c:when test="${user.id eq loginUserId}">
				<a>Profile</a>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${pageMode eq 'CREATE' }">
						<a>User - Add</a>
					</c:when>
					<c:otherwise>
						<a>User - Edit</a>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<div class="alert alert-success alert-dismissable" id="pageInfo" style="display:none;">
	<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	<div class="messageDetail"></div>
</div>
<form:form modelAttribute="user" id="userForm" action="${userSubmitUrl}" method="post" class="form-horizontal data-setup" commandName="user">
	<div class="box box-primary row smallPanel">
		<div class="box-header with-border">
			<div class="image-editor">
				<input type="file" class="cropit-image-input">
				<div class="cropit-preview"></div>
				<div class="image-size-label">Resize image</div>
				<input type="range" class="cropit-image-zoom-input">
				<button class="rotate-ccw">Rotate counterclockwise</button>
				<button class="rotate-cw">Rotate clockwise</button>

				<span class="btn btn-primary export" type="button">Export</span>
			</div>
			<c:if test="${pageMode eq 'EDIT'}">
				<div class="row center-text" style="padding: 5px;">
				<sec:authorize access="hasAnyAuthority(${changedPassword})">
					<span class="btn btn-primary btn-sm " data-toggle="modal" data-target="#changePasswordModal" data-id="${user.id}" id="changePassword">Change Password</span>
				</sec:authorize>
				<sec:authorize access="hasAnyAuthority(${resetPassword})">
					<span class="btn btn-primary btn-sm add-sec-button" data-toggle="modal" data-target="#resetPasswordModal" data-id="${user.id}" id="resetPassword">Reset Password</span>
				</sec:authorize>										
				</div>	
			</c:if>			
		</div>	
		<div class="box-body">
			<div class="form-group">
				<label for="loginId" class="control-label col-sm-3 required">Login ID :</label>
				<div class="col-sm-4 control-block">
					<form:input id="loginId" type="text" path="loginId" class="${pageMode eq 'EDIT' ? 'disabled':''} form-control text-primary  input-sm"/>
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="control-label col-sm-3 required">Name :</label>
				<div class="col-sm-4 control-block">
					<form:input path="name" id="name" placeholder="Full Name" class="form-control input-sm"/>
				</div>
			</div>				
			<div class="form-group">
				<label for="email" class="control-label col-sm-3 required">Email :</label>
				<div class="col-sm-4 control-block">
					<form:input id="email" type="email" path="email"  placeholder="abc@gmail.com" class=" form-control text-primary input-sm"/>
				</div>
			</div>
			<c:if test="${pageMode eq 'CREATE'}">
				<div class="form-group">
					<label for="password" class="control-label col-sm-3 required">Password :</label>
					<div class="col-sm-4 control-block">
						<form:password path="password" id="password" placeholder="Enter Your Password" class="form-control input-sm"/>
					</div>
				</div>
				<div class="form-group">
					<label for="confirmPassword" class="control-label col-sm-3 required">Password Confirm :</label>
					<div class="col-sm-4 control-block">
						<form:password path="confirmPassword" id="confirmPassword" placeholder="Write again your password" class="form-control input-sm"/>
					</div>
				</div>					
				<div class="form-group">
					<label for="role" class="control-label col-sm-3 required">Role :</label>
					<div class="col-sm-4 control-block">
						<input type="hidden" id="selectedRoles" value="${user.roleIds}"> 
						<form:select multiple="multiple" title="Select..." path="roleIds" id="roles" class="form-control input-sm selectpicker show-tick" data-live-search="true" data-size="5" data-selected-text-format="count > 3">
						</form:select>
					</div>
				</div>				
			</c:if>
			<div class="form-group">
				<label for="nrc" class="control-label col-sm-3 required">NRC :</label>
				<div class="col-sm-4 control-block">
					<form:input path="nrc" id="nrc" class="form-control input-sm"/>
				</div>
			</div>
			<div class="form-group">
				<label for="phone" class="control-label col-sm-3 required">Phone :</label>
				<div class="col-sm-4 control-block">
					<form:input path="phone" class="form-control input-sm"/>
				</div>	
			</div>
			<div class="form-group">
				<label for="age" class="control-label col-sm-3 required">Age :</label>
				<div class="col-sm-2 control-block">
					<form:input path="age" id="age" placeholder="age" class="form-control input-sm"/>
				</div>
			</div>			
			<div class="form-group">
				<label for="dob" class="control-label col-sm-3">DOB :</label>
				<div class="col-sm-3 control-block">
					<div id="dobInputGroup" class="input-group date">
						<form:input id="dob" path="dobAsString" class="form-control input-sm"  placeholder="Date Of Birth" type="text" />
						<span class="input-group-addon">
                        	<span class="glyphicon glyphicon-calendar"></span>
                    	</span>  
					</div>	
				</div>					
			</div>
			<div class="form-group">
				<label for="address" class="control-label col-sm-3">Address :</label>
				<div class="col-sm-8">
					<form:textarea id="address" path="address" rows="2" class="form-control input-sm col-sm-8"/>
				</div>
			</div>
			<div class="form-group">
				<label for="gender" class="control-label col-sm-3">Gender :</label>
				<div class="col-sm-6">
					<div class="radio-inline">
						<form:radiobutton id="male" value="MALE" path="gender" /><label for="male">Male</label>
					</div>
					<div class="radio-inline">
						<form:radiobutton id="female" value="FEMALE" path="gender" /><label for="female">Female</label>
					</div>
				
				</div>
			</div>		
		</div>	
		<div class="box-footer text-center">
			<button class="btn btn-info btn-sm" type="button" id="btnReset" style="width: 70px;">Reset</button>
			<button class="btn btn-primary btn-sm" type="button" id="btnSave" style="width: 70px;">Save</button>
			<button class="btn btn-sm btn-danger add-sec-button" type="button" id="btnCancel" style="width: 70px;">Cancel</button>		
		</div>
	</div>
</form:form>
<div class="modal fade" id="changePasswordModal" role="dialog">
	<div class="modal-dialog">
	    <div class="modal-content">
	        <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	            <h4 class="modal-title">Change Password</h4>
	        </div>
	        <div class="modal-body">
	        	<form class="form-horizontal" id="change_PasswordForm" role="form">
	        		<div class="form-group">
	        			<label class="control-label col-sm-4 required">Old Password : </label>
	        			<div class="col-sm-8">
	        				<input type="password" class="form-control input-sm" type="text" id="change_oldPassword" placeholder="Type your old password" />
	        			</div>
	        		</div>
	        		<div class="form-group">
	        			<label class="control-label col-sm-4 required">New Password : </label>
	        			<div class="col-sm-8">
	        				<input type="password" class="form-control input-sm col-sm-8" type="text" id="change_newPassword" placeholder="Define for new password" />
	        			</div>	        			
	        		</div>
	        		<div class="form-group">
	        			<label class="control-label col-sm-4 required">Confirm Password : </label>
	        			<div class="col-sm-8">
	        				<input type="password" class="form-control input-sm col-sm-8" type="text" id="change_confirmPassword" placeholder="Write your new password again" />
	        			</div>	        			
	        		</div>	
	        	</form>
	        </div>
	        <div class="modal-footer center-text">
	            <button type="button" id="confirmChangePassword" data-id="${user.loginId}" class="btn btn-primary btn-sm" style="width: 70px;">Save</button>
	            <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal" style="width: 70px;">Cancel</button>
	        </div>
	    </div>
	</div> 
 </div>
<div class="modal fade" id="resetPasswordModal" role="dialog">
	<div class="modal-dialog">
	    <div class="modal-content">
	        <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	            <h4 class="modal-title">Reset Password</h4>
	        </div>
	        <div class="modal-body">
	        	<form class="form-horizontal" id="reset_PasswordForm" role="form">
	        		<div class="form-group">
	        			<label class="control-label col-sm-4 required">New Password : </label>
	        			<div class="col-sm-8">
	        				<input type="password" class="form-control input-sm col-sm-8" type="text" id="reset_newPassword" placeholder="Define for new password" />
	        			</div>	        			
	        		</div>
	        		<div class="form-group">
	        			<label class="control-label col-sm-4 required">Confirm Password : </label>
	        			<div class="col-sm-8">
	        				<input type="password" class="form-control input-sm col-sm-8" type="text" id="reset_confirmPassword" placeholder="Write your new password again" />
	        			</div>	        			
	        		</div>	
	        	</form>
	        </div>
	        <div class="modal-footer center-text">
	            <button type="button" id="confirmResetPassword" data-id="${user.loginId}" class="btn btn-primary btn-sm" style="width: 70px;">Save</button>
	            <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal" style="width: 70px;">Cancel</button>
	        </div>
	    </div>
 </div>