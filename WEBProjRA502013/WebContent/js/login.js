var username = document.getElementById("username");
var password = document.getElementById("password");

var usernameError = document.getElementById("usernameError");
var passwordError = document.getElementById("passwordError");

username.addEventListener("keyup", checkUsername);
password.addEventListener("keyup", checkPassword);

function checkUsername() {
	if (username.value.length == 0) {
		usernameError.innerHTML = "Username must not be empty.";
	} else if (username.value.indexOf("\|") > -1) {
		usernameError.innerHTML = "Username must not contain the symbol \'\|\'.";
	}else {
		usernameError.innerHTML = "";
	}
}

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