var commentMessage = document.getElementById("commentMessage");

var commentMessageError = document.getElementById("commentMessageError");

commentMessage.addEventListener("keyup", checkCommentMessage);

function checkCommentMessage() {
	if (commentMessage.value.length == 0) {
		commentMessageError.innerHTML = "Amenity name field must not be empty.";
	} else if (commentMessage.value.indexOf("\|") > -1) {
		commentMessageError.innerHTML = "Amenity name must not contain the symbol \'\|\'.";
	} else {
		commentMessageError.innerHTML = "";
	}
}