<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reservation Overview</title>
	<link href="css/mainstyle.css" rel="stylesheet" type="text/css">
	<link href="css/deletemodal.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="site-header">
		<div class="site-header-content">
		<a href="home.jsp">
			<img src="images/logo.png" alt="webProjLogo">
		</a>
		</div>
	</div>
	<div class="div-wrapper maximum-width">
	<c:choose>
		<c:when test="${sessionScope.role == 'HOST' || sessionScope.role == 'ADMIN'}">
			<h1 align="center">Reservation Overview</h1>
		</c:when>
		<c:otherwise>
			<h1 align="left">Reservation Overview</h1>
		</c:otherwise>
	</c:choose>
	<div class="div-left-side">
		<label class="label-entry">Sort by</label>
		<table class="table-overview">
			<tr>
				<td class="sort-column">
					<form method="post" action="/WEBProjRA502013/SortReservationServlet">
						<input class="submit-button sort" name="sortBtn" type="submit" value="Apartment"/>
					</form>

					<form method="post" action="/WEBProjRA502013/SortReservationServlet">
						<input class="submit-button sort" name="sortBtn" type="submit" value="Reservation Date"/>
					</form>

					<form method="post" action="/WEBProjRA502013/SortReservationServlet">
						<input class="submit-button sort" name="sortBtn" type="submit" value="Night Count"/>
					</form>
				</td>
				<td class="sort-column">
					<form method="post" action="/WEBProjRA502013/SortReservationServlet">
						<input class="submit-button sort" name="sortBtn" type="submit" value="Price"/>
					</form>

					<form method="post" action="/WEBProjRA502013/SortReservationServlet">
						<input class="submit-button sort" name="sortBtn" type="submit" value="Message"/>
					</form>
				</td>
				<td class="sort-column">
					<form method="post" action="/WEBProjRA502013/SortReservationServlet">
						<input class="submit-button sort" name="sortBtn" type="submit" value="Guest"/>
					</form>

					<form method="post" action="/WEBProjRA502013/SortReservationServlet">
						<input class="submit-button sort" name="sortBtn" type="submit" value="Status"/>
					</form>
				</td>
			</tr>
			<c:forEach var="reservation" items="${sessionScope.reservations}">
				<tr>
					<td colspan="100%">
						<div class="div-wrapper">
						<label>Status: <b>${reservation.status}</b></label><br/>
						<label>Apartment: <b>${reservation.apartment}</b></label><br/>
						<label>Date: <b>${reservation.date}</b></label><br/>
						<label>Number of nights: <b>${reservation.nights}</b></label><br/>
						<label>Guest: <b>${reservation.guest}</b></label><br/>
						<label>Price: <b>${reservation.price}</b></label><br/>
						<label>Message: <b>${reservation.message}</b></label><br/>	
	<%-- 				<c:out value="${reservation.apartment}" />
					<c:out value="${reservation.date}" />
					<c:out value="${reservation.nights}" />
					<c:out value="${reservation.price}" />
					<c:out value="${reservation.message}" />
					<c:out value="${reservation.guest}" />
					<c:out value="${reservation.status}" /> --%>
					<c:if test="${sessionScope.role == 'GUEST' && (reservation.status == 'CREATED' || reservation.status == 'ACCEPTED')}">
						
					 	<form method="post" action="/WEBProjRA502013/ReservationOverviewServlet">
					 		<input type="hidden" name="currentRow" value="${reservation.id}"/>
					 		<input class="submit-button sub-option" name="cancelBtn" id="${reservation.id}" type="submit" value="Cancel"/>
					 	</form>
						
					</c:if>
					<c:if test="${sessionScope.role == 'HOST'}">
						<c:if test="${reservation.status == 'CREATED'}">
								<form method="post" action="/WEBProjRA502013/ReservationOverviewServlet">
							 		<input type="hidden" name="currentRow" value="${reservation.id}"/>
							 		<input class="submit-button sub-option" name="acceptBtn" id="${reservation.id}" type="submit" value="Accept"/>
							 	</form>
						</c:if>
						<c:if test="${reservation.status == 'CREATED' || reservation.status == 'ACCEPTED'}">
	
								<form method="post" action="/WEBProjRA502013/ReservationOverviewServlet">
							 		<input type="hidden" name="currentRow" value="${reservation.id}"/>
							 		<input class="submit-button sub-option" name="rejectBtn" id="${reservation.id}" type="submit" value="Reject"/>
							 	</form>
	
						</c:if>
						<c:if test="${reservation.status == 'ACCEPTED' && reservation.noNightsLeft == 'true'}">
	
								<form method="post" action="/WEBProjRA502013/ReservationOverviewServlet">
							 		<input type="hidden" name="currentRow" value="${reservation.id}"/>
							 		<input class="submit-button sub-option" name="finishBtn" id="${reservation.id}" type="submit" value="Finish"/>
							 	</form>
	
						</c:if>
					</c:if>
					</div>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
		<c:if test="${sessionScope.role == 'HOST' || sessionScope.role == 'ADMIN'}">
			<div class="div-find-filter">
		<form method="post" id="findFiterReservationForm" action="/WEBProjRA502013/FindReservationServlet">
			<h2>Find/Filter</h2>
				<table class="table-entry table-overview">
				<tr>
					<td><label class="label-entry">Find or Filter?</label>
						<input type="radio" class="sub-entry"id="findRBtn" name="rBtn" onclick="changeAction(this);" value="Find" checked>
						<label for="findRBtn">Find</label><br>
						<input type="radio" class="sub-entry" id="filterRBtn" name="rBtn" onclick="changeAction(this);" value="Filter">
						<label for="filterRBtn">Filter</label><br>
					</td>
				</tr>
				<tr>
					<td>
						<label class="label-entry">By status</label>
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
						<label class="label-entry">By Username</label>
						<input type="text" name="username" id="username"/>
					</td>					
				</tr>
				</table>
				<input type="submit" id="findFilterSubmitBtn" class="submit-button basic" value="Find Reservation"/>
			</form>
			</div>
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