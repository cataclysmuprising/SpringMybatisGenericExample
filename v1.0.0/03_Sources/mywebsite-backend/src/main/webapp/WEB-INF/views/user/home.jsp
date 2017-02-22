<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<div class="row crumbContainer">
	<div class="crumb flat">
		<a href="${configHomeUrl}"><i class="glyphicon glyphicon-home"></i></a>
		<a href="${userHomeUrl}">User</a>
		<a>User - Main</a>
	</div>
</div>
<div class="box box-primary row">
	<div class="box-header with-border">
		<form class="form-horizontal control-block" role="form" method="post">
			<div class="col-sm-2">
				<input id="loginId" placeholder="Login ID" type="text" class="form-control input-sm">
			</div>
			<div class="col-sm-4 search-item">
				<input id="keyword" placeholder="Search..." type="text" class="form-control input-sm">
			</div>
			<div class="col-sm-2 search-item">
				<select class="form-control input-sm  selectpicker show-tick" title="Select..." id="roles">
				</select>
			</div>
			<div class="col-sm-4 search-item">
				<button type="button" class="btn btn-primary btn-sm" id="btnSearch">Search</button>
				<button type="button" class="btn btn-info btn-sm" id="btnReset">Reset</button>
				<spring:url value="/users/add" var="userAddUrl" />
				<sec:authorize access="hasAnyAuthority(${userAdd})">
					<a href="${userAddUrl}" class="btn btn-sm btn-primary addNewButton pull-right" role="button">Add New</a>
				</sec:authorize>
			</div>
		</form>	
	</div>
	<div class="box-body">
		<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th data-id="loginId">Login ID</th>
					<th data-id="name">Name</th>
					<th>Email</th>
					<th>NRC</th>
					<th data-id="loginDate">Last Login</th>
					<th>Role(s)</th>
					<sec:authorize access="hasAnyAuthority(${userDetail}${not empty userDetail ? ',':''}${userEdit}${not empty userEdit ? ',':''}${userRemove})">
						<th class="functionColumn">Function</th>
					</sec:authorize>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>	
	</div>
</div>	
<div id="deleteConfirmModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Confirmation</h4>
            </div>
            <div class="modal-body">
                <p class="text-center">Are you sure you want to remove this record ?</p>
                <p class="text-warning text-center">We have to make sure about this because this process can't retain the informations.</p>
            </div>
            <div class="modal-footer">
                <button type="button" id="confirmDelete" data-id="${selectedRole}" class="btn btn-sm btn-primary">Yes, I'm sure.</button>
                <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal" style="width: 50px;">No</button>
            </div>
        </div>
    </div>
</div>