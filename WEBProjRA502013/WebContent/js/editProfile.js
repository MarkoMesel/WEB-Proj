var username = document.getElementById("username");
var password = document.getElementById("password");
var firstName = document.getElementById("firstName");
var lastName = document.getElementById("lastName");

var usernameError = document.getElementById("usernameError");
var passwordError = document.getElementById("passwordError");
var firstNameError = document.getElementById("firstNameError");
var lastNameError = document.getElementById("lastNameError");

password.addEventListener("keyup", checkPassword);
firstName.addEventListener("keyup", checkFirstName);
lastName.addEventListener("keyup", checkLastName);

function checkPassword() {
	if (password.value.length == 0) {
		passwordError.innerHTML = "Password must not be empty.";
	} else if (password.value.indexOf("\|") > -1) {
		passwordError.innerHTML = "Password must not contain the symbol \'\|\'.";
	} else if(password.value.length < 8) {
		passwordError.innerHTML = "Password must be at least 8 characters long.";
	} else {
		passwordError.innerHTML = "";
	}
}

function checkFirstName() {
	if (firstName.value.length == 0) {
		firstNameError.innerHTML = "First name must not be empty.";
	} else if (firstName.value.indexOf("\|") > -1) {
		firstNameError.innerHTML = "First name must not contain the symbol \'\|\'.";
	} else {
		firstNameError.innerHTML = "";
	}
}

function checkLastName() {
	if (lastName.value.length == 0) {
		lastNameError.innerHTML = "Last name must not be empty.";
	} else if (lastName.value.indexOf("\|") > -1) {
		lastNameError.innerHTML = "Last name must not contain the symbol \'\|\'.";
	}else {
		lastNameError.innerHTML = "";
	}
}