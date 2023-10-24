<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
</head>
<body>
<div class="container">
	<div id="links">
		<h1><a href="list">Book Store</a></h1>
		<h2><a href="new">Add New Book</a></h2>
	</div>
	
	<div id="form-container">
	<form name="book_form" method="post" action="insert" class="center-form">
	<caption><h2>New Book Form</h2></caption>
	<div class="center-input-areas">
		<p>
			<label>Title:</label>
			<input type="text" name="booktitle" required>
		</p>
		<p>
			<label>Author:</label>
			<input type="text" name="bookauthor" required>
		</p>
		<p>
			<label>Price:</label>
			<input type="text" name="bookprice" required>
		</p>
		<p>
			<input type="submit" value="Submit">
		</p>
	</div>
	</form>
	</div>
</div> 
</body>
</html>