<!doctype html>
<html lang="en">
  <head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.3.1/css/ol.css" type="text/css">
    <style>
      .map {
        height: 400px;
        width: 100%;
      }
    </style>
    <script src="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.3.1/build/ol.js"></script>
    <title>OpenLayers example</title>
  </head>
  <body>
    <h2>My Map</h2>
    <div id="map" class="map"></div>
    <input id="address" class="form-control" placeholder="Clinic Address"/>
    <input id="lon" type="number" step="0.000001">
	<input id="lat" type="number" step="0.000001">
	<a class="button" id="reversegeocoding" href="#">Update With Coordinates</a>
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
		      document.getElementById('address').value = json.display_name;
		    })
		  }
		  map.addEventListener('click', function(e) {
		    var coordinate = ol.proj.toLonLat(e.coordinate).map(function(val) {
		      return val.toFixed(6);
		    });
		    var lon = document.getElementById('lon').value = coordinate[0];
		    var lat = document.getElementById('lat').value = coordinate[1];
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
  </body>
</html>
