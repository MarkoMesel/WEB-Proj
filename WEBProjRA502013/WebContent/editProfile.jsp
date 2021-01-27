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
	<table class="table-entry">
		<tr>
			<td><label class="label-entry">Change First Name</label>
			<input type="text" name="firstName" id="firstName" value="${sessionScope.firstName}"/>
			<label class="label-entry-error" id="firstNameError"></label></td>
		</tr>
		<tr>
			<td><label class="label-entry">Change Last Name</label>
			<input type="text" name="lastName" id="lastName" value="${sessionScope.lastName}"/>
			<label class="label-entry-error"id="lastNameError"></label></td>
		</tr>
		<tr><td><label class="label-entry">Change Gender</label>
				<select name="gender" id="gender">
				  <option value="MALE" ${sessionScope.gender == 'MALE' ? 'selected' : ''}>Male</option>
				  <option value="FEMALE" ${sessionScope.gender == 'FEMALE' ? 'selected' : ''}>Female</option>
				</select>
			</td>
		</tr>
	</table>
	<input type="submit" class="submit-button basic" value="Edit Profile"/>
	</form>
</div>
<script type="text/javascript" src="js/editProfile.js"></script>
</body>
</html>