<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Apartment Overview</title>
	<link href="css/mainpage.css" rel="stylesheet" type="text/css">
	<link href="css/deletemodal.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="mainPanelLarge">
	<form method="post" action="/WEBProjRA502013/FindApartmentServlet">
		<h1>Find Apartments</h1>
		<table border="1" align="center">
		<tr>
			<td>By Type:</td>
			<td>
				<select name="type">
					<option value="NONE">Ignore Type</option>
					<option value="ROOM">Room</option>
					<option value="FULL">Full</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>By Room Count (leave blank to ignore):</td>
			<td><input type="text" name="roomCount" id="guestCount"/></td>
		</tr>
		<tr>
			<td>By Guest Count (leave blank to ignore):</td>
			<td><input type="text" name="guestCount" id="guestCount"/></td>
		</tr>
		<tr>
			<td>By Price (this or less - leave blank to ignore):</td>
			<td><input type="text" name="price" id="price"/></td>
		</tr>
		<tr><td colspan="2"><input type="submit" class="submitButton" value="Find Apartments"/></td></tr>
	</table>
	</form>
	<h1>Apartment Overview</h1>
	<table border="1" align="center">
		<tr>
			<th>
				<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
					<input class="button" name="sortBtn" type="submit" value="Type"/>
				</form>
			</th>
			<th>
				<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
					<input class="button" name="sortBtn" type="submit" value="Room Count"/>
				</form>
			</th>
			<th>
				<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
					<input class="button" name="sortBtn" type="submit" value="Guest Count"/>
				</form>
			</th>
			<th>
				<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
					<input class="button" name="sortBtn" type="submit" value="Location"/>
				</form>
			</th>
			<th>
				<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
					<input class="button" name="sortBtn" type="submit" value="Host"/>
				</form>
			</th>
			<th>
				<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
					<input class="button" name="sortBtn" type="submit" value="Price"/>
				</form>
			</th>
			<th>
				<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
					<input class="button" name="sortBtn" type="submit" value="Booking Time"/>
				</form>
			</th>
			<th>
				<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
					<input class="button" name="sortBtn" type="submit" value="Cancel Time"/>
				</form>
			</th>
			<th>
				<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
					<input class="button" name="sortBtn" type="submit" value="Status"/>
				</form>
			</th>
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
			<tr>
				<td align="left" colspan="100%">
				 	<form style="display:inline-block;" method="get" action="/WEBProjRA502013/ViewCommentsServlet">
				 		<input hidden="true" type="text" name="currentRow" value="${apartment.id}"/>
				 		<input class="button smaller" id="${apartment.id}" type="submit" value="View Comments"/>
				 	</form>
				<c:if test="${sessionScope.role == 'HOST' || sessionScope.role == 'ADMIN'}">
					 	<form style="display:inline-block;" method="get" action="/WEBProjRA502013/EditApartmentServlet">
					 		<input hidden="true" type="text" name="currentRow" value="${apartment.id}"/>
					 		<input class="button smaller" id="${apartment.id}" type="submit" value="Edit"/>
					 	</form>

					 	<input style="display:inline-block;" class="deleteButton smaller" id="${apartment.id}" 
					 		onclick="displayDeleteModal('${apartment.id}')" type="submit" value="Delete"/>

				</c:if>
				<c:if test="${sessionScope.role == 'GUEST'}">

					 	<form style="display:inline-block;" method="get" action="/WEBProjRA502013/ReserveApartmentServlet">
					 		<input hidden="true" type="text" name="currentRow" value="${apartment.id}"/>
					 		<input class="button reserve smaller" id="${apartment.id}" type="submit" value="Reserve"/>
					 	</form>
				</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${sessionScope.role == 'HOST'}">
		<h2>Inactive Apartments</h2>
		<table border="1" align="center">
			<tr>
				<th>
					<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
						<input class="button" name="sortInactiveBtn" type="submit" value="Type"/>
					</form>
				</th>
				<th>
					<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
						<input class="button" name="sortInactiveBtn" type="submit" value="Room Count"/>
					</form>
				</th>
				<th>
					<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
						<input class="button" name="sortInactiveBtn" type="submit" value="Guest Count"/>
					</form>
				</th>
				<th>
					<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
						<input class="button" name="sortInactiveBtn" type="submit" value="Location"/>
					</form>
				</th>
				<th>
					<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
						<input class="button" name="sortInactiveBtn" type="submit" value="Host"/>
					</form>
				</th>
				<th>
					<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
						<input class="button" name="sortInactiveBtn" type="submit" value="Price"/>
					</form>
				</th>
				<th>
					<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
						<input class="button" name="sortInactiveBtn" type="submit" value="Booking Time"/>
					</form>
				</th>
				<th>
					<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
						<input class="button" name="sortInactiveBtn" type="submit" value="Cancel Time"/>
					</form>
				</th>
				<th>
					<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
						<input class="button" name="sortInactiveBtn" type="submit" value="Status"/>
					</form>
				</th>
			</tr>
			<c:forEach var="apartment" items="${sessionScope.inactiveApartments}">
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
	</c:if>
</div>

<div id="id01" class="modal">
</div>

<div id="id02" class="modal2">
  <span onclick="closeDeleteModal()" class="close" title="Close Modal">&times;</span>
  <form class="modal-content" method="post" action="/WEBProjRA502013/DeleteApartmentServlet">
    <div class="container">
      <h1 class="deleteModalHeader" id="deleteHeader">Delete Apartment</h1>
      <p>Are you sure you want to delete this apartment?</p>

      <div class="clearfix">
       	<input hidden="true" type="text" id="currentSelectedApartment" name="currentSelectedApartment" value="a"/>
       	<input class="deleteButton small" id="" type="submit" value="Yes"/>
        <button onclick="closeDeleteModal()" type="button" class="button small">No</button>
      </div>
    </div>
  </form>
</div>

<script>
function displayDeleteModal(id) {
	document.getElementById('id01').style.display='block';
	document.getElementById('id02').style.display='block';
	document.getElementById("currentSelectedApartment").value = id;
}
function closeDeleteModal() {
	document.getElementById('id01').style.display='none';
	document.getElementById('id02').style.display='none';
}
</script>

</body>
</html>