<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Comments List</title>
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
			<a class="navbar-brand" href="getAllComments">Comments List</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="logout">Log out</a></li>
		</ul>
	</div>
	</nav>
	<c:if test="${empty itemList}">
		No comments
	</c:if>
	<c:if test="${not empty itemList}">
		<form action="searchComment">
			<div class="row">
				<div class="col-md-1">Search Comment:</div>
				<input class="col-md-2" type="text" name="searchId">
				<div class="col-md-4">
					<input class="btn btn-success" type='submit' value='Search' />
				</div>
			</div>
		</form>
		<div class="row " id="table-header">
			<div class="col-md-1" id="table-header">Id</div>
			<div class="col-md-1" id="table-header">Comment</div>
			<div class="col-md-1" id="table-header">Task</div>
		</div>
		<c:forEach items="${itemList}" var="comment">
			<div class="row " id="table-head">
				<div class="col-md-1" id="table-header">
					<c:out value="${comment.id}" />
				</div>
				<div class="col-md-1" id="table-header">
					<c:out value="${comment.description}" />
				</div>
				<div class="col-md-1" id="table-header">
					<c:out value="${comment.task.title}" />
				</div>
				<div class="col-md-1" id="table-header">
					<a href="editComment?id=<c:out value='${comment.id}'/>">Edit</a>
				</div>
				<div class="col-md-1" id="table-header">
					<a href="deleteComment?id=<c:out value='${comment.id}'/>">Delete</a>
				</div>
			</div>
		</c:forEach>
	</c:if>
</body>
</html>

