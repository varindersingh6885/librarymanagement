<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
	
	<%
		if(session.getAttribute("username") != null) {
			response.sendRedirect("/library.management/books");
			return;
		}
		String loginSuccess = (String)request.getAttribute("loginSuccess");
	%>

	<jsp:include page="header.jsp"></jsp:include>
    <div class="d-flex justify-content-center">
        <% if(loginSuccess != null && loginSuccess.equals("false")) { %>
			<div class="alert alert-danger alert-dismissible fade show w-50 text-center" role="alert">
		        <strong class="text-danger">Login failed! </strong>
		        <span>Invalid credentials</span>
		        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>	
		<% } %>
    </div>
	
	<div class="container-fluid d-flex flex-wrap justify-content-center align-items-center" style="height: 75vh;">
        <div class="row">
            <div class="col-12">
    
                <h3 class="text-center col-12">Librarian Login</h3>
                
            </div>
            <form class="col-12 ps-5 pe-5" method="post" action="login">
                <div class="row p-2">
                    <label class="col-3" for="username">Username</label>
                    <div class="col-9">
                        <input class="w-100" type="text" id="username" name="username" minlength="5" maxlength="50">
                    </div>
                </div>
                <div class="row p-2">
                    <label class="col-3" for="password">Password</label>
                    <div class="col-9">
                        <input class="w-100" type="password" id="password" name="password" minlength="5" maxlength="50">
                    </div>
                </div>
                <div class="row p-2">
                    <div class="col">
                        <input class="btn btn-primary btn-block w-100" type="submit" value="Login">
                    </div>
                </div>
            </form>
        </div>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</html>