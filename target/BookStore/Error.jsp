<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>An Error Occurred</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/error.css">
</head>
<body>
<div class="container">
	<h1>We're Sorry :|</h1>
	<div class="error-message">
    	<p><%= request.getAttribute("errorMessage") %></p>
	</div>	
	
</div>
</body>
</html>