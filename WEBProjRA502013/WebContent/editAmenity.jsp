<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Amenity</title>
	<link href="css/mainstyle.css" rel="stylesheet" type="text/css">
</head>
<body>

<c:if test="${sessionScope.role != 'ADMIN'}">
	<script>
		document.location.href="/WEBProjRA502013/ForbiddenErrorServlet"; 
	</script>
</c:if>

<div class="mainPanel">
	<div class="site-header">
		<div class="site-header-content">
		<a href="home.jsp">
			<img src="images/logo.png" alt="webProjLogo">
		</a>
		</div>
	</div>
	<h1>Edit Amenity</h1>
	<form method="post" action="/WEBProjRA502013/EditAmenityServlet">
	<div style="color: #00BF00;">${successMessage}</div>
	<table class="table-entry">
		<tr>
			<td>
			<label class="label-entry">Change Name</label>
			<input type="text" name="amenityName" id="amenityName" value="${sessionScope.amenityName}"/>
			<label class="label-entry-error" id="amenityNameError"></label></td>
		</tr>
		<tr>
			<td>
				<label class="label-entry">Change Details</label>
				<input type="text" name="amenityDetails" id="amenityDetails" value="${sessionScope.amenityDetails}"/>
				<label class="label-entry-error" id="amenityDetailsError"></label>
			</td>
		</tr>
	</table>
	<input type="submit" class="submit-button basic" value="Edit Amenity"/>
	</form>
</div>
<script type="text/javascript" src="js/amenityValidation.js"></script>
</body>
</html>