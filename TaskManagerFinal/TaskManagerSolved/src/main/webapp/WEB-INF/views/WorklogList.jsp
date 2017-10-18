<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Worklog List</title>
<!-- Bootstrap CSS -->
<%-- <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"> --%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/startbootstrap-sb-admin-2/3.3.7/css/sb-admin-2.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/startbootstrap-sb-admin-2/3.3.7/css/timeline.min.css">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-T8Gy5hrqNKT+hzMclPo118YTQO6cYprQmhrYwIiQ/3axmI1hQomh7Ud2hPOy8SP1"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/startbootstrap-sb-admin-2/3.3.7/js/sb-admin-2.min.js"></script>

<style type="text/css">
.arrow {
	float: right;
	line-height: 1.42857
}

.glyphicon.arrow:before {
	content: "\e079"
}

.active>a>.glyphicon.arrow:before {
	content: "\e114"
}

.fa.arrow:before {
	content: "\f104"
}

.active>a>.fa.arrow:before {
	content: "\f107"
}

.plus-times {
	float: right
}

.fa.plus-times:before {
	content: "\f067"
}

.active>a>.fa.plus-times {
	filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=1);
	-webkit-transform: rotate(45deg);
	-moz-transform: rotate(45deg);
	-ms-transform: rotate(45deg);
	-o-transform: rotate(45deg);
	transform: rotate(45deg)
}

.plus-minus {
	float: right
}

.fa.plus-minus:before {
	content: "\f067"
}

.active>a>.fa.plus-minus:before {
	content: "\f068"
}

.myrow-container {
	margin: 20px;
}
</style>
</head>
<body class="container-fluid">
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header"></div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="logout">Log out</a></li>
		</ul>
	</div>
	</nav>
	<div id="wrapper">
		<c:import url="/Menu" />
		<div id="page-wrapper">
			<div class="panel panel-default">
				<div class="panel-title">
					<h3 align="center" style="font-size: 1.3em;">
						<b>Worklog List</b> <br> <b>Worklogs of: <c:out value="${gomna}" />
						</b>
					</h3>
				</div>
			</div>
			<div class="panel-body" style="margin-top: 50px;">

				<c:set var="object" value="${itemList.pageList[0]}" />
				<form class="form-control" style="border: none;"
					action="<%=request.getContextPath()%>/getAllWorklogs">
					<input type="hidden" name="parentId" id="parentId"
						value='${parentId}' />

					<div class="row">
						<div class="col-md-2">
							Search by:
							<div class="col-md-10">
								<select class="form-control"
									name="searchColumn${itemClass['class'].simpleName}"
									id="searchColumn${itemClass['class'].simpleName}">
									<c:forEach var="field" items="${columnNames}">
										<c:if test="${searchColumn == field.value}">
											<option value="${field.value}" label="${field.value}"
												selected="selected">${field.value}</option>
										</c:if>
										<c:if test="${searchColumn != field.value}">
											<option value="${field.value}" label="${field.value}">${field.value}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-md-2">
							Type your search here:
							<div class="col-md-12">
								<input class="form-control" type="text"
									name="searchName${itemClass['class'].simpleName}"
									id="searchName${itemClass['class'].simpleName}"
									value="${searchName}">
							</div>
						</div>
						<div class="col-md-2">
							Sort By:
							<div class="col-md-10">
								<select class="form-control"
									name="sortColumn${itemClass['class'].simpleName}"
									id="sortColumn${itemClass['class'].simpleName}">
									<c:forEach var="field" items="${columnNames}">
										<c:if test="${sortColumn == field.value}">
											<option value="${field.value}" label="${field.value}"
												selected="selected">${field.value}</option>
										</c:if>
										<c:if test="${sortColumn != field.value}">
											<option value="${field.value}" label="${field.value}">${field.value}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-md-2">
							Order:
							<div class="col-md-10">
								<select class="form-control"
									name="sortOrder${itemClass['class'].simpleName}"
									id="sortOrder${itemClass['class'].simpleName}">
									<c:if test="${sortOrder == 'ascending'}">
										<option value="ascending" label="ascending"
											selected="selected">ascending</option>
									</c:if>
									<c:if test="${sortOrder != 'ascending'}">
										<option value="ascending" label="ascending">ascending</option>
									</c:if>
									<c:if test="${sortOrder == 'descending'}">
										<option value="descending" label="descending"
											selected="selected">descending</option>
									</c:if>
									<c:if test="${sortOrder != 'descending'}">
										<option value="descending" label="descending">descending</option>
									</c:if>
								</select>
							</div>
						</div>
						<div class="col-md-2">
							Items per page:
							<div class="col-md-9">
								<select class="form-control"
									name="itemsPerPage${itemClass['class'].simpleName}"
									id="itemsPerPage${itemClass['class'].simpleName}"
									onchange="this.form.submit()">
									<c:forEach var="i" begin="5" end="25" step="5">
										<c:if test="${i == itemsPerPage}">
											<option value="${i}" label="${i}" selected="selected">${i}</option>
										</c:if>
										<c:if test="${i != itemsPerPage}">
											<option value="${i}" label="${i}">${i}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
							<input type="hidden" id="parentId" name="parentId" />
							<div class="col-md-2 col-md-offset-1">
								<input class="btn btn-default " type="submit" value='Submit' />
							</div>
						</div>
						<div class="col-md-1 col-md-offset-1" style="margin-top: 18px;">
							<c:if test="${maxPages != 1}">
								<c:if test="${page == 0}">
									<div class="col-md-1"></div>
									<div class="col-md-1">
										<button class="btn btn-default " onclick="this.form.submit()"
											name="page${itemClass['class'].simpleName}"
											id="page${itemClass['class'].simpleName}" value='${page + 1}'>${page + 1}</button>
									</div>
								</c:if>
								<c:if test="${page == (maxPages - 1)}">
									<div class="col-md-1">
										<button class="btn btn-default " onclick="this.form.submit()"
											name="page${itemClass['class'].simpleName}"
											id="page${itemClass['class'].simpleName}" value='${page - 1}'>${page - 1}</button>
									</div>
									<div class="col-md-1"></div>
								</c:if>
								<c:if test="${page > 0 && page < (maxPages-1)}">
									<div class="col-md-1">
										<button class="btn btn-default " onclick="this.form.submit()"
											name="page${itemClass['class'].simpleName}"
											id="page${itemClass['class'].simpleName}" value='${page - 1}'>${page - 1}</button>
									</div>
									<div class="col-md-1">
										<button class="btn btn-default " onclick="this.form.submit()"
											name="page${itemClass['class'].simpleName}"
											id="page${itemClass['class'].simpleName}" value='${page + 1}'>${page + 1}</button>
									</div>
								</c:if>
							</c:if>
						</div>
					</div>

					<c:if test="${empty itemList.pageList}">
						<h3 align="center">There are no Worklogs</h3>
					</c:if>
					<c:if test="${not empty itemList.pageList}">
						<!-- FORM -->

						<div class="row" style="margin-top: 40px;">
							<table class="table table-striped table-hover table-bordered">
								<thead>
									<tr>
										<th>Hours</th>
										<th>Task</th>
										<th>Date Worklog</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${itemList.pageList}" var="item">
										<tr>
											<th><c:out value="${item.hours}" /></th>
											<th><c:out value="${item.task.title}" /></th>
											<th><c:out value="${item.dateWorklog}" /></th>

											<th><a class="text-muted"
												href="<%=request.getContextPath()%>/editWorklog?id=<c:out value='${item.id}'/>">Edit</a></th>
											<th><a class="text-muted"
												href="<%=request.getContextPath()%>/deleteWorklog?id=<c:out value='${item.id}'/>">Delete</a></th>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</c:if>
			</div>
		</div>
		</form>
	</div>
</body>
</html>
