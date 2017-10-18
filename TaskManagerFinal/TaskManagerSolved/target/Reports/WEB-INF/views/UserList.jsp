<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users List</title>
<!-- Bootstrap CSS -->
<%-- <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"> --%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<style type="text/css">
.myrow-container {
	margin: 20px;
}

#table-head {
	background-color: #d3d3d3;
}

#table-header {
	background-color: #d3d3d3;
}

#addUser {
	text-decoration: none;
}
</style>
</head>
<body class=".container-fluid">
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="getAllUsers">User List</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="addUser">Add User</a></li>
			<li class="active"><a href="logout">Log out</a></li>
			<li class="active"><a href="getAllTasks">Task</a></li>
		</ul>
	</div>
	</nav>
	<c:if test="${empty itemList}">
		No users
	</c:if>
	<c:if test="${not empty itemList}">
		<form action="searchUser">
			<div class="row">
				<div class="col-md-1">Search Users:</div>
				<input class="col-md-2" type="text" name="searchId">
				<div class="col-md-4">
					<input class="btn btn-success" type='submit' value='Search' />
				</div>
			</div>
		</form>
		<div class="row " id="table-header">
			<div class="col-md-1" id="table-header">Id</div>
			<div class="col-md-2" id="table-header">Username</div>
			<div class="col-md-2" id="table-header">Password</div>
			<div class="col-md-2" id="table-header">First Name</div>
			<div class="col-md-2" id="table-header">Last Name</div>
			<div class="col-md-1" id="table-header">Is admin</div>
		</div>
		<c:forEach items="${itemList}" var="user">
			<div class="row " id="table-head">
				<div class="col-md-1" id="table-header">
					<c:out value="${user.id}" />
				</div>
				<div class="col-md-2" id="table-header">
					<c:out value="${user.username}" />
				</div>
				<div class="col-md-2" id="table-header">
					<c:out value="${user.password}" />
				</div>
				<div class="col-md-2" id="table-header">
					<c:out value="${user.firstName}" />
				</div>
				<div class="col-md-2" id="table-header">
					<c:out value="${user.lastName}" />
				</div>
				<div class="col-md-1" id="table-header">
					<c:out value="${user.admin}" />
				</div>
				<div class="col-md-1" id="table-header">
					<a href="editUser?id=<c:out value='${user.id}'/>">Edit</a>
				</div>
				<div class="col-md-1" id="table-header">
					<a href="deleteUser?id=<c:out value='${user.id}'/>">Delete</a>
				</div>
			</div>
		</c:forEach>
	</c:if>
</body>
</html>

