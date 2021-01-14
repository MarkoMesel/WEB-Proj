var firstName = document.getElementById("firstName");
var lastName = document.getElementById("lastName");

var firstNameError = document.getElementById("firstNameError");
var lastNameError = document.getElementById("lastNameError");

firstName.addEventListener("keyup", checkFirstName);
lastName.addEventListener("keyup", checkLastName);

function checkFirstName() {
	if (firstName.value.length == 0) {
		firstNameError.innerHTML = "First name must not be empty.";
	} else if (firstName.value.indexOf(",") > -1) {
		firstNameError.innerHTML = "First name must not contain the symbol \',\'.";
	} else {
		firstNameError.innerHTML = "";
	}
}

function checkLastName() {
	if (lastName.value.length == 0) {
		lastNameError.innerHTML = "Last name must not be empty.";
	} else if (lastName.value.indexOf(",") > -1) {
		lastNameError.innerHTML = "Last name must not contain the symbol \',\'.";
	}else {
		lastNameError.innerHTML = "";
	}
}