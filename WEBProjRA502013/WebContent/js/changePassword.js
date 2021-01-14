var oldPassword = document.getElementById("oldPassword");
var newPassword = document.getElementById("newPassword");

var oldPasswordError = document.getElementById("oldPasswordError");
var newPasswordError = document.getElementById("newPasswordError");

oldPassword.addEventListener("keyup", checkOldPassword);
newPassword.addEventListener("keyup", checkNewPassword);

function checkOldPassword() {
	if (oldPassword.value.length == 0) {
		oldPasswordError.innerHTML = "Old password must not be empty.";
	} else if (oldPassword.value.indexOf(",") > -1) {
		oldPasswordError.innerHTML = "Old password must not contain the symbol \',\'.";
	} else if(oldPassword.value.length < 8) {
		oldPasswordError.innerHTML = "New password must be at least 8 characters long.";
	} else {
		oldPasswordError.innerHTML = "";
	}
}

function checkNewPassword() {
	if (newPassword.value.length == 0) {
		newPasswordError.innerHTML = "New password must not be empty.";
	} else if (newPassword.value.indexOf(",") > -1) {
		newPasswordError.innerHTML = "New password must not contain the symbol \',\'.";
	} else if(newPassword.value.length < 8) {
		newPasswordError.innerHTML = "New password must be at least 8 characters long.";
	} else {
		newPasswordError.innerHTML = "";
	}
}