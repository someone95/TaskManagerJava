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
<title>Add a comment</title>
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
	<h2>Add a comment</h2>
	<form:form method="post" action="saveComment" modelAttribute="item">
		<inpyt type="hidden" id="id" name="id" value="${item.id}" />
		<input type="hidden" name="task.id" id="task.id"
			value="${parentTaskId}" />
		<div class="form-group">
			<label id="description" name="description">Comment:</label> <input
				type="text" class="form-control" id="description" name="description"
				value="${item.description}" />
		</div>
		<input type="submit" class="btn btn-primary" value="Save" />
	</form:form>

</body>
</html>