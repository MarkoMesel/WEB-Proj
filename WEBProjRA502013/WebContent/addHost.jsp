<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Host</title>
	<link href="css/mainstyle.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="mainPanel">
	<div class="site-header">
		<div class="site-header-content">
		<a href="home.jsp">
			<img src="images/logo.png" alt="webProjLogo">
		</a>
		</div>
	</div>
	<h1>Add Host</h1>
	<form method="post" action="/WEBProjRA502013/AddHostServlet">
	<table class="table-entry" >
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
		</tr>
	</table>
	<input class="submit-button basic" type="submit" value="Add Host"/>
	</form>
</div>
<script type="text/javascript" src="js/registerGuest.js"></script>
</body>
</html>