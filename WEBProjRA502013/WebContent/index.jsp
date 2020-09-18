<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
	<link href="css/mainpage.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="mainPanel">
		<h1>Welcome!</h1>
		<div style="color: #008000;">${welcomeMessage}</div>
		Links:<br/>
		<a class = "button" href="/WEBProjRA502013/registerGuest.jsp">Register Account</a><br/>
		<a class = "button" href="/WEBProjRA502013/login.jsp">Login</a><br/>
	</div>
</body>
</html>