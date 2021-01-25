package helper;

public class DateManager {

	public static String convertDateToYYYYMMDDString(String date) {
		String[] parts = date.split("-");
		if(parts.length == 3)
			return parts[2] + parts[1] + parts[0];
		else {
			System.out.println("The date " + date + "Has an invalid date format or it cannot be correctly split.");
			return "0";
		}
	}

}
