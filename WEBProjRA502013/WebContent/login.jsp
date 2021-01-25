<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
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
	<h1>Login</h1>
	<form method="post" action="/WEBProjRA502013/LoginServlet">
	<div style="color: #FF0000;">${errorMessage}</div>
	<table border="1" align="center">
		<tr>
			<td>Username:</td>
			<td><input type="text" name="username" id="username"/></td>
			<td><label id="usernameError"></label></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type="text" name="password" id="password"/></td>
			<td><label id="passwordError"></label></td>
		</tr>
		<tr><td colspan="3"><input class="submit-button basic" type="submit" value="Login"/></td></tr>
	</table>
	</form>
</div>
<script type="text/javascript" src="js/login.js"></script>
</body>
</html>