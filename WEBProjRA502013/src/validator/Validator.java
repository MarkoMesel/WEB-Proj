package validator;

import javax.servlet.http.HttpServletRequest;

import controller.ContainerController;
import message.MessageGenerator;

public class Validator {
	public static final int MINIMUM_PASSWORD_LENGTH = 8;
	
	public static ValidationResponse validateGuestRegistration(HttpServletRequest request) {
		//Username
		if(ValidationRules.isEmpty(request.getParameter("username")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Username"));
		if(ValidationRules.usernameIsNotUnique(request.getParameter("username"), ContainerController.users))
			return new ValidationResponse(false, MessageGenerator.generateMustBeUniqueMessage("Username"));
		if(ValidationRules.containsForbiddenSymbol(request.getParameter("username")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Username"));
		//Password
		if(ValidationRules.isEmpty(request.getParameter("password")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Password"));
		if(ValidationRules.containsForbiddenSymbol(request.getParameter("password")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Password"));
		if(ValidationRules.isNotLongEnough(request.getParameter("password"), MINIMUM_PASSWORD_LENGTH))
			return new ValidationResponse(false, MessageGenerator.generateNotLongEnoughMessage("Password", MINIMUM_PASSWORD_LENGTH));
		//Repeat Password
		if(ValidationRules.isEmpty(request.getParameter("repeatPassword")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Repeated password"));
		if(ValidationRules.containsForbiddenSymbol(request.getParameter("repeatPassword")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Repeated password"));
		if(ValidationRules.isNotLongEnough(request.getParameter("repeatPassword"), MINIMUM_PASSWORD_LENGTH))
			return new ValidationResponse(false, MessageGenerator.generateNotLongEnoughMessage("Repeated password", MINIMUM_PASSWORD_LENGTH));
		//Password & Repeat Password
		if(ValidationRules.passwordsDontMatch(request.getParameter("password"), request.getParameter("repeatPassword")))
			return new ValidationResponse(false, MessageGenerator.generatePasswordsMustMatchMessage());
		//First Name
		if(ValidationRules.isEmpty(request.getParameter("firstName")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("First name"));
		if(ValidationRules.containsForbiddenSymbol(request.getParameter("firstName")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("First name"));
		//Last Name
		if(ValidationRules.isEmpty(request.getParameter("lastName")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Last name"));
		if(ValidationRules.containsForbiddenSymbol(request.getParameter("lastName")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Last name"));
		//Valid
		return new ValidationResponse(true, "");
	}
	
	public static ValidationResponse validateLogin(HttpServletRequest request) {
		//Username
		if(ValidationRules.isEmpty(request.getParameter("username")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Username"));
		if(ValidationRules.containsForbiddenSymbol(request.getParameter("username")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Username"));
		if(!ValidationRules.usernameIsNotUnique(request.getParameter("username"), ContainerController.users))
			return new ValidationResponse(false, MessageGenerator.generateIsNotRegisteredMessage("Username"));
		//Password
		if(ValidationRules.isEmpty(request.getParameter("password")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Password"));
		if(ValidationRules.containsForbiddenSymbol(request.getParameter("password")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Password"));
		if(ValidationRules.isNotLongEnough(request.getParameter("password"), MINIMUM_PASSWORD_LENGTH))
			return new ValidationResponse(false, MessageGenerator.generateNotLongEnoughMessage("Password", MINIMUM_PASSWORD_LENGTH));
		//Username & Password
		if(ValidationRules.isPasswordIncorrect(request.getParameter("username"), request.getParameter("password"), ContainerController.users))
			return new ValidationResponse(false, MessageGenerator.generateWrongPasswordMessage());
		//Valid
		return new ValidationResponse(true, "");
	}

	public static ValidationResponse validateEditProfile(HttpServletRequest request) {
		//Password
		if(ValidationRules.isEmpty(request.getParameter("password")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Password"));
		if(ValidationRules.containsForbiddenSymbol(request.getParameter("password")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Password"));
		if(ValidationRules.isNotLongEnough(request.getParameter("password"), MINIMUM_PASSWORD_LENGTH))
			return new ValidationResponse(false, MessageGenerator.generateNotLongEnoughMessage("Password", MINIMUM_PASSWORD_LENGTH));
		//First Name
		if(ValidationRules.isEmpty(request.getParameter("firstName")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("First name"));
		if(ValidationRules.containsForbiddenSymbol(request.getParameter("firstName")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("First name"));
		//Last Name
		if(ValidationRules.isEmpty(request.getParameter("lastName")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Last name"));
		if(ValidationRules.containsForbiddenSymbol(request.getParameter("lastName")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Last name"));
		//Valid
		return new ValidationResponse(true, "");
	}
}
