<%@page import="com.nagarro.training.springmvc.entity.Book"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Books</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

	<%
		if(session.getAttribute("username") == null){
			System.out.println("username not set");
			response.sendRedirect("/library.management/login");
			return;
		}
		
		Book[] books = (Book[])request.getAttribute("books");
		String errorMsg = (String)request.getAttribute("errorMsg");
	%>
	
	<jsp:include page="header.jsp"></jsp:include>
	
	<% if(errorMsg != null) { %>
		<div class="alert alert-danger alert-dismissible fade show w-50 text-center m-auto" role="alert">
		        <strong><%= errorMsg %></strong>
		        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
	    </div>	
	<% } %>
	
	<% if(errorMsg == null && books != null && books.length > 0) { %>
		<div class="container-fluid pt-2 mb-2">
			<div class="row">
				<div class="col-6 offset-3 text-center">
					<h4>Books Listing</h4>
				</div>
				<div class="col-3 d-flex justify-content-end align-items-center">
					<a href="/library.management/books/add" class="btn btn-outline-primary">Add Book</a>
				</div>
			</div>
		</div>
		<div class="ps-5 pe-5 pt-3 mt-2 mb-5">
			<div class="container-fluid p-0"
				style="box-sizing: border-box; max-height: 80vh; overflow-y: auto; border: 1px solid black;">
		
				<table class="table m-0 table-bordered" style="position: relative;">
					<thead class="thead-dark"
						style="position: sticky; top: 0; background: black; color: white;">
						<tr>
							<th>Book Code</th>
							<th>Book Name</th>
							<th>Author</th>
							<th>Date Added</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<%
						for (Book book : books) {
						%>
						<tr>
							<td><%=book.getBookCode()%></td>
							<td><%=book.getBookName()%></td>
							<td><%=book.getAuthor().getFullName()%></td>
							<td><%=book.printDate()%></td>
							<td>
								<a href=<%="books/edit/" + book.getBookCode()%> class="btn btn-outline-primary">Edit</a>
								<a href=<%="books/delete/" + book.getBookCode()%> onclick="return confirm('Do you want to Delete this book?')" class="btn btn-outline-danger">Delete</a>
							</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
	<% } else if(errorMsg == null) { %>
		<div class="container-fluid d-flex flex-wrap justify-content-center align-items-center" style="height : 50vh">
	        <div class="row text-center">
	            <h2 class="text-primary">No Books Available!</h2>
				<a href="/library.management/books/add">Click here to add</a>
	        </div>
    	</div>
	<% } %>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</html>