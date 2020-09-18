<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Account Information</title>
</head>
<body>
<form method="post" action="/WEBProjRA502013/EditProfileServlet">
<div style="color: #008000;">${successMessage}</div>
<table border="1">
	<tr>
		<td>Change Password:</td>
		<td><input type="text" name="password" id="password" value="${sessionScope.password}"/></td>
		<td><label id="passwordError"></label></td>
	</tr>
	<tr>
		<td>Change First Name:</td>
		<td><input type="text" name="firstName" id="firstName" value="${sessionScope.firstName}"/></td>
		<td><label id="firstNameError"></label></td>
	</tr>
	<tr>
		<td>Change Last Name:</td>
		<td><input type="text" name="lastName" id="lastName" value="${sessionScope.lastName}"/></td>
		<td><label id="lastNameError"></label></td>
	</tr>
	<tr><td>Change Gender:</td>
		<td>
			<select name="gender" id="gender">
			  <option value="MALE" ${sessionScope.gender == 'MALE' ? 'selected' : ''}>Male</option>
			  <option value="FEMALE" ${sessionScope.gender == 'FEMALE' ? 'selected' : ''}>Female</option>
			</select>
		</td>
	</tr>
	<tr><td colspan="2"><input type="submit" value="Edit Profile"/></td></tr>
</table>
</form>
<script type="text/javascript" src="js/editProfile.js"></script>
</body>
</html>