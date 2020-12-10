<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reserve Apartment</title>
	<link href="css/mainpage.css" rel="stylesheet" type="text/css">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>jQuery UI Datepicker - Default functionality</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
<div class="mainPanel">
	<h1>Reserve Apartment</h1>
	<input hidden="true" type="text" id="apartmentDates" value="${sessionScope.apartmentDates}"/>
	<form method="post" action="/WEBProjRA502013/ReserveApartmentServlet">
	<table border="1" align="center" >
		<tr>
			<td>Reservation date:</td>
			<td><input type="text" name="datepicker" id="datepicker"></td>
			<td><label id="datepickerError"></label></td>
		</tr>
		<tr>
			<td>Num. of nights:</td>
			<td><input type="text" name="numberOfNights" id="numberOfNights"></td>
			<td><label id="numberOfNightsError"></label></td>
		</tr>
		<tr>
			<td>Message to Host:</td>
			<td><input type="text" name="reservationMessage" id="reservationMessage"></td>
			<td><label id="reservationMessageError"></label></td>
		</tr>
		<tr><td colspan="3"><input class="submitButton" type="submit" value="Reserve Apartment"/></td></tr>
	</table>
	</form>
</div>

<script type="text/javascript" src="js/reserveApartment.js"></script>
</body>
</html>