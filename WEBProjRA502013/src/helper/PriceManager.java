package helper;

import model.Apartment;

public class PriceManager {
	//Compare
	public static boolean comparePrices(String p1, ComparisonOption comparison, String p2) {
		Double p1d = convertPriceToDouble(p1);
		Double p2d = convertPriceToDouble(p2);
		
		int comparisonResult = Double.compare(p1d, p2d);
		
		switch(comparison) {
			case LESS_THAN:
				return comparisonResult < 0;
			case LESS_THAN_OR_EQUAL_TO:
				return comparisonResult <= 0;
			case EQUAL_TO:
				return comparisonResult == 0;
			case GREATER_THAN_OR_EQUAL_TO:
				return comparisonResult >= 0;
			case GREATER_THAN:
				return comparisonResult > 0;
			default:
				return false;
		}
	}
	
	//Convert
	public static Double convertPriceToDouble(String price) {
		String priceString = price;
		//Remove all "," and "$" from price string
		priceString = priceString.replace("$", "").replaceAll(",", "");
		//If there's a "." at the end, remove it.
		if(priceString.length() > 0 && priceString.charAt(priceString.length()-1) == '.') {
			priceString = priceString.substring(0, priceString.length()-1);
		}
		//If the string is empty, return 0.
		if(priceString.isEmpty())
			return 0.00;
		//Convert to double
		Double result = 0.00;
		try {
			result = Double.parseDouble(priceString);
		} catch (NumberFormatException e) {
			System.out.println("Invalid format: " + priceString);
		    System.out.println("Returning 0.00");
		}
		return result;
	}

	//Calculate Total Price
	public static String calculateTotalPrice(Apartment apartment, Integer numberOfNights) {
		Double resultDouble = convertPriceToDouble(apartment.getPrice()) * numberOfNights;
		return resultDouble.toString();
	}
}
