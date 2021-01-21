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
<div class="mainPanelLarge">
	<h1>Apartment Overview</h1>
	<form method="post" id="findFiterApartmentForm" action="/WEBProjRA502013/FindApartmentServlet">
		<h2>Find/Filter</h2>
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
			<td rowspan="2" >By Date</td>
			<td align="left">
				Arrival date:
			</td>
			<td>
				<input type="text" name="datepickerArrive" id="datepickerArrive"/>
			</td>
		</tr>
		<tr>
			<td align="left">
				Leave date:
			</td>
			<td>
				<input type="text" name="datepickerLeave" id="datepickerLeave"/>
			</td>
		</tr>
		<tr>
			<td rowspan="2" >By Time</td>
			<td align="left">
				Arrival time:
			</td>
			<td>
				<input type="text" name="timeArrive" id="timeArrive"/>
			</td>
		</tr>
		<tr>
			<td align="left">
				Leave time:
			</td>
			<td>
				<input type="text" name="timeLeave" id="timeLeave"/>
			</td>
		</tr>
		<tr>
			<td rowspan="2">By Price</td>
			<td align="left">
				Min:
			</td>
			<td>
				<input type="text" name="priceMin" id="priceMin"/>
			</td>
		</tr>
		<tr>
			<td align="left">
				Max:
			</td>
			<td>
				<input type="text" name="priceMax" id="priceMax"/>
			</td>
		</tr>
		<tr>
			<td rowspan="2">By Room Count</td>
			<td align="left">
				Min:
			</td>
			<td >
				<input type="text" name="roomCountMin" id="roomCountMin"/>
			</td>
		</tr>
		<tr>
			<td align="left">
				Max:
			</td>
			<td >
				<input type="text" name="roomCountMax" id="roomCountMax"/>
			</td>
		</tr>
		<tr>
			<td>By Guest Count</td>
			<td align="left">Num. of Guests:</td>
			<td>
				<input type="text" name="guestCount" id="guestCount"/>
			</td>
		</tr>
		<tr>
			<td>By Location</td>
			<td align="left">City or Country:</td>
			<td>
				<input type="text" name="location" id="location"/>
			</td>
		</tr>
		<tr><td colspan="3" align="center">Search by Amenities</tr>
	       <c:forEach var="element" items="${sessionScope.amenities}">
	       	<tr>
	           <td align="left">
	           		<input type="checkbox" id="${element.name}" name="${element.name}" value="${element.name}">
	           		<label for="${element.name}">${element.name}</label>
	           </td>
	           <td colspan="2" align="left"><label><c:out value="${element.details}"></c:out></label></td>
	        </tr>
	       </c:forEach>
   		<tr><td colspan="3"><input type="submit" id="findFilterSubmitBtn" class="submit-button" value="Find Apartment"/></td></tr>
	</table>
	
	</form>
	
	<h2>Active Apartments</h2>
	<table class="table-overview">
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
				<td colspan="100%">
					<div>
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
					 		<input hidden="true" type="text" name="currentRow" value="${apartment.id}"/>
					 		<input class="button smaller" id="${apartment.id}" type="submit" value="View Comments"/>
					 	</form>
					 	<form style="display:inline-block;" method="get" action="/WEBProjRA502013/ViewPicturesServlet">
					 		<input hidden="true" type="text" name="currentRow" value="${apartment.id}"/>
					 		<input class="button smaller" id="${apartment.id}" type="submit" value="View Pictures"/>
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
					</div>
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
					<td><label>${apartment.type}</label></td>
					<td><label>${apartment.roomCount}</label></td>
					<td><label>${apartment.guestCount}</label></td>
					<td><label>${apartment.location}</label></td>
					<td><label>${apartment.host}</label></td>
					<td><label>${apartment.price}</label></td>
					<td><label>${apartment.bookingTime}</label></td>
					<td><label>${apartment.cancelTime}</label></td>
					<td><label>${apartment.status}</label></td>
				</tr>
				<tr>
					<td align="left" colspan="100%"><label>Amenities: ${apartment.apartmentAmenities}</label></td>
				</tr>
				<tr>
				<td align="left" colspan="100%">
				 	<form style="display:inline-block;" method="get" action="/WEBProjRA502013/ViewCommentsServlet">
				 		<input hidden="true" type="text" name="currentRow" value="${apartment.id}"/>
				 		<input class="button smaller" id="${apartment.id}" type="submit" value="View Comments"/>
				 	</form>
				 	<form style="display:inline-block;" method="get" action="/WEBProjRA502013/ViewPicturesServlet">
				 		<input hidden="true" type="text" name="currentRow" value="${apartment.id}"/>
				 		<input class="button smaller" id="${apartment.id}" type="submit" value="View Pictures"/>
				 	</form>
					<c:if test="${sessionScope.role == 'HOST' || sessionScope.role == 'ADMIN'}">
					 	<form style="display:inline-block;" method="get" action="/WEBProjRA502013/EditApartmentServlet">
					 		<input hidden="true" type="text" name="currentRow" value="${apartment.id}"/>
					 		<input class="button smaller" id="${apartment.id}" type="submit" value="Edit"/>
					 	</form>

					 	<input style="display:inline-block;" class="deleteButton smaller" id="${apartment.id}" 
					 		onclick="displayDeleteModal('${apartment.id}')" type="submit" value="Delete"/>
					</c:if>
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