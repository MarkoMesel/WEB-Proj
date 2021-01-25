<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Account Information</title>
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
	<h1>Add Amenity</h1>
	<form method="post" action="/WEBProjRA502013/AddAmenityServlet">
	<div style="color: #00BF00;">${successMessage}</div>
	<table border="1" align="center">
		<tr>
			<td>Name:</td>
			<td><input type="text" name="amenityName" id="amenityName" value=""/></td>
			<td><label id="amenityNameError"></label></td>
		</tr>
		<tr>
			<td>Details:</td>
			<td><input type="text" name="amenityDetails" id="amenityDetails" value=""/></td>
			<td><label id="amenityDetailsError"></label></td>
		</tr>
		<tr><td colspan="3"><input type="submit" class="submit-button basic" value="Add Amenity"/></td></tr>
	</table>
	</form>
</div>
<script type="text/javascript" src="js/amenityValidation.js"></script>
</body>
</html>