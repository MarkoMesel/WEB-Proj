<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Amenity</title>
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
	<table class="table-entry">
		<tr>
			<td>
				<label class="label-entry">Name</label>
				<input type="text" name="amenityName" id="amenityName" value=""/>
				<label class="label-entry-error" id="amenityNameError"></label>
			</td>
		</tr>
		<tr>
			<td>
			<label class="label-entry">Details</label>
			<input type="text" name="amenityDetails" id="amenityDetails" value=""/>
			<label class="label-entry-error" id="amenityDetailsError"></label></td>
		</tr>
	</table>
	<input type="submit" class="submit-button basic" value="Add Amenity"/>
	</form>
</div>
<script type="text/javascript" src="js/amenityValidation.js"></script>
</body>
</html>