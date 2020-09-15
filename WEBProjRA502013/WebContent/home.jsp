<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>
<body>
	<h1>Welcome!</h1>
	<% if (session.getAttribute("id") != null && session.getAttribute("role") == "ADMIN") { %>
	    <p> You are admin. </p>
	<% } else if (session.getAttribute("id") != null && session.getAttribute("role") == "HOST") { %>
	    <p> You are host. </p>
	<% } else if (session.getAttribute("id") != null && session.getAttribute("role") == "GUEST") { %>
		<p> You are guest. </p>
	<% } else { %>
		<p> What are you??? </p>
	<% } %>
	<% if(session.getAttribute("id") != null) { %>
	<form method="post" action="/WEBProjRA502013/LogoutServlet">
		<input type="submit" value="Logout"/>
	</form>
	<% } %>
</body>
</html>