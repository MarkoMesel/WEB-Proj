package validator;

import javax.servlet.http.HttpServletRequest;

import controller.ContainerController;
import message.MessageGenerator;

public class Validator {
	public static ValidationRules rules = new ValidationRules();
	public static final int MINIMUM_PASSWORD_LENGTH = 8;
	
	public static ValidationResponse validateGuestRegistration(HttpServletRequest request) {
		if(rules.isEmpty(request.getParameter("username")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Username"));
		if(rules.isEmpty(request.getParameter("password")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Password"));
		if(rules.isEmpty(request.getParameter("repeatPassword")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Repeated password"));
		if(rules.isEmpty(request.getParameter("firstName")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("First name"));
		if(rules.isEmpty(request.getParameter("lastName")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Last name"));
		if(rules.usernameIsNotUnique(request.getParameter("username"), ContainerController.users))
			return new ValidationResponse(false, MessageGenerator.generateMustBeUniqueMessage("Username"));
		if(rules.containsForbiddenSymbol(request.getParameter("username")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Username"));
		if(rules.containsForbiddenSymbol(request.getParameter("password")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Password"));
		if(rules.containsForbiddenSymbol(request.getParameter("repeatPassword")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Repeated password"));
		if(rules.containsForbiddenSymbol(request.getParameter("firstName")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("First name"));
		if(rules.containsForbiddenSymbol(request.getParameter("lastName")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Last name"));
		if(rules.isNotLongEnough(request.getParameter("password"), MINIMUM_PASSWORD_LENGTH))
			return new ValidationResponse(false, MessageGenerator.generateNotLongEnoughMessage("Password", MINIMUM_PASSWORD_LENGTH));
		if(rules.isNotLongEnough(request.getParameter("repeatPassword"), MINIMUM_PASSWORD_LENGTH))
			return new ValidationResponse(false, MessageGenerator.generateNotLongEnoughMessage("Repeated password", MINIMUM_PASSWORD_LENGTH));
		if(rules.passwordsDontMatch(request.getParameter("password"), request.getParameter("repeatPassword")))
			return new ValidationResponse(false, MessageGenerator.generatePasswordsMustMatchMessage());
		return new ValidationResponse(true, "");
	}
	
	public static ValidationResponse validateLogin(HttpServletRequest request) {
		if(rules.isEmpty(request.getParameter("username")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Username"));
		if(rules.isEmpty(request.getParameter("password")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Password"));
		if(rules.containsForbiddenSymbol(request.getParameter("username")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Username"));
		if(rules.containsForbiddenSymbol(request.getParameter("password")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Password"));
		if(!rules.usernameIsNotUnique(request.getParameter("username"), ContainerController.users))
			return new ValidationResponse(false, MessageGenerator.generateIsNotRegisteredMessage("Username"));
		if(rules.isPasswordIncorrect(request.getParameter("username"), request.getParameter("password"), ContainerController.users))
			return new ValidationResponse(false, MessageGenerator.generateWrongPasswordMessage());
		return new ValidationResponse(true, "");
	}
}
