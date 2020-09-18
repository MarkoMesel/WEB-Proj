<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Apartment Overview</title>
</head>
<body>
<form method="post" action="/WEBProjRA502013/ApartmentOverviewServlet">
	<h1>Find Apartments</h1>
	<table border="1">
	<tr><td>By Type:</td>
		<td>
			<select name="type">
				<option value="NONE">Ignore Type</option>
				<option value="ROOM">Room</option>
				<option value="FULL">Full</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>By Guest Count (leave blank to ignore):</td>
		<td><input type="text" name="guestCount" id="guestCount"/></td>
	</tr>
	<tr>
		<td>By Price (this or less - leave blank to ignore):</td>
		<td><input type="text" name="price" id="price"/></td>
	</tr>
	<tr><td colspan="2"><input type="submit" value="Find Apartments"/></td></tr>
</table>
</form>
<h1>Apartment Overview</h1>
<table border="1">
	<tr>
	  <th>type</th>
	  <th>Room Count</th>
	  <th>Guest Count</th>
	  <th>Location</th>
	  <th>Host</th>
	  <th>Price</th>
	  <th>Booking Time</th>
	  <th>Cancel Time</th>
	  <th>Status</th>
	</tr>
	<c:forEach var="apartment" items="${sessionScope.apartments}">
		<tr>
		  <td><c:out value="${apartment.type}" /></td>
		  <td><c:out value="${apartment.roomCount}" /></td>
		  <td><c:out value="${apartment.guestCount}" /></td>
		  <td><c:out value="${apartment.location}" /></td>
		  <td><c:out value="${apartment.host}" /></td>
		  <td><c:out value="${apartment.price}" /></td>
		  <td><c:out value="${apartment.bookingTime}" /></td>
		  <td><c:out value="${apartment.cancelTime}" /></td>
		  <td><c:out value="${apartment.status}" /></td>
		</tr>
	</c:forEach>
</table>

</body>
</html>