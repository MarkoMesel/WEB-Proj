<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Account Information</title>
	<link href="css/mainpage.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="mainPanel">
	<h1>Edit Profile</h1>
	<form method="post" action="/WEBProjRA502013/ChangePasswordServlet">
	<div style="color: #008000;">${successMessage}</div>
	<table border="1" align="center">
		<tr>
			<td>Change First Name:</td>
			<td><input type="text" name="oldPassword" id="oldPassword" value=""/></td>
			<td><label id="oldPasswordError"></label></td>
		</tr>
		<tr>
			<td>Change Last Name:</td>
			<td><input type="text" name="newPassword" id="newPassword" value=""/></td>
			<td><label id="newPasswordError"></label></td>
		</tr>
		<tr><td colspan="3"><input type="submit" class="submitButton" value="Edit Profile"/></td></tr>
	</table>
	</form>
</div>
<script type="text/javascript" src="js/changePassword.js"></script>
</body>
</html>