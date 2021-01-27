<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Apartment Overview</title>
	<link href="css/mainstyle.css" rel="stylesheet" type="text/css">
	<link href="css/deletemodal.css" rel="stylesheet" type="text/css">
	  <meta name="viewport" content="width=device-width, initial-scale=1" charset="UTF-8">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <!--  <link rel="stylesheet" href="/resources/demos/style.css"> -->
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
	<h1 align="center">Apartment Overview</h1>
	<div class="div-left-side">
	<h2>Active Apartments</h2>
	<label class="label-entry">Sort by</label>
	<table class="table-overview">
		<tr>
			<td class="sort-column">
				<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
					<input class="submit-button sort" name="sortBtn" type="submit" value="Type"/>
				</form>

				<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
					<input class="submit-button sort" name="sortBtn" type="submit" value="Room Count"/>
				</form>
				<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
					<input class="submit-button sort" name="sortBtn" type="submit" value="Guest Count"/>
				</form>
			</td>
			<td class="sort-column">
				<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
					<input class="submit-button sort" name="sortBtn" type="submit" value="Location"/>
				</form>
				<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
					<input class="submit-button sort" name="sortBtn" type="submit" value="Host"/>
				</form>
				<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
					<input class="submit-button sort" name="sortBtn" type="submit" value="Price"/>
				</form>
			</td>
			<td class="sort-column">
				<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
					<input class="submit-button sort" name="sortBtn" type="submit" value="Booking Time"/>
				</form>
				<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
					<input class="submit-button sort" name="sortBtn" type="submit" value="Cancel Time"/>
				</form>
				<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
					<input class="submit-button sort" name="sortBtn" type="submit" value="Status"/>
				</form>
			</td>
		</tr>
		<c:forEach var="apartment" items="${sessionScope.apartments}">
			<tr>
				<td colspan="100%">
					<div class="div-wrapper">
						<div class="div-picture">
							<img class="apartment-img" src="images/apartmentImages/${apartment.id}_0">
						</div>
						<div class="div-details">
							<label>Status: <b>${apartment.status}</b></label><br/>	
							<label>Host: <b>${apartment.host}</b></label><br/>
							<label>Location: <b>${apartment.location}</b></label><br/>
							<label>Type: <b>${apartment.type} (${apartment.roomCount} room count)</b></label><br/>
							<label>Guest capacity: <b>${apartment.guestCount}</b></label><br/>
							<label>Booking time: <b>${apartment.bookingTime}</b></label><br/>
							<label>Cancel time: <b>${apartment.cancelTime}</b></label><br/>
							<label>Price: <b>${apartment.price}</b></label><br/>
							<label>Amenities: <b>${apartment.apartmentAmenities}</b></label><br/>
							
			<!-- 				</td> -->
			<!-- 				<td> -->
							<%-- //<label>${apartment.roomCount}</label>
			
			<!-- 				</td> -->
			<!-- 				<td> -->
							//<label>${apartment.guestCount}</label>
			<!-- 				</td> -->
			<!-- 				<td> -->
							//<label>${apartment.location}</label>
			<!-- 				</td> -->
			<!-- 				<td> -->
							//<label>${apartment.host}</label>
			<!-- 				</td> -->
			<!-- 				<td> -->
							//<label>${apartment.price}</label>
			<!-- 				</td> -->
			<!-- 				<td> -->
							//<label>${apartment.bookingTime}</label>
			<!-- 				</td> -->
			<!-- 				<td> -->
							//<label>${apartment.cancelTime}</label>
			<!-- 				</td> -->
			<!-- 				<td> -->
							//<label>${apartment.status}</label>
		<!-- 				</td> -->
		<!-- 			</tr> -->
		<!-- 			<tr> -->
		<!-- 				<td align="left" colspan="100%"> -->
							//<label>Amenities: ${apartment.apartmentAmenities}</label>
		<!-- 				</td> -->
		<!-- 			</tr> -->
		<!-- 			<tr> -->
		<!-- 				<td align="left" colspan="100%"> --> --%>
						 	<form style="display:inline-block;" method="get" action="/WEBProjRA502013/ViewCommentsServlet">
						 		<input type="hidden" name="currentRow" value="${apartment.id}"/>
						 		<input class="button smaller" id="${apartment.id}" type="submit" value="View Comments"/>
						 	</form>
						 	<form style="display:inline-block;" method="get" action="/WEBProjRA502013/ViewPicturesServlet">
						 		<input type="hidden" name="currentRow" value="${apartment.id}"/>
						 		<input class="button smaller" id="${apartment.id}" type="submit" value="View Pictures"/>
						 	</form>
							<c:if test="${sessionScope.role == 'HOST' || sessionScope.role == 'ADMIN'}">
							 	<form style="display:inline-block;" method="get" action="/WEBProjRA502013/EditApartmentServlet">
							 		<input type="hidden" name="currentRow" value="${apartment.id}"/>
							 		<input class="button smaller" id="${apartment.id}" type="submit" value="Edit"/>
							 	</form>
		
							 	<input style="display:inline-block;" class="deleteButton smaller" id="${apartment.id}" 
							 		onclick="displayDeleteModal('${apartment.id}')" type="submit" value="Delete"/>
		
							</c:if>
							<c:if test="${sessionScope.role == 'GUEST'}">
							 	<form style="display:inline-block;" method="get" action="/WEBProjRA502013/ReserveApartmentServlet">
							 		<input type="hidden" name="currentRow" value="${apartment.id}"/>
							 		<input class="button reserve smaller" id="${apartment.id}" type="submit" value="Reserve"/>
							 	</form>
							</c:if>
						</div>
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${sessionScope.role == 'HOST'}">
		<h2>Inactive Apartments</h2>
		<label class="label-entry">Sort by</label>
		<table class="table-overview">
			<tr>
				<td class="sort-column">
					<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
						<input class="submit-button sort" name="sortInactiveBtn" type="submit" value="Type"/>
					</form>

					<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
						<input class="submit-button sort" name="sortInactiveBtn" type="submit" value="Room Count"/>
					</form>

					<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
						<input class="submit-button sort" name="sortInactiveBtn" type="submit" value="Guest Count"/>
					</form>
				</td>
				<td class="sort-column">
					<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
						<input class="submit-button sort" name="sortInactiveBtn" type="submit" value="Location"/>
					</form>
				
					<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
						<input class="submit-button sort" name="sortInactiveBtn" type="submit" value="Host"/>
					</form>
				
					<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
						<input class="submit-button sort" name="sortInactiveBtn" type="submit" value="Price"/>
					</form>
				</td>
				<td class="sort-column">
					<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
						<input class="submit-button sort" name="sortInactiveBtn" type="submit" value="Booking Time"/>
					</form>
				
					<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
						<input class="submit-button sort" name="sortInactiveBtn" type="submit" value="Cancel Time"/>
					</form>
				
					<form method="post" action="/WEBProjRA502013/SortApartmentServlet">
						<input class="submit-button sort" name="sortInactiveBtn" type="submit" value="Status"/>
					</form>
				</td>
			</tr>
			<c:forEach var="apartment" items="${sessionScope.inactiveApartments}">
				<tr>
					<td colspan="100%">
						<div class="div-wrapper">
							<div class="div-picture">
								<img class="apartment-img" src="images/apartmentImages/${apartment.id}_0">
							</div>
							<div class="div-details">
							<label>Status: <b>${apartment.status}</b></label><br/>	
							<label>Host: <b>${apartment.host}</b></label><br/>
							<label>Location: <b>${apartment.location}</b></label><br/>
							<label>Type: <b>${apartment.type} (${apartment.roomCount} room count)</b></label><br/>
							<label>Guest capacity: <b>${apartment.guestCount}</b></label><br/>
							<label>Booking time: <b>${apartment.bookingTime}</b></label><br/>
							<label>Cancel time: <b>${apartment.cancelTime}</b></label><br/>
							<label>Price: <b>${apartment.price}</b></label><br/>
							<label>Amenities: <b>${apartment.apartmentAmenities}</b></label><br/>
				 	<form style="display:inline-block;" method="get" action="/WEBProjRA502013/ViewCommentsServlet">
				 		<input type="hidden" name="currentRow" value="${apartment.id}"/>
				 		<input class="button smaller" id="${apartment.id}" type="submit" value="View Comments"/>
				 	</form>
				 	<form style="display:inline-block;" method="get" action="/WEBProjRA502013/ViewPicturesServlet">
				 		<input type="hidden" name="currentRow" value="${apartment.id}"/>
				 		<input class="button smaller" id="${apartment.id}" type="submit" value="View Pictures"/>
				 	</form>
					<c:if test="${sessionScope.role == 'HOST' || sessionScope.role == 'ADMIN'}">
					 	<form style="display:inline-block;" method="get" action="/WEBProjRA502013/EditApartmentServlet">
					 		<input type="hidden" name="currentRow" value="${apartment.id}"/>
					 		<input class="button smaller" id="${apartment.id}" type="submit" value="Edit"/>
					 	</form>

					 	<input style="display:inline-block;" class="deleteButton smaller" id="${apartment.id}" 
					 		onclick="displayDeleteModal('${apartment.id}')" type="submit" value="Delete"/>
					</c:if>
					</div>
					</div>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	</div>
	<div class="div-find-filter">
	<form method="post" id="findFiterApartmentForm" action="/WEBProjRA502013/FindApartmentServlet">
		<h2>Find/Filter</h2>
		<table class="table-entry table-overview">
		<tr>
			<td><label class="label-entry">Find or Filter?</label>
				<input type="radio" class="sub-entry" id="findRBtn" name="rBtn" onclick="changeAction(this);" value="Find" checked>
				<label for="findRBtn">Find</label><br/>
				<input type="radio" class="sub-entry" id="filterRBtn" name="rBtn" onclick="changeAction(this);" value="Filter">
				<label for="filterRBtn">Filter</label>
			</td>
		</tr>
		<tr>
			<td>
				<label class="label-entry bottom-spacing">By Date</label>
				<label class="label-entry sub-entry smaller-font">Arrival date</label>
				<input class="sub-entry" type="text" name="datepickerArrive" id="datepickerArrive"/>
				<label class="label-entry sub-entry smaller-font">Leave date</label>
				<input class="sub-entry" type="text" name="datepickerLeave" id="datepickerLeave"/>
			</td>
		</tr>
		<tr>
			<td>
				<label class="label-entry bottom-spacing">By Time</label>
				<label class="label-entry sub-entry smaller-font">Arrival time</label>
				<input class="sub-entry" type="text" name="timeArrive" id="timeArrive"/>
				<label class="label-entry sub-entry smaller-font">Leave time</label>
				<input class="sub-entry" type="text" name="timeLeave" id="timeLeave"/>
			</td>
		</tr>
		<tr>
			<td>
				<label class="label-entry bottom-spacing">By Price</label>
				<label class="label-entry sub-entry smaller-font">Min</label>
				<input class="sub-entry" type="text" name="priceMin" id="priceMin"/>
				<label class="label-entry sub-entry smaller-font">Max</label>
				<input class="sub-entry" type="text" name="priceMax" id="priceMax"/>
			</td>
		</tr>
		<tr>
			<td>
				<label class="label-entry bottom-spacing">By Room Count</label>
				<label class="label-entry sub-entry smaller-font">Min</label>
				<input class="sub-entry" type="text" name="roomCountMin" id="roomCountMin"/>
				<label class="label-entry sub-entry smaller-font">Max</label>
				<input class="sub-entry" type="text" name="roomCountMax" id="roomCountMax"/>
			</td>
		</tr>
		<tr>
			<td>
				<label class="label-entry bottom-spacing">By Guest Count</label>
				<label class="label-entry sub-entry smaller-font">Num. of Guests</label>
				<input class="sub-entry" type="text" name="guestCount" id="guestCount"/>
			</td>
		</tr>
		<tr>
			<td>
				<label class="label-entry bottom-spacing">By Location</label>
				<label class="label-entry sub-entry smaller-font">City or Country</label>
				<input class="sub-entry" type="text" name="location" id="location"/>
			</td>
		</tr>
		<tr>
			<td class="bottom-spacing"><label class="label-entry">Search by Amenities</label>
		</tr>
	       <c:forEach var="element" items="${sessionScope.amenities}">
	       	<tr>
	           <td class="bottom-spacing">
	           		<input class="sub-entry" type="checkbox" id="${element.name}" name="${element.name}" value="${element.name}">
	           		<label for="${element.name}">${element.name}</label><br/>
	           		<label class="sub-entry">Details: <b>${element.details}</b></label>
	           	</td>
	        </tr>
	       </c:forEach>
	</table>
	<input type="submit" id="findFilterSubmitBtn" class="submit-button" value="Find Apartment"/>
	
	</form>
	</div>
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
       	<input type="hidden" id="currentSelectedApartment" name="currentSelectedApartment" value="a"/>
       	<input class="deleteButton small" id="" type="submit" value="Yes"/>
        <button onclick="closeDeleteModal()" type="button" class="button small">No</button>
      </div>
    </div>
  </form>
</div>

<script>
$( function() {
	$( "#datepickerArrive" ).datepicker({dateFormat: 'dd-mm-yy'});
	$( "#datepickerLeave" ).datepicker({dateFormat: 'dd-mm-yy'});
  } );
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
    	document.getElementById("findFiterApartmentForm").action = "\/WEBProjRA502013\/FindApartmentServlet";
    	document.getElementById("findFilterSubmitBtn").value = "Find Apartment";
    } else if(currentId=="filterRBtn") {
    	document.getElementById("findFiterApartmentForm").action = "\/WEBProjRA502013\/FilterApartmentsServlet";
    	document.getElementById("findFilterSubmitBtn").value = "Filter Apartments";
	}
}
</script>

</body>
</html>