<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Guest Account</title>
	<link href="css/mainstyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="mainPanel">
	<h1>Register Guest Account</h1>
	<div class="site-header">
		<div class="site-header-content">
		<a href="home.jsp">
			<img src="images/logo.png" alt="webProjLogo">
		</a>
		</div>
	</div>
	<form method="post" action="/WEBProjRA502013/RegisterGuestServlet">
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
		<tr>
			<td><label class="label-entry">Repeat Password</label>
			<input type="text" name="repeatPassword" id="repeatPassword"/>
			<label class="label-entry-error" id="repeatPasswordError"></label></td>
		</tr>
		<tr>
			<td><label class="label-entry">First Name</label>
			<input type="text" name="firstName" id="firstName"/>
			<label class="label-entry-error" id="firstNameError"></label></td>
		</tr>
		<tr>
			<td><label class="label-entry">Last Name</label>
			<input type="text" name="lastName" id="lastName"/>
			<label class="label-entry-error" id="lastNameError"></label></td>
		</tr>
		<tr><td><label class="label-entry">Gender</label>
				<select name="gender">
				  <option value="MALE">Male</option>
				  <option value="FEMALE">Female</option>
				</select>
			</td>
		</tr>
	</table>
	<input class="submit-button basic" type="submit" value="Register"/>
	</form>
</div>
<script type="text/javascript" src="js/registerGuest.js"></script>
</body>
</html>