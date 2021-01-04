<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reservation Overview</title>
	<link href="css/mainpage.css" rel="stylesheet" type="text/css">
	<link href="css/deletemodal.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="mainPanelLarge">
	<c:if test="${sessionScope.role == 'HOST' || sessionScope.role == 'ADMIN'}">
		<form method="post" id="findFiterReservationForm" action="/WEBProjRA502013/FindReservationServlet">
			<h1>Find/Filter</h1>
				<table border="1" align="center">
				<tr>
					<td>Find or Filter?</td>
					<td>
						<input type="radio" id="findRBtn" name="rBtn" onclick="changeAction(this);" value="Find" checked>
						<label for="findRBtn">Find</label><br>
					</td>
					<td>
						<input type="radio" id="filterRBtn" name="rBtn" onclick="changeAction(this);" value="Filter">
						<label for="filterRBtn">Filter</label><br>
					</td>
				</tr>
				<tr>
					<td>
						By status:
					</td>
					<td colspan="2">
						<select name="reservationStatusSearch">
							<option value="NONE">Ignore Status</option>
							<option value="CREATED">Created</option>
							<option value="REJECTED">Rejected</option>
							<option value="CANCELED">Canceled</option>
							<option value="ACCEPTED">Accepted</option>
							<option value="FINISHED">Finished</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						By username:
					</td>
					<td colspan="2">
						<input type="text" name="username" id="username"/>
					</td>					
				</tr>
				<tr><td colspan="3"><input type="submit" id="findFilterSubmitBtn" class="submitButton" value="Find Reservation"/></td></tr>
				</table>
		</form>
	</c:if>
	<h1>Reservation Overview</h1>
	<table border="1" align="center">
		<tr>
			<th>
				<form method="post" action="/WEBProjRA502013/SortReservationServlet">
					<input class="button" name="sortBtn" type="submit" value="Apartment"/>
				</form>
			</th>
			<th>
				<form method="post" action="/WEBProjRA502013/SortReservationServlet">
					<input class="button" name="sortBtn" type="submit" value="Reservation Date"/>
				</form>
			</th>
			<th>
				<form method="post" action="/WEBProjRA502013/SortReservationServlet">
					<input class="button" name="sortBtn" type="submit" value="Night Count"/>
				</form>
			</th>
			<th>
				<form method="post" action="/WEBProjRA502013/SortReservationServlet">
					<input class="button" name="sortBtn" type="submit" value="Price"/>
				</form>
			</th>
			<th>
				<form method="post" action="/WEBProjRA502013/SortReservationServlet">
					<input class="button" name="sortBtn" type="submit" value="Message"/>
				</form>
			</th>
			<th>
				<form method="post" action="/WEBProjRA502013/SortReservationServlet">
					<input class="button" name="sortBtn" type="submit" value="Guest"/>
				</form>
			</th>
			<th>
				<form method="post" action="/WEBProjRA502013/SortReservationServlet">
					<input class="button" name="sortBtn" type="submit" value="Status"/>
				</form>
			</th>
		</tr>
		<c:forEach var="reservation" items="${sessionScope.reservations}">
			<tr>
				<td><c:out value="${reservation.apartment}" /></td>
				<td><c:out value="${reservation.date}" /></td>
				<td><c:out value="${reservation.nights}" /></td>
				<td><c:out value="${reservation.price}" /></td>
				<td><c:out value="${reservation.message}" /></td>
				<td><c:out value="${reservation.guest}" /></td>
				<td><c:out value="${reservation.status}" /></td>
				<c:if test="${sessionScope.role == 'GUEST' && (reservation.status == 'CREATED' || reservation.status == 'ACCEPTED')}">
					<td align="center">
					 	<form method="post" action="/WEBProjRA502013/ReservationOverviewServlet">
					 		<input hidden="true" type="text" name="currentRow" value="${reservation.id}"/>
					 		<input class="button" name="cancelBtn" id="${reservation.id}" type="submit" value="Cancel"/>
					 	</form>
					</td>
				</c:if>
				<c:if test="${sessionScope.role == 'HOST'}">
					<c:if test="${reservation.status == 'CREATED'}">
						<td align="center">
							<form method="post" action="/WEBProjRA502013/ReservationOverviewServlet">
						 		<input hidden="true" type="text" name="currentRow" value="${reservation.id}"/>
						 		<input class="button" name="acceptBtn" id="${reservation.id}" type="submit" value="Accept"/>
						 	</form>
						 </td>
					</c:if>
					<c:if test="${reservation.status == 'CREATED' || reservation.status == 'ACCEPTED'}">
						<td align="center">
							<form method="post" action="/WEBProjRA502013/ReservationOverviewServlet">
						 		<input hidden="true" type="text" name="currentRow" value="${reservation.id}"/>
						 		<input class="button" name="rejectBtn" id="${reservation.id}" type="submit" value="Reject"/>
						 	</form>
						 </td>
					</c:if>
					<c:if test="${reservation.status == 'ACCEPTED' && reservation.noNightsLeft == 'true'}">
						<td align="center">
							<form method="post" action="/WEBProjRA502013/ReservationOverviewServlet">
						 		<input hidden="true" type="text" name="currentRow" value="${reservation.id}"/>
						 		<input class="button" name="finishBtn" id="${reservation.id}" type="submit" value="Finish"/>
						 	</form>
						 </td>
					</c:if>
				</c:if>
			</tr>
		</c:forEach>
	</table>
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
function changeAction(myRadio) {
    var currentId = myRadio.id;
    if(currentId=="findRBtn") {
    	document.getElementById("findFiterReservationForm").action = "\/WEBProjRA502013\/FindReservationServlet";
    	document.getElementById("findFilterSubmitBtn").value = "Find Reservation";
    } else if(currentId=="filterRBtn") {
    	document.getElementById("findFiterReservationForm").action = "\/WEBProjRA502013\/FilterReservationsServlet";
    	document.getElementById("findFilterSubmitBtn").value = "Filter Reservations";
	}
}
</script>

</body>
</html>