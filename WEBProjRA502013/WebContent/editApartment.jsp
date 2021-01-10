<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.3.1/css/ol.css" type="text/css">
    <style>
      .map {
        height: 400px;
        width: 600px;
      }
    </style>
    <script src="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.3.1/build/ol.js"></script>
<meta charset="UTF-8">
<title>Edit Apartment</title>
	<link href="css/mainpage.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="mainPanel">
	<h1>Edit Apartment</h1>
	<form method="post" action="/WEBProjRA502013/EditApartmentServlet">
	<h2>General Info</h2>
	<table border="1" align="center" >
		<tr>
			<td>
				<select name="status" id="status">
				  <option value="ACTIVE" ${sessionScope.apartmentStatus == 'ACTIVE' ? 'selected' : ''}>Active</option>
				  <option value="INACTIVE" ${sessionScope.apartmentStatus == 'INACTIVE' ? 'selected' : ''}>Inactive</option>
				</select>
			</td>
		</tr>
		<tr>
		<td>Apartment Type:</td>
			<td>
				<select name="aType" id="aType">
				  <option value="FULL" ${sessionScope.apartmentType == 'FULL' ? 'selected' : ''}>Full</option>
				  <option value="ROOM" ${sessionScope.apartmentType == 'ROOM' ? 'selected' : ''}>Room</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>Number of Rooms:</td>
			<td><input type="text" name="roomCount" id="roomCount" value="${sessionScope.apartmentRoomCount}" ${sessionScope.apartmentType == 'ROOM' ? 'disabled' : ''}/></td>
			<td><label id="roomCountError"></label></td>
		</tr>
		<tr>
			<td>Capacity (max num. of guests):</td>
			<td><input type="text" name="guestCount" id="guestCount" value="${sessionScope.apartmentGuestCount}"/></td>
			<td><label id="guestCountError"></label></td>
		</tr>
		<tr>
			<td>Price (per night - in $):</td>
			<td>
			<!-- 
				<span>
					<label style="display: inline-block; width: 12px;">$</label>
					<input type="number" name="price" id="price" style="width:85px; float:right">
				</span>
			 -->
				<input type="text" name="price" id="price" value="${sessionScope.apartmentPrice}">
			</td>
			<td><label id="priceError"></label></td>
		</tr>
		<tr>
			<td>Check-In Time:</td>
			<td><input type="text" name="checkInTime" value="14:00" id="checkInTime" value="${sessionScope.apartmentCheckInTime}"/></td>
			<td><label id="checkInTimeError"></label></td>
		</tr>
		<tr>
			<td>Check-Out Time:</td>
			<td><input type="text" name="checkOutTime" value="10:00" id="checkOutTime" value="${sessionScope.apartmentCheckOutTime}"/></td>
			<td><label id="checkOutTimeError"></label></td>
		</tr>
	</table>
	<h2>Location</h2>
	<div id="map" class="map"></div>
	<table border="1" align="center" >
		<tr>
			<td>Latitude:</td>
			<td><input type="text" step="any" name="latitude" id="latitude" value="${sessionScope.locationLatitude}"/></td>
			<td><label id="latitudeError"></label></td>
		</tr>
		<tr>
			<td>Longitude:</td>
			<td><input type="text" step="any" name="longitude" id="longitude" value="${sessionScope.locationLongitude}"/></td>
			<td><label id="longitudeError"></label></td>
		</tr>
		<tr>
			<td>Street Name:</td>
			<td><input type="text" name="streetName" id="streetName" value="${sessionScope.locationStreetName}"/></td>
			<td><label id="streetNameError"></label></td>
		</tr>
		<tr>
			<td>Street Number:</td>
			<td><input type="text" name="streetNumber" id="streetNumber" value="${sessionScope.locationStreetNumber}"/></td>
			<td><label id="streetNumberError"></label></td>
		</tr>
		<tr>
			<td>City:</td>
			<td><input type="text" name="city" id="city" value="${sessionScope.locationCity}"/></td>
			<td><label id="cityError"></label></td>
		</tr>
		<tr>
			<td>Post Number:</td>
			<td><input type="text" name="postNumber" id="postNumber" value="${sessionScope.locationPostNumber}"/></td>
			<td><label id="postNumberError"></label></td>
		</tr>
	</table>
	<h2>Amenities</h2>
	<table border="1" align="center" >
       <c:forEach var="element" items="${sessionScope.amenities}">
       	<tr>
           <td align="left">
           		<input type="checkbox" id="${element.name}" name="${element.name}" value="${element.name}" ${element.checked == 'true' ? 'checked' : ''}>
           		<label for="${element.name}">${element.name}</label>
           </td>
           <td align="left"><label><c:out value="${element.details}"></c:out></label></td>
        </tr>
       </c:forEach>
	</table>
	<table border="1" align="center" >
		<tr><td colspan="3"><input class="submitButton" type="submit" value="Edit Apartment"/></td></tr>
	</table>
	</form>
</div>
<script type="text/javascript">
		var map = new ol.Map({
		    layers: [
		      new ol.layer.Tile({
		        source: new ol.source.OSM()
		      })
		    ],
		    target: 'map',
		    view: new ol.View({
		      center: [0, 0],
		      zoom: 2
		    })
		  });
		  function simpleReverseGeocoding(lon, lat) {
		    fetch('http://nominatim.openstreetmap.org/reverse?format=json&lon=' + lon + '&lat=' + lat).then(function(response) {
		      return response.json();
		    }).then(function(json) {
		    	var place = "";
		    	let place_switch = 0;
		    	do{
			    	switch(place_switch) {
			    	  case 0:
			    		place = json.address.city;
			    	    break;
			    	  case 1:
			    		place = json.address.suburb;
			    	  	break;
			    	  case 2:
			    		place = json.address.town;
			    	  	break;
			    	  case 3:
			    		place = json.address.state;
			    	  	break;
			    	  case 4:
				    	place = json.address.country;
			    	  default:
			    		place = "undefined";
			    	}
			    	place_switch++;
		    	} while(typeof place === 'undefined' && place_switch < 6);

		    	document.getElementById('streetName').value = json.address.road;
		    	document.getElementById('streetNumber').value = json.address.house_number;
		    	document.getElementById('city').value = place;
		    	document.getElementById('postNumber').value = json.address.postcode;
		    	
			    validateLatitude();
			    validateLongitude();
			    validateStreetName();
			    validateStreetNumber();
			    validateCity();
			    validatePostNumber();
		    })
		  }
		  map.addEventListener('click', function(e) {
		    var coordinate = ol.proj.toLonLat(e.coordinate).map(function(val) {
		      return val.toFixed(6);
		    });
		    var lon = document.getElementById('longitude').value = coordinate[0];
		    var lat = document.getElementById('latitude').value = coordinate[1];
		    simpleReverseGeocoding(lon, lat);
		  });
		  /*
		  document.getElementById('reversegeocoding').addEventListener('click', function(e) {
		    if (document.getElementById('lon').value && document.getElementById('lat').value) {
		      simpleReverseGeocoding(document.getElementById('lon').value, document.getElementById('lat').value);
		    }
		  });
		  */
</script>
<script type="text/javascript" src="js/addApartment.js"></script>
</body>
</html>