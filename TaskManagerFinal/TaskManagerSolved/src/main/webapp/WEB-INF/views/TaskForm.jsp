<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Ranga Reddy">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add a task</title>
<!-- Bootstrap CSS -->
<%-- <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"> --%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<style type="text/css">
</style>
</head>
<body class="container">
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="logout">Log out</a>
		</div>
		<ul class="nav navbar-nav">
		</ul>
	</div>
	</nav>
	<h2>Add a task</h2>
	<form:form method="post" action="saveTask" modelAttribute="item">
		<form:hidden path="id" value="${item.id}" />
		<div class="form-group">
			<label id="title" name="title">Title:</label> <input type="text"
				class="form-control" id="title" name="title" value="${item.title}"
				placeholder="Enter a title">
		</div>
		<div class="form-group">
			<label id="content" name="content">Content:</label> <input
				type="text" class="form-control" id="content" name="content"
				value="${item.content}" placeholder="Enter content">
		</div>
		<div class="form-group">
			<label id="mark" name="mark">Mark:</label> <input type="text"
				class="form-control" id="mark" name="mark" value="${item.mark}"
				placeholder="Enter mark">
		</div>
		<div class="form-group">
			<label id="dateOfCreation" name="dateOfCreation">Date of
				creation:</label> <input type="text" class="form-control"
				id="dateOfCreation" name="dateOfCreation"
				value="${item.dateOfCreation}"
				placeholder="Enter your date of creation">
		</div>
		<div class="form-group">
			<label id="dateOfLastEdit" name="dateOfLastEdit">Date of last
				edit:</label> <input type="text" class="form-control" id="dateOfLastEdit"
				name="dateOfLastEdit" value="${item.dateOfLastEdit}"
				placeholder="Enter date of last edit">
		</div>
		<div>
			<div class="col-md-3">
				<label id="assingedUser.id" name="assingedUser.id">Assigned
					User:</label>
			</div>
			
				<select id="assignedUser.id" name="assignedUser.id">
					<c:forEach items="${userList}" var="user">
						<option value="${user.id}">${user.username}</option>
					</c:forEach>
				</select>
		</div>
		<div class="row">
			<input type="submit" class="btn btn-primary" value="Save" />
		</div>
	</form:form>

</body>
</html>