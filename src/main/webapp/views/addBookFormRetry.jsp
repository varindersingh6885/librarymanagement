<%@page import="com.nagarro.training.springmvc.entity.Author"%>
<%@page import="com.nagarro.training.springmvc.entity.Book"%>
<%
	Book book = (Book)request.getAttribute("book");
	Author[] authors = (Author[])request.getAttribute("authors");
	String errorMsg = (String)request.getAttribute("errorMsg");
%>

	<div class="container-fluid pt-2 mb-2">
		<div class="row">
			<div class="col-6 offset-3 text-center">
				<h4 class="heading">Add Book Details </h4>
			</div>
			<div class="col-3 d-flex justify-content-end align-items-center">
				<a href="/library.management/books" class="btn btn-outline-primary">View Books</a>
			</div>
		</div>
	</div>
	
	<div class="alert alert-danger alert-dismissible fade show w-50 text-center m-auto" role="alert">
	        <strong><%= errorMsg %></strong>
	        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
    </div>

<div class="container-fluid p-4">
		<form class="w-75 m-auto p-4" action="/library.management/books/add" method="post">
			<div class="form-group row mb-2">
				<label for="inputBookCode" class="col-sm-2 col-form-label">Book Code</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputBookCode" name="bookCode"
						placeholder="Book Code" value="<%= book.getBookCode() %>" pattern="[a-zA-Z0-9]+" title="Alphanumeric characters are supported only." required>
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
						id="date" value="<%= book.printDate() %>">
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-10">
					<button type="submit" class="btn btn-primary">Submit</button>
					<a href="/library.management/books" class="btn btn-outline-danger">Cancel</a>
				</div>
			</div>
		</form>
	</div>