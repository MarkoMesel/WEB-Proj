<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<form method="post" action="/WEBProjRA502013/LoginServlet">
<div style="color: #FF0000;">${errorMessage}</div>
<table border="1">
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
	<tr><td colspan="2"><input type="submit" value="Login"/></td></tr>
</table>
</form>

<script type="text/javascript" src="js/login.js"></script>
</body>
</html>