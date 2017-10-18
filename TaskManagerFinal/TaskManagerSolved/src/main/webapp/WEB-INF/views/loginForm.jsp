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
<title>Login</title>
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
			<a class="navbar-brand" href="home">Home</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="addUser">Register</a></li>
		</ul>
	</div>
	</nav>
	<h2>User Login</h2>
	<form:form role="form" action="userLogin" modelAttribute="user">
		<div class="form-group">
			<label id="username" name="username">Username:</label> <input
				type="text" class="form-control" id="username" name="username"
				placeholder="Enter username">
		</div>
		<div class="form-group">
			<label id="password" name="password">Password:</label> <input
				type="password" class="form-control" id="password" name="password"
				placeholder="Enter password">
		</div>
		<input type="submit" class="btn btn-default" value="Log in" />
	</form:form>
</body>
</html>