<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add ApartmentPictures</title>
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
	<form method="post" action="/WEBProjRA502013/AddApartmentPicturesServlet" enctype="multipart/form-data">
		<h1>Add Apartment Pictures</h1>
		<label for="myfile">Select files:</label>
		<input type="file" id="myfile" name="myfile" multiple><br><br>
		<input type="submit" class="submit-button basic">
 	</form>
</div>
<script type="text/javascript" src="js/addApartment.js"></script>
</body>
</html>