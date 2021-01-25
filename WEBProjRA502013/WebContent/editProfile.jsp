<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Account Information</title>
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
	<h1>Edit Profile</h1>
	<form method="post" action="/WEBProjRA502013/EditProfileServlet">
	<div style="color: #008000;">${successMessage}</div>
	<table border="1" align="center">
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
		<tr><td colspan="3"><input type="submit" class="submit-button basic" value="Edit Profile"/></td></tr>
	</table>
	</form>
</div>
<script type="text/javascript" src="js/editProfile.js"></script>
</body>
</html>