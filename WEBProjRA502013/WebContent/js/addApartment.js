//ComboBox
var apartmentType = document.getElementById("aType");
//InputFields
var roomCount = document.getElementById("roomCount");
var guestCount = document.getElementById("guestCount");
var price = document.getElementById("price");
var checkInTime = document.getElementById("checkInTime");
var checkOutTime = document.getElementById("checkOutTime");
var latitude = document.getElementById("latitude");
var longitude = document.getElementById("longitude");
var streetName = document.getElementById("streetName");
var streetNumber = document.getElementById("streetNumber");
var city = document.getElementById("city");
var postNumber = document.getElementById("postNumber");
//ErrorLabels
var roomCountError = document.getElementById("roomCountError");
var guestCountError = document.getElementById("guestCountError");
var priceError = document.getElementById("priceError");
var checkInTimeError = document.getElementById("checkInTimeError");
var checkOutTimeError = document.getElementById("checkOutTimeError");
var latitudeError = document.getElementById("latitudeError");
var longitudeError = document.getElementById("longitudeError");
var streetNameError = document.getElementById("streetNameError");
var streetNumberError = document.getElementById("streetNumberError");
var cityError = document.getElementById("cityError");
var postNumberError = document.getElementById("postNumberError");
//Regex
var numNoLeadingZerosRegex = /^([1-9][0-9]*)$/;
var dollarRegex = /^\$?\-?([1-9]{1}[0-9]{0,2}(\,\d{3})*(\.\d{0,2})?|[1-9]{1}\d{0,}(\.\d{0,2})?|0(\.\d{0,2})?|(\.\d{1,2}))$|^\-?\$?([1-9]{1}\d{0,2}(\,\d{3})*(\.\d{0,2})?|[1-9]{1}\d{0,}(\.\d{0,2})?|0(\.\d{0,2})?|(\.\d{1,2}))$|^\(\$?([1-9]{1}\d{0,2}(\,\d{3})*(\.\d{0,2})?|[1-9]{1}\d{0,}(\.\d{0,2})?|0(\.\d{0,2})?|(\.\d{1,2}))\)$/;
var timeRegex = /^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$/;
var latitudeRegex = /^[-+]?([1-8]?\d(\.\d+)?|90(\.0+)?)$/;
var longitudeRegex = /^[-+]?(180(\.0+)?|((1[0-7]\d)|([1-9]?\d))(\.\d+)?)$/;
var streetNumberRegex = /^([1-9][0-9]*[A-Za-z]?)$/;
var numberRegex = /^([0-9]*)$/;
//EventListeners
apartmentType.addEventListener("change", toggleRoomCountDisabled);
roomCount.addEventListener("keyup", validateRoomCount);
guestCount.addEventListener("keyup", validateGuestCount);
price.addEventListener("keyup", validatePrice);
checkInTime.addEventListener("keyup", validateCheckInTime);
checkOutTime.addEventListener("keyup", validateCheckOutTime);
latitude.addEventListener("keyup", validateLatitude);
longitude.addEventListener("keyup", validateLongitude);
streetName.addEventListener("keyup",validateStreetName);
streetNumber.addEventListener("keyup",validateStreetNumber);
city.addEventListener("keyup",validateCity);
postNumber.addEventListener("keyup",validatePostNumber);
//Functions
function toggleRoomCountDisabled() {
	if(apartmentType.value == "FULL") {
		roomCount.disabled = false;
		roomCountError.innerHTML = "";
	} else {
		roomCount.value = "1";
		roomCount.disabled = true;
		roomCountError.innerHTML = "";
	}
}
function validateRoomCount() {
	if (roomCount.value.length == 0) {
		roomCountError.innerHTML = "Room count field must not be empty.";
	} else if (roomCount.value.indexOf("\|") > -1) {
		roomCountError.innerHTML = "Room count field must not contain the symbol \'\|\'.";
	} else if (!(numNoLeadingZerosRegex.test(roomCount.value))) {
		roomCountError.innerHTML = "Room count field must be a natural number higher than 0 (ex. 1, 2, 5, 6, etc.).";
	} else {
		roomCountError.innerHTML = "";
	}
}
function validateGuestCount() {
	if (guestCount.value.length == 0) {
		guestCountError.innerHTML = "Guest count field must not be empty.";
	} else if (guestCount.value.indexOf("\|") > -1) {
		guestCountError.innerHTML = "Guest count field must not contain the symbol \'\|\'.";
	} else if (!(numNoLeadingZerosRegex.test(guestCount.value))) {
		guestCountError.innerHTML = "Guest count field must be a natural number higher than 0 (ex. 1, 2, 5, 6, etc.).";
	} else {
		guestCountError.innerHTML = "";
	}
}
function validatePrice() {
	if (price.value.length == 0) {
		priceError.innerHTML = "Price field must not be empty.";
	} else if (price.value.indexOf("\|") > -1) {
		priceError.innerHTML = "Price field must not contain the symbol \'\|\'.";
	} else if (!(dollarRegex.test(price.value))) {
		priceError.innerHTML = "Price must be written in a valid price format.";
	} else {
		priceError.innerHTML = "";
	}
}
function validateCheckInTime() {
	if (checkInTime.value.length == 0) {
		checkInTimeError.innerHTML = "Check-In time field must not be empty.";
	} else if (checkInTime.value.indexOf("\|") > -1) {
		checkInTimeError.innerHTML = "Check-In time field must not contain the symbol \'\|\'.";
	} else if (!(timeRegex.test(checkInTime.value))) {
		checkInTimeError.innerHTML = "Time must be written in a 24-hour time format (\'h:mm\' or \'hh:mm\').";
	} else {
		checkInTimeError.innerHTML = "";
	}
}
function validateCheckOutTime() {
	if (checkOutTime.value.length == 0) {
		checkOutTimeError.innerHTML = "Check-In time field must not be empty.";
	} else if (checkOutTime.value.indexOf("\|") > -1) {
		checkOutTimeError.innerHTML = "Check-In time field must not contain the symbol \'\|\'.";
	} else if (!(timeRegex.test(checkOutTime.value))) {
		checkOutTimeError.innerHTML = "Time must be written in a 24-hour time format (\'h:mm\' or \'hh:mm\').";
	} else {
		checkOutTimeError.innerHTML = "";
	}
}
function validateLatitude() {
	if (latitude.value.length == 0) {
		latitudeError.innerHTML = "Latitude field must not be empty.";
	} else if (latitude.value.indexOf("\|") > -1) {
		latitudeError.innerHTML = "Latitude field must not contain the symbol \'\|\'.";
	} else if (!(latitudeRegex.test(latitude.value))) {
		latitudeError.innerHTML = "Latitude must be written in valid latitude format (-90 to 90, with or without decimal spaces).";
	} else {
		latitudeError.innerHTML = "";
	}
}
function validateLongitude() {
	if (longitude.value.length == 0) {
		longitudeError.innerHTML = "Longitude field must not be empty.";
	} else if (longitude.value.indexOf("\|") > -1) {
		longitudeError.innerHTML = "Longitude field must not contain the symbol \'\|\'.";
	} else if (!(longitudeRegex.test(longitude.value))) {
		longitudeError.innerHTML = "Longitude must be written in valid longitude format (-180 to 180, with or without decimal spaces).";
	} else {
		longitudeError.innerHTML = "";
	}
}
function validateStreetName() {
	if (streetName.value.length == 0) {
		streetNameError.innerHTML = "Street name field must not be empty.";
	} else if (streetName.value.indexOf("\|") > -1) {
		streetNameError.innerHTML = "Street name field must not contain the symbol \'\|\'.";
	} else {
		streetNameError.innerHTML = "";
	}
}
function validateStreetNumber() {
	if (streetNumber.value.length == 0) {
		streetNumberError.innerHTML = "Street number field must not be empty.";
	} else if (streetNumber.value.indexOf("\|") > -1) {
		streetNumberError.innerHTML = "Street number field must not contain the symbol \'\|\'.";
	} else if (!(streetNumberRegex.test(streetNumber.value))) {
		streetNumberError.innerHTML = "Street number must be written in valid street number format (ex. 102, 50a, 12B, etc.).";
	} else {
		streetNumberError.innerHTML = "";
	}
}
function validateCity() {
	if (city.value.length == 0) {
		cityError.innerHTML = "City field must not be empty.";
	} else if (city.value.indexOf("\|") > -1) {
		cityError.innerHTML = "City field must not contain the symbol \'\|\'.";
	} else {
		cityError.innerHTML = "";
	}
}
function validatePostNumber() {
		if (postNumber.value.length == 0) {
		postNumberError.innerHTML = "Post number field must not be empty.";
	} else if (postNumber.value.indexOf("\|") > -1) {
		postNumberError.innerHTML = "Post number field must not contain the symbol \'\|\'.";
	} else if (!(numberRegex.test(postNumber.value))) {
		postNumberError.innerHTML = "Post number can only contain numbers.";
	} else {
		postNumberError.innerHTML = "";
	}
}