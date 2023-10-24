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
    <form name="edit_book_form" action="update" method="get" class="center-form">
    <div class="center-input-areas">
        <p>
        	<input type="hidden" name="action" value="update">
        </p>
        <p>
        	<input type="hidden" name="bookId" value="${bookToEdit.id}">
        </p>
		<p>
			<label>Title:</label>
			<input type="text" name="booktitle" value="${bookToEdit.title}">
		</p>
		<p>
			<label>Author:</label>
			<input type="text" name="bookauthor" value="${bookToEdit.author}">
		</p>
		<p>
			<label>Price:</label>
			<input type="text" name="bookprice" value="${bookToEdit.price}">
		</p>
		<p>
			<input type="submit" value="Update">
		</p>
	</div> 
    </form>
    </div> 
</div> 
</body>
</html>
