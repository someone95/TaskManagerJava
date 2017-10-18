<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tasks List</title>
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
			<a class="navbar-brand" href="getAllTasks">Task List</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="addTask">Add Task</a></li>
			<li class="active"><a href="logout">Log out</a></li>
		</ul>
	</div>
	</nav>
	<c:if test="${empty itemList}">
		No tasks
	</c:if>
	<c:if test="${not empty itemList}">
		<form action="searchTask">
			<div class="row">
				<div class="col-md-1">Search Task:</div>
				<input class="col-md-2" type="text" name="searchId">
				<div class="col-md-4">
					<input class="btn btn-success" type='submit' value='Search' />
				</div>
			</div>
		</form>
		<div class="row " id="table-header">
			<div class="col-md-1" id="table-header">Id</div>
			<div class="col-md-1" id="table-header">Title</div>
			<div class="col-md-1" id="table-header">Content</div>
			<div class="col-md-1" id="table-header">Mark</div>
			<div class="col-md-2" id="table-header">Date of creation</div>
			<div class="col-md-1" id="table-header">Date of last edit</div>
			<div class="col-md-2" id="table-header">Creator</div>
		</div>
		<c:forEach items="${itemList}" var="task">
			<div class="row " id="table-head">
				<div class="col-md-1" id="table-header">
					<c:out value="${task.id}" />
				</div>
				<div class="col-md-1" id="table-header">
					<c:out value="${task.title}" />
				</div>
				<div class="col-md-1" id="table-header">
					<c:out value="${task.content}" />
				</div>
				<div class="col-md-1" id="table-header">
					<c:out value="${task.mark}" />
				</div>
				<div class="col-md-2" id="table-header">
					<c:out value="${task.dateOfCreation}" />
				</div>
				<div class="col-md-1" id="table-header">
					<c:out value="${task.dateOfLastEdit}" />
				</div>
				<div class="col-md-1" id="table-header">
					<c:out value="${task.creator.username}" />
				</div>
				<div class="col-md-1" id="table-header">
					<a href="editTask?id=<c:out value='${task.id}'/>">Edit</a>
				</div>
				<div class="col-md-1" id="table-header">
					<a href="deleteTask?id=<c:out value='${task.id}'/>">Delete</a>
				</div>
				<div class="col-md-1" id="table-header">
					<a href="addComment?parentTaskId=<c:out value="${task.id}" />">Add comment</a>
				</div>
				<div class="col-md-1" id="table-header">
					<a href="addWorklog?parentTaskId=<c:out value="${task.id}" />">Add worklog</a>
				</div>
				<div class="col-md-1" id="table-header">
					<a href="getAllComments?parentTaskId=<c:out value="${task.id}" />">Show Comments</a>
				</div>
			</div>
		</c:forEach>
	</c:if>
</body>
</html>

