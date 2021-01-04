var amenityName = document.getElementById("amenityName");
var amenityDetails = document.getElementById("amenityDetails");

var amenityNameError = document.getElementById("amenityNameError");
var amenityDetailsError = document.getElementById("amenityDetailsError");

amenityName.addEventListener("keyup", checkAmenityName);
amenityDetails.addEventListener("keyup", checkAmenityDetails);

function checkAmenityName() {
	if (amenityName.value.length == 0) {
		amenityNameError.innerHTML = "Amenity name field must not be empty.";
	} else if (amenityName.value.indexOf(",") > -1) {
		amenityNameError.innerHTML = "Amenity name must not contain the symbol \',\'.";
	} else {
		amenityNameError.innerHTML = "";
	}
}

function checkAmenityDetails() {
	if (amenityDetails.value.length == 0) {
		amenityDetailsError.innerHTML = "Amenity details field must not be empty.";
	} else if (amenityDetails.value.indexOf(",") > -1) {
		amenityDetailsError.innerHTML = "Amenity details field must not contain the symbol \',\'.";
	}else {
		amenityDetailsError.innerHTML = "";
	}
}