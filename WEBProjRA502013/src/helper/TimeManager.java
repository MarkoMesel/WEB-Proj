package helper;

public class TimeManager {

	public static double convertTimeToDouble(String bookingTime) {
		return Double.parseDouble(bookingTime.replace(':','.'));
	}

}
