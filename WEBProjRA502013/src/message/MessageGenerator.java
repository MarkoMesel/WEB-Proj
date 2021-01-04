package message;

public class MessageGenerator {
	public static String generateSuccessfulRegisterMessage() {
		return "You have successfully registered your account!";
	}
	public static String generateSuccessfulUpdateMessage(String s) {
		return "You have successfully updated " + s + "!";
	}
	public static String generateSuccessfulCreateMessage(String s) {
		return "You have successfully created a new " + s + "!";
	}
	public static String generateNotEmptyMessage(String s) {
		return s + " must not be empty.";
	}
	public static String generateNotSymbolMessage(String s) {
		return s + " must not contain the symbol \',\'.";
	}
	public static String generateMustBeUniqueMessage(String s) {
		return s + " must be unique.";
	}
	public static String generateNotLongEnoughMessage(String s, int length) {
		return s + " must be at least " + length + " characters long.";
	}
	public static String generatePasswordsMustMatchMessage() {
		return "Passwords must match.";
	}
	public static String generateIsNotRegisteredMessage(String s) {
		return s + " is not registered.";
	}
	public static String generateWrongPasswordMessage() {
		return "Wrong password.";
	}
	public static String generateMustBeNaturalNumberBiggerThanZeroMessage(String s) {
		return s + " field must be a natural number higher than 0 (ex. 1, 2, 5, 6, etc.).";
	}
	public static String generateMustBeInValidPriceFormatMessage(String s) {
		return s + " must be written in a valid price format.";
	}
	public static String generateMustBeInValidTimeFormatMessage(String s) {
		return s + " must be written in a 24-hour time format (\'h:mm\' or \'hh:mm\').";
	}
	public static String generateMustBeInValidLatitudeFormatMessage(String s) {
		return s + " must be written in valid latitude format (-90 to 90, with or without decimal spaces).";
	}
	public static String generateMustBeInValidLongitudeFormatMessage(String s) {
		return s + " must be written in valid longitude format (-180 to 180, with or without decimal spaces).";
	}
	public static String generateMustBeInValidStreetNumberFormatMessage(String s) {
		return s + " must be written in valid street number format (ex. 102, 50a, 12B, etc.).";
	}
	public static String generateMustBeInValidNumberFormatMessage(String s) {
		return s + " can only contain numbers.";
	}
	public static String generateSuccessfulDeleteMessage(String s) {
		return "You have successfully deleted an amenity!";
	}
	public static String generateMustBeInValidDateFormatMessage(String s) {
		return s + " must be written in the \"dd-mm-yyyy\" date format.";
	}
	public static String generateChosenDatesNotAvailableMessage() {
		return "The chosen dates are not available.";
	}
	public static String generateEntryAlreadyExistsMessage(String s) {
		return s + " already exists in the database.";
	}
	public static String generateSpammingMessage() {
		return "Don't spam! You have already left this comment.";
	}
	
}
