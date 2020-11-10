<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Guest Account</title>
	<link href="css/mainpage.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="mainPanel">
	<h1>Register Guest Account</h1>
	<form method="post" action="/WEBProjRA502013/AddApartmentServlet">
	<h2>General Info</h2>
	<table border="1" align="center" >
		<tr>
		<td>Apartment Type:</td>
			<td>
				<select name="aType" id="aType">
				  <option value="FULL">Full</option>
				  <option value="ROOM">Room</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>Number of Rooms:</td>
			<td><input type="text" name="roomCount" id="roomCount"/></td>
			<td><label id="roomCountError"></label></td>
		</tr>
		<tr>
			<td>Capacity (max num. of guests):</td>
			<td><input type="text" name="guestCount" id="guestCount"/></td>
			<td><label id="guestCountError"></label></td>
		</tr>
		<tr>
			<td>Price (per night):</td>
			<td>
			<!-- 
				<span>
					<label style="display: inline-block; width: 12px;">$</label>
					<input type="number" name="price" id="price" style="width:85px; float:right">
				</span>
			 -->
				<input type="text" name="price" id="price">
			</td>
			<td><label id="priceError"></label></td>
		</tr>
		<tr>
			<td>Check-In Time:</td>
			<td><input type="text" name="checkInTime" value="14:00" id="checkInTime"/></td>
			<td><label id="checkInTimeError"></label></td>
		</tr>
		<tr>
			<td>Check-Out Time:</td>
			<td><input type="text" name="checkOutTime" value="10:00" id="checkOutTime"/></td>
			<td><label id="checkOutTimeError"></label></td>
		</tr>
	</table>
	<h2>Location</h2>
	<table border="1" align="center" >
		<tr>
			<td>Latitude:</td>
			<td><input type="text" step="any" name="latitude" id="latitude"/></td>
			<td><label id="latitudeError"></label></td>
		</tr>
		<tr>
			<td>Longitude:</td>
			<td><input type="text" step="any" name="longitude" id="longitude"/></td>
			<td><label id="longitudeError"></label></td>
		</tr>
		<tr>
			<td>Street Name:</td>
			<td><input type="text" name="streetName" id="streetName"/></td>
			<td><label id="streetNameError"></label></td>
		</tr>
		<tr>
			<td>Street Number:</td>
			<td><input type="text" name="streetNumber" id="streetNumber"/></td>
			<td><label id="streetNumberError"></label></td>
		</tr>
		<tr>
			<td>City:</td>
			<td><input type="text" name="city" id="city"/></td>
			<td><label id="cityError"></label></td>
		</tr>
		<tr>
			<td>Post Number:</td>
			<td><input type="text" name="postNumber" id="postNumber"/></td>
			<td><label id="postNumberError"></label></td>
		</tr>
	</table>
	<h2>Amenities</h2>
	<table border="1" align="center" >
       <c:forEach var="element" items="${sessionScope.amenities}">
       	<tr>
           <td align="left">
           		<input type="checkbox" id="${element.name}" name="${element.name}" value="${element.name}">
           		<label for="${element.name}">${element.name}</label>
           </td>
           <td align="left"><label><c:out value="${element.details}"></c:out></label></td>
        </tr>
       </c:forEach>
	</table>
	<table border="1" align="center" >
		<tr><td colspan="3"><input class="submitButton" type="submit" value="AddApartment"/></td></tr>
	</table>
	</form>
</div>
<script type="text/javascript" src="js/addApartment.js"></script>
</body>
</html>