package validator;

import java.util.ArrayList;

import model.User;

public class ValidationRules {
	
	public boolean containsForbiddenSymbol(String s) {
		if(s.contains("|"))
			return true;
		return false;
	}
	
	public boolean isEmpty(String s) {
		if(s.trim().isEmpty()) 
			return true;
		return false;
	}

	public boolean usernameIsNotUnique(String s, ArrayList<User> list) {
		for(User u : list)
			if(u.getUsername().equals(s))
				return true;
		return false;
	}

	public boolean isNotLongEnough(String s, int length) {
		if(s.length() < length)
			return true;
		return false;
	}

	public boolean passwordsDontMatch(String s0, String s1) {
		if(!s0.contentEquals(s1))
			return true;
		return false;
	}

	public boolean isPasswordIncorrect(String username, String password, ArrayList<User> users) {
		User u = users.stream().filter(u0 -> username.equals(u0.getUsername())).findFirst().orElse(null);
		if(u != null)
			if(u.getPassword().equals(password))
				return false;
		return true;
	}
	
}
