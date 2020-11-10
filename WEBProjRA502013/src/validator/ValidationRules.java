package validator;

import java.util.ArrayList;
import java.util.regex.*;  

import model.User;

public class ValidationRules {
	
	public static String numNoLeadingZerosRegex = "^([1-9][0-9]*)$";
	public static String dollarRegex = "^\\$?\\-?([1-9]{1}[0-9]{0,2}(\\,\\d{3})*(\\.\\d{0,2})?|[1-9]{1}\\d{0,}(\\.\\d{0,2})?|0(\\.\\d{0,2})?|(\\.\\d{1,2}))$|^\\-?\\$?([1-9]{1}\\d{0,2}(\\,\\d{3})*(\\.\\d{0,2})?|[1-9]{1}\\d{0,}(\\.\\d{0,2})?|0(\\.\\d{0,2})?|(\\.\\d{1,2}))$|^\\(\\$?([1-9]{1}\\d{0,2}(\\,\\d{3})*(\\.\\d{0,2})?|[1-9]{1}\\d{0,}(\\.\\d{0,2})?|0(\\.\\d{0,2})?|(\\.\\d{1,2}))\\)$";
	public static String timeRegex = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";
	public static String latitudeRegex = "^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?)$";
	public static String longitudeRegex = "^[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$";
	public static String streetNumberRegex = "^([1-9][0-9]*[A-Za-z]?)$";
	public static String numberRegex = "^([0-9]*)$";
	
	public static boolean containsForbiddenSymbol(String s) {
		if(s.contains("|"))
			return true;
		return false;
	}	
	public static boolean isEmpty(String s) {
		if(s.trim().isEmpty()) 
			return true;
		return false;
	}
	public static boolean usernameIsNotUnique(String s, ArrayList<User> list) {
		for(User u : list)
			if(u.getUsername().equals(s))
				return true;
		return false;
	}
	public static boolean isNotLongEnough(String s, int length) {
		if(s.length() < length)
			return true;
		return false;
	}
	public static boolean passwordsDontMatch(String s0, String s1) {
		if(!s0.contentEquals(s1))
			return true;
		return false;
	}
	public static boolean isPasswordIncorrect(String username, String password, ArrayList<User> users) {
		User u = users.stream().filter(u0 -> username.equals(u0.getUsername())).findFirst().orElse(null);
		if(u != null)
			if(u.getPassword().equals(password))
				return false;
		return true;
	}
	public static boolean isNotNumberWithNoLeadingZeros(String s) {
		if(Pattern.matches(numNoLeadingZerosRegex, s))
			return false;
		return true;
	}
	public static boolean isNotInValidPriceFormat(String s) {
		if(Pattern.matches(dollarRegex, s))
			return false;
		return true;
	}
	public static boolean isNotInValidTimeFormat(String s) {
		if(Pattern.matches(timeRegex, s))
			return false;
		return true;
	}
	public static boolean isNotInValidLatitudeFormat(String s) {
		if(Pattern.matches(latitudeRegex, s))
			return false;
		return true;
	}
	public static boolean isNotInValidLongitudeFormat(String s) {
		if(Pattern.matches(longitudeRegex, s))
			return false;
		return true;
	}
	public static boolean isNotInValidStreetNumberFormat(String s) {
		if(Pattern.matches(streetNumberRegex, s))
			return false;
		return true;
	}
	public static boolean isNotInValidNumberFormat(String s) {
		if(Pattern.matches(numberRegex, s))
			return false;
		return true;
	}
}
