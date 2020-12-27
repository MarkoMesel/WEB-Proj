<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add ApartmentPictures</title>
	<link href="css/mainpage.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="mainPanel">
	<form method="post" action="/WEBProjRA502013/AddApartmentPicturesServlet" enctype="multipart/form-data">
		<h1>Add Apartment Pictures</h1>
		<label for="myfile">Select files:</label>
		<input type="file" id="myfile" name="myfile" multiple><br><br>
		<input type="submit">
 	</form>
</div>
<script type="text/javascript" src="js/addApartment.js"></script>
</body>
</html>