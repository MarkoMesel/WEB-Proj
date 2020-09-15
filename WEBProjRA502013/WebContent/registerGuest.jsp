<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Guest Account</title>
</head>
<body>
<form method="post" action="/WEBProjRA502013/RegisterGuestServlet">
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
	<tr><td colspan="2"><input type="submit" value="Register"/></td></tr>
</table>
</form>

<script type="text/javascript" src="js/registerGuest.js"></script>
</body>
</html>