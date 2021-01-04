var username = document.getElementById("username");
var password = document.getElementById("password");
var repeatPassword = document.getElementById("repeatPassword");
var firstName = document.getElementById("firstName");
var lastName = document.getElementById("lastName");

var usernameError = document.getElementById("usernameError");
var passwordError = document.getElementById("passwordError");
var repeatPasswordError = document.getElementById("repeatPasswordError");
var firstNameError = document.getElementById("firstNameError");
var lastNameError = document.getElementById("lastNameError");

username.addEventListener("keyup", checkUsername);
password.addEventListener("keyup", checkPasswords);
repeatPassword.addEventListener("keyup", checkPasswords);
firstName.addEventListener("keyup", checkFirstName);
lastName.addEventListener("keyup", checkLastName);

function checkUsername() {
	if (username.value.length == 0) {
		usernameError.innerHTML = "Username must not be empty.";
	} else if (username.value.indexOf(",") > -1) {
		usernameError.innerHTML = "Username must not contain the symbol \',\'.";
	}else {
		usernameError.innerHTML = "";
	}
}

function checkPasswords() {
	var passwordValid = "false";
	var repeatPasswordValid = "false";
	if (password.value.length == 0) {
		passwordError.innerHTML = "Password must not be empty.";
	} else if (password.value.indexOf(",") > -1) {
		passwordError.innerHTML = "Password must not contain the symbol \',\'.";
	} else if(password.value.length < 8) {
		passwordError.innerHTML = "Password must be at least 8 characters long.";
	} else {
		passwordValid = "true";
	}
	if (repeatPassword.value.length == 0) {
		repeatPasswordError.innerHTML = "Repeated password must not be empty.";
	} else if (repeatPassword.value.indexOf(",") > -1) {
		repeatPasswordError.innerHTML = "Repeated password must not contain the symbol \',\'.";
	} else if(repeatPassword.value.length < 8) {
		repeatPasswordError.innerHTML = "Repeated password must be at least 8 characters long.";
	} else {
		repeatPasswordValid = "true";
	}
	if(passwordValid == "true" && repeatPasswordValid == "true") {
		if(password.value != repeatPassword.value) {
			passwordError.innerHTML = "Passwords must match.";
			repeatPasswordError.innerHTML = "Passwords must match.";
		} else {
			passwordError.innerHTML = "";
			repeatPasswordError.innerHTML = "";	
		}
	} else if(passwordValid == "true"){
		passwordError.innerHTML = "Passwords must match.";
	} else if(repeatPasswordValid == "true") {
		repeatPasswordError.innerHTML = "Passwords must match.";
	}
}

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