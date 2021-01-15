<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Guest Account</title>
	<link href="css/mainpage.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="mainPanel">
	<h1>Register Guest Account</h1>
	<form method="post" action="/WEBProjRA502013/AddHostServlet">
	<table border="1" align="center" >
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
		<tr>
			<td>Repeat Password:</td>
			<td><input type="text" name="repeatPassword" id="repeatPassword"/></td>
			<td><label id="repeatPasswordError"></label></td>
		</tr>
		<tr>
			<td>First Name:</td>
			<td><input type="text" name="firstName" id="firstName"/></td>
			<td><label id="firstNameError"></label></td>
		</tr>
		<tr>
			<td>Last Name:</td>
			<td><input type="text" name="lastName" id="lastName"/></td>
			<td><label id="lastNameError"></label></td>
		</tr>
		<tr><td>Gender:</td>
			<td>
				<select name="gender">
				  <option value="MALE">Male</option>
				  <option value="FEMALE">Female</option>
				</select>
			</td>
		</tr>
		<tr><td colspan="3"><input class="submitButton" type="submit" value="Add Host"/></td></tr>
	</table>
	</form>
</div>
<script type="text/javascript" src="js/registerGuest.js"></script>
</body>
</html>