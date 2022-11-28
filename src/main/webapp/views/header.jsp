<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <%
		String username = null;
		if(session.getAttribute("username") == null){
			System.out.println("username not set");
		} else {
			username = (String)session.getAttribute("username");
		}
	%>
	<style>
		.heading {
		    text-decoration: none;
    		color: black;
		}
		.heading:hover{
			text-decoration: none;
		}
	</style>
	
	<header class="container-fluid pt-2 mb-2">
        <div class="row">
        	<div class="col-3">
        	<%
            	if(username != null){
            %>
	        		<a class="heading" href="/library.management/books">
	        			<svg xmlns="http://www.w3.org/2000/svg" width="48" fill="currentColor" class="bi bi-house" viewBox="0 0 16 16">
						  <path fill-rule="evenodd" d="M2 13.5V7h1v6.5a.5.5 0 0 0 .5.5h9a.5.5 0 0 0 .5-.5V7h1v6.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5zm11-11V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z"/>
						  <path fill-rule="evenodd" d="M7.293 1.5a1 1 0 0 1 1.414 0l6.647 6.646a.5.5 0 0 1-.708.708L8 2.207 1.354 8.854a.5.5 0 1 1-.708-.708L7.293 1.5z"/>
						</svg>
					</a>
        	<%
            	}
            %>
        	</div>
            <div class="col-6 text-center">
                <h2><a class="heading" href="/library.management/books">Library Management</a></h2>
            </div>
            
            <%
            	if(username != null){
            %>
		            <div class="col-3 d-flex justify-content-end align-items-center">
		                <span class="m-2 text-capitalize">Hi <%= username %></span>
		                <a href="/library.management/logout" class="btn btn-outline-danger">Logout</a>
		            </div>
            <%
            	}
            %>
            
        </div>
        <hr>
    </header>