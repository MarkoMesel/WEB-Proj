  $( function() {
		var enableDaysString = document.getElementById("apartmentDates").value.replace("[","").replace("]","");
	  	var enableDays = enableDaysString.split(", ");
	    function enableAllTheseDays(date) {
	        var sdate = $.datepicker.formatDate( 'dd-mm-yy', date)
	        if($.inArray(sdate, enableDays) != -1) {
	            return [false];
	        }
	        return [true];
	    }
	  
	  $( "#datepicker" ).datepicker({dateFormat: 'dd-mm-yy', beforeShowDay: enableAllTheseDays});;
  } );

//InputFields
var datepicker = document.getElementById("datepicker");
var numberOfNights = document.getElementById("numberOfNights");
var reservationMessage = document.getElementById("reservationMessage");
//ErrorLabels
var datepickerError = document.getElementById("datepickerError");
var numberOfNightsError = document.getElementById("numberOfNightsError");
var reservationMessageError = document.getElementById("reservationMessageError");
//Regex
var numNoLeadingZerosRegex = /^([1-9][0-9]*)$/;
var dateRegex = /^(0[1-9],[12][0-9],3[01])[- /.](0[1-9],1[012])[- /.](19,20)\d\d$/;
//EventListeners
datepicker.addEventListener("keyup", validateDatepicker);
numberOfNights.addEventListener("keyup", validateNumberOfNights);
reservationMessage.addEventListener("keyup", validateReservationMessage);
//Functions
function validateDatepicker() {
	if (datepicker.value.length == 0) {
		datepickerError.innerHTML = "Datepicker field must not be empty.";
	} else if (datepicker.value.indexOf(",") > -1) {
		datepickerError.innerHTML = "Datepicker field must not contain the symbol \',\'.";
	} else if (!(dateRegex.test(datepicker.value))) {
		datepickerError.innerHTML = "Date must be written in the \"dd-mm-yyyy\" date format.";
	} else {
		datepickerError.innerHTML = "";
	}
}
function validateNumberOfNights() {
	if (numberOfNights.value.length == 0) {
		numberOfNightsError.innerHTML = "Number of nights field must not be empty.";
	} else if (numberOfNights.value.indexOf(",") > -1) {
		numberOfNightsError.innerHTML = "Number of nights field must not contain the symbol \',\'.";
	} else if (!(numNoLeadingZerosRegex.test(numberOfNights.value))) {
		numberOfNightsError.innerHTML = "Number of nights must be a natural number higher than 0 (ex. 1, 2, 5, 6, etc.).";
	} else {
		numberOfNightsError.innerHTML = "";
	}
}
function validateReservationMessage() {
	if (reservationMessage.value.length == 0) {
		reservationMessageError.innerHTML = "Reservation message field must not be empty.";
	} else {
		reservationMessageError.innerHTML = "";
	}
}
