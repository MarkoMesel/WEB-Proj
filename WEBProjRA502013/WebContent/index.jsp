<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
	<link href="css/mainstyle.css" rel="stylesheet" type="text/css">
</head>
<body>	
	<div class="site-header">
		<div class="site-header-content">
		<a href="home.jsp">
			<img src="images/logo.png" alt="webProjLogo">
		</a>
		</div>
	</div>
	<div class="mainPanel">
		<h1>Welcome!</h1>
		<div style="color: #008000;">${welcomeMessage}</div>
		<!-- <a class = "submit-button basic" href="/WEBProjRA502013/registerGuest.jsp">Register Account</a><br/>
		<a class = "submit-button basic" href="/WEBProjRA502013/login.jsp">Login</a><br/> -->
		<input type="submit" class="submit-button register" value="Register Account" onClick="window.location='registerGuest.jsp';"/>
		<br/>
		<input type="submit" class="submit-button login" value="Login" onClick="window.location='login.jsp';"/>
	</div>
</body>
</html>