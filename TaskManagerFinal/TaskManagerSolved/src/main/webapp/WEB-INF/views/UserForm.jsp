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
		<div class="navbar-header"></div>
		<ul class="nav navbar-nav">
		</ul>
	</div>
	</nav>
	<h2>Registration</h2>
	<form:form method="post" action="saveUser" modelAttribute="item">
		<input type="hidden" class="form-control" id="id" name="id" value="${item.id}" />
		<div class="form-group">
			<label id="username" name="username">Username:</label> <input
				type="text" class="form-control" id="username" name="username"
				value="${item.username}" placeholder="Enter your username">
		</div>
		<div class="form-group">
			<label id="password" name="password">Password:</label> <input
				type="password" class="form-control" id="password" name="password"
				value="${item.password}" placeholder="Enter your password">
		</div>
		<div class="form-group">
			<label id="firstName" name="firstName">First Name:</label> <input
				type="text" class="form-control" id="firstName" name="firstName"
				value="${item.firstName}" placeholder="Enter your first name">
		</div>
		<div class="form-group">
			<label id="lastName" name="lastName">Last Name:</label> <input
				type="text" class="form-control" id="lastName" name="lastName"
				value="${item.lastName}" placeholder="Enter your last name">
		</div>
		<c:if test="${userLogger.admin == true}">
			<select id="admin" name="admin">
				<option value="true">Admin</option>
				<option value="false">Non admin</option>
			</select>
		</c:if>
		<c:if test="${userLogger.admin == false}">
			<input type="hidden" id="admin" name="admin" value="false">
		</c:if>

		<c:if test="${userLogger != null}">
			<input type="submit" class="btn btn-primary" value="Save" />
		</c:if>
		<c:if test="${userLogger == null}">
			<input type="submit" class="btn btn-primary" value="Sign up" />
		</c:if>
	</form:form>

</body>
</html>