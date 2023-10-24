<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
</head>
<body>
<div class = "container">
	<div class = "links">
		<h1><a href="list"> Book Store </a></h1>
		<h2><a href="new"> Add New Book </a></h2>
	</div>
	<div class="booktable">
		<table>
			<caption>List of Books</caption>
			<tr>
				<th class="index">Book No.</th>
				<th class="col1">Title</th>
				<th class="col2">Author</th>
				<th class="col3">Price</th>
				<th class="col4">Actions</th>
			</tr>
			
			<c:forEach items="${book_list}" var="item">
				<tr>
					<td> ${item.getId() } </td>
					<td> ${item.getTitle() } </td>
					<td> ${item.getAuthor() } </td>
					<td> ${item.getPrice() } </td>
					<td>
           				 <a href="edit?id=<c:out value='${item.getId()}' />">Edit</a>
           				 &nbsp;&nbsp;&nbsp;&nbsp;
           				 <a id="del" href="delete?id=<c:out value='${item.getId()}' />">Delete</a>
        			</td>
				</tr>
			</c:forEach>
			
		</table>
	
		
	</div>

</div>	
</body>
</html>