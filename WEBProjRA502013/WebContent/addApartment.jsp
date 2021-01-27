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
<title>Add New Apartment</title>
	<link href="css/mainstyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="mainPanel">
	<div class="site-header">
		<div class="site-header-content">
		<a href="home.jsp">
			<img src="images/logo.png" alt="webProjLogo">
		</a>
		</div>
	</div>
	<h1>Add New Apartment</h1>
	<form method="post" action="/WEBProjRA502013/AddApartmentServlet">
	<h2>General Info</h2>
	<table class="table-entry">
		<tr>
		<td><label class="label-entry">Apartment Type</label>
			
				<select name="aType" id="aType">
				  <option value="FULL">Full</option>
				  <option value="ROOM">Room</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<label class="label-entry">Number of Rooms</label>
				<input type="text" name="roomCount" id="roomCount"/>
				<label class="label-entry-error" id="roomCountError"></label>
			</td>
		</tr>
		<tr>
			<td>
				<label class="label-entry">Capacity (max num. of guests)</label>
				<input type="text" name="guestCount" id="guestCount"/>
				<label class="label-entry-error" id="guestCountError"></label>
			</td>
		</tr>
		<tr>
			<td>
				<label class="label-entry">Price (per night - in $)</label>
				<!-- 
					<span>
						<label style="display: inline-block; width: 12px;">$</label>
						<input type="number" name="price" id="price" style="width:85px; float:right">
					</span>
				 -->
				<input type="text" name="price" id="price">
				<label class="label-entry-error" id="priceError"></label>
			</td>
		</tr>
		<tr>
			<td>
				<label class="label-entry">Check-In Time</label>
				<input type="text" name="checkInTime" value="14:00" id="checkInTime"/>
				<label class="label-entry-error" id="checkInTimeError"></label>
			</td>
		</tr>
		<tr>
			<td>
				<label class="label-entry">Check-Out Time</label>
				<input type="text" name="checkOutTime" value="10:00" id="checkOutTime"/>
				<label class="label-entry-error" id="checkOutTimeError"></label>
			</td>
		</tr>
	</table>
	<h2>Location</h2>
	<div id="map" class="map"></div>
	<table class="table-entry">
		<tr>
			<td>
				<label class="label-entry">Latitude</label>
				<input type="text" step="any" name="latitude" id="latitude"/>
				<label class="label-entry-error" id="latitudeError"></label>
			</td>
		</tr>
		<tr>
			<td>
				<label class="label-entry">Longitude</label>
				<input type="text" step="any" name="longitude" id="longitude"/>
				<label class="label-entry-error" id="longitudeError"></label>
			</td>
		</tr>
		<tr>
			<td>
				<label class="label-entry">Street Name</label>
				<input type="text" name="streetName" id="streetName"/>
				<label class="label-entry-error" id="streetNameError"></label>
			</td>
		</tr>
		<tr>
			<td>
				<label class="label-entry">Street Number</label>
				<input type="text" name="streetNumber" id="streetNumber"/>
				<label class="label-entry-error" id="streetNumberError"></label>
			</td>
		</tr>
		<tr>
			<td>
				<label class="label-entry">Place</label>
				<input type="text" name="city" id="city"/>
				<label class="label-entry-error" id="cityError"></label>
			</td>
		</tr>
		<tr>
			<td>
				<label class="label-entry">Post Number</label>
				<input type="text" name="postNumber" id="postNumber"/>
				<label class="label-entry-error" id="postNumberError"></label>
			</td>
		</tr>
	</table>
	
	<h2>Amenities</h2>
	<table class="table-entry table-overview">
       <c:forEach var="element" items="${sessionScope.amenities}">
       	<tr>
           <td class="bottom-spacing">
	     		<input type="checkbox" id="${element.name}" name="${element.name}" value="${element.name}">
	     		<label for="${element.name}">${element.name}</label><br/>
	     		<label> Details: <b>${element.details}</b></label>
           </td>
        </tr>
       </c:forEach>
	</table>
	<input class="submit-button basic" type="submit" value="Add Apartment"/>
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