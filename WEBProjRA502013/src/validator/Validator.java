package validator;

import javax.servlet.http.HttpServletRequest;

import controller.ContainerController;
import message.MessageGenerator;

public class Validator {
	//FIELDS
	
	//Constants
	public static final int MINIMUM_PASSWORD_LENGTH = 8;
	
	//METHODS
	
	//Validate
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
	public static ValidationResponse validateApartment(HttpServletRequest request) {
		//Room Count
		if(ValidationRules.isEmpty(request.getParameter("roomCount")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Room count"));
		if(ValidationRules.containsForbiddenSymbol(request.getParameter("roomCount")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Room count"));
		if(ValidationRules.isNotNumberWithNoLeadingZeros(request.getParameter("roomCount")))
			return new ValidationResponse(false, MessageGenerator.generateMustBeNaturalNumberBiggerThanZeroMessage("Room count"));
		//Guest Count
		if(ValidationRules.isEmpty(request.getParameter("guestCount")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Guest count"));
		if(ValidationRules.containsForbiddenSymbol(request.getParameter("guestCount")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Guest count"));
		if(ValidationRules.isNotNumberWithNoLeadingZeros(request.getParameter("guestCount")))
			return new ValidationResponse(false, MessageGenerator.generateMustBeNaturalNumberBiggerThanZeroMessage("Guest count"));
		//Price
		if(ValidationRules.isEmpty(request.getParameter("price")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Price"));
		if(ValidationRules.containsForbiddenSymbol(request.getParameter("price")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Price"));
		if(ValidationRules.isNotInValidPriceFormat(request.getParameter("price")))
			return new ValidationResponse(false, MessageGenerator.generateMustBeInValidPriceFormatMessage("Price"));
		//Check-In Time
		if(ValidationRules.isEmpty(request.getParameter("checkInTime")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Check-In time"));
		if(ValidationRules.containsForbiddenSymbol(request.getParameter("checkInTime")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Check-In time"));
		if(ValidationRules.isNotInValidTimeFormat(request.getParameter("checkInTime")))
			return new ValidationResponse(false, MessageGenerator.generateMustBeInValidTimeFormatMessage("Check-In time"));
		//Check-Out Time
		if(ValidationRules.isEmpty(request.getParameter("checkOutTime")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Check-Out time"));
		if(ValidationRules.containsForbiddenSymbol(request.getParameter("checkOutTime")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Check-Out time"));
		if(ValidationRules.isNotInValidTimeFormat(request.getParameter("checkOutTime")))
			return new ValidationResponse(false, MessageGenerator.generateMustBeInValidTimeFormatMessage("Check-Out time"));
		//Latitude
		if(ValidationRules.isEmpty(request.getParameter("latitude")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Latitude"));
		if(ValidationRules.containsForbiddenSymbol(request.getParameter("latitude")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Latitude"));
		if(ValidationRules.isNotInValidLatitudeFormat(request.getParameter("latitude")))
			return new ValidationResponse(false, MessageGenerator.generateMustBeInValidLatitudeFormatMessage("Latitude"));
		//Longitude
		if(ValidationRules.isEmpty(request.getParameter("longitude")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Longitude"));
		if(ValidationRules.containsForbiddenSymbol(request.getParameter("longitude")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Longitude"));
		if(ValidationRules.isNotInValidLongitudeFormat(request.getParameter("longitude")))
			return new ValidationResponse(false, MessageGenerator.generateMustBeInValidLongitudeFormatMessage("Longitude"));
		//Street Name
		if(ValidationRules.isEmpty(request.getParameter("streetName")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Street name"));
		if(ValidationRules.containsForbiddenSymbol(request.getParameter("streetName")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Street name"));
		//Street Number
		if(ValidationRules.isEmpty(request.getParameter("streetNumber")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Street number"));
		if(ValidationRules.containsForbiddenSymbol(request.getParameter("streetNumber")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Street number"));
		if(ValidationRules.isNotInValidStreetNumberFormat(request.getParameter("streetNumber")))
			return new ValidationResponse(false, MessageGenerator.generateMustBeInValidStreetNumberFormatMessage("Street number"));
		//City
		if(ValidationRules.isEmpty(request.getParameter("city")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("City"));
		if(ValidationRules.containsForbiddenSymbol(request.getParameter("city")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("City"));
		//Post Number
		if(ValidationRules.isEmpty(request.getParameter("postNumber")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Post number"));
		if(ValidationRules.containsForbiddenSymbol(request.getParameter("postNumber")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Post number"));
		if(ValidationRules.isNotInValidNumberFormat(request.getParameter("postNumber")))
			return new ValidationResponse(false, MessageGenerator.generateMustBeInValidNumberFormatMessage("Post number"));
		//Valid
		return new ValidationResponse(true, "");
	}
	public static ValidationResponse validateEditAmenity(HttpServletRequest request) {
		//Amenity Name
		if(ValidationRules.isEmpty(request.getParameter("amenityName")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Amenity name"));
		if(ValidationRules.containsForbiddenSymbol(request.getParameter("amenityName")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Amenity name"));
		//Amenity Details
		if(ValidationRules.isEmpty(request.getParameter("amenityDetails")))
			return new ValidationResponse(false, MessageGenerator.generateNotEmptyMessage("Amenity details"));
		if(ValidationRules.containsForbiddenSymbol(request.getParameter("amenityDetails")))
			return new ValidationResponse(false, MessageGenerator.generateNotSymbolMessage("Amenity details"));
		//Valid
		return new ValidationResponse(true, "");
	}
}
