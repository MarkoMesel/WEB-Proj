<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
		<link href="css/mainpage.css" rel="stylesheet" type="text/css">
</head>
<body>
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
		<tr><td colspan="3"><input class="submitButton" type="submit" value="Login"/></td></tr>
	</table>
	</form>
</div>
<script type="text/javascript" src="js/login.js"></script>
</body>
</html>