<%@page import="com.nagarro.training.springmvc.entity.Book"%>
<%@page import="com.nagarro.training.springmvc.entity.Author"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Book</title>
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
		String successMsg = (String)request.getAttribute("successMsg");
		String errorMsg = (String)request.getAttribute("errorMsg");
		Book book = (Book)request.getAttribute("book");
		Author[] authors = (Author[])request.getAttribute("authors");
	%>
	
	<jsp:include page="header.jsp"></jsp:include>
	
	<div class="container-fluid pt-2 mb-2">
		<div class="row">
			<div class="col-6 offset-3 text-center">
				<h4>Edit Book Details</h4>
			</div>
			<div class="col-3 d-flex justify-content-end align-items-center">
				<a href="/library.management/books" class="btn btn-outline-primary">View Books</a>
			</div>
		</div>
	</div>
	
	<% if(successMsg != null) { %>
		<div class="alert alert-success alert-dismissible fade show w-50 text-center m-auto" role="alert">
	        <strong><%= successMsg %></strong>
	        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>	
	<% } %>
	<% if(errorMsg != null) { %>
		<div class="alert alert-danger alert-dismissible fade show w-50 text-center m-auto" role="alert">
	        <strong><%= errorMsg %></strong>
	        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>	
	<% } %>
	

	<div class="container-fluid p-4">
		<form class="w-75 m-auto p-4" action=<%="/library.management/books/edit/"+book.getBookCode()%> method="post">
			<div class="form-group row mb-2">
				<label for="inputBookCode" class="col-sm-2 col-form-label">Book Code</label>
				<div class="col-sm-10">
					<input type="text" readonly class="form-control-plaintext" id="inputBookCode" name="bookCode"
						value="<%= book.getBookCode() %>" required>
				</div>
			</div>
			<div class="form-group row mb-2">
				<label for="inputBookName" class="col-sm-2 col-form-label">Book Name</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputBookName" name="bookName"
						placeholder="Book Name" value="<%= book.getBookName() %>" required>
				</div>
			</div>
			<div class="form-group row mb-2">
				<label for="author" class="col-sm-2 col-form-label">Author</label>
				<div class="col-sm-10">
					<select class="form-control form-select" id="inputAuthor" name="author">
							<% for(Author author : authors) { %>
								<% if(author.getId() == book.getAuthor().getId()){ %>
									<option value=<%= author.getId() %> selected>
										<%= author.getFullName() %>
									</option>
								<% } else { %>
									<option value=<%= author.getId() %>>
										<%= author.getFullName() %>
									</option>
								<% } %>
							<% } %>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label for="date" class="col-sm-2 col-form-label">Added on</label>
				<div class="col-sm-10">
					<input type="text" readonly class="form-control-plaintext"
						id="date" name="date" value="<%= book.printDate() %>">
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-10">
					<button type="submit" class="btn btn-primary">Update</button>
					<a href="/library.management/books" class="btn btn-outline-danger">Cancel</a>
				</div>
			</div>
		</form>
	
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</html>