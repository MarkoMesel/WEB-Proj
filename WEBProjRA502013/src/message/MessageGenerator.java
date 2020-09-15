package message;

public class MessageGenerator {
	public static String generateSuccessfulRegisterMessage() {
		return "You have successfully registered your account!";
	}
	public static String generateNotEmptyMessage(String s) {
		return s + " must not be empty.";
	}
	public static String generateNotSymbolMessage(String s) {
		return s + " must not contain the symbol \'|\'.";
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
}
