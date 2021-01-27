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
	<table class="table-entry">
		<tr>
			<td><label class="label-entry">Username</label>
			<input type="text" name="username" id="username"/>
			<label class="label-entry-error" id="usernameError"></label></td>
		</tr>
		<tr>
			<td><label class="label-entry">Password</label>
			<input type="text" name="password" id="password"/>
			<label class="label-entry-error" id="passwordError"></label></td>
		</tr>
	</table>
	<input class="submit-button basic" type="submit" value="Login"/>
	</form>
</div>
<script type="text/javascript" src="js/login.js"></script>
</body>
</html>