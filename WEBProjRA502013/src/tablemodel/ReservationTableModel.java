package tablemodel;

public class ReservationTableModel {
	public String id;
	public String apartment;
	public String date;
	public String nights;
	public String price;
	public String message;
	public String guest;
	public String status;
	public String noNightsLeft;
	
	public ReservationTableModel(String id, String apartment, String date, String nights, String price, String message, String guest,
			String status, String noNightsLeft) {
		super();
		this.id = id;
		this.apartment = apartment;
		this.date = date;
		this.nights = nights;
		this.price = price;
		this.message = message;
		this.guest = guest;
		this.status = status;
		this.noNightsLeft = noNightsLeft;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNights() {
		return nights;
	}

	public void setNights(String nights) {
		this.nights = nights;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNoNightsLeft() {
		return noNightsLeft;
	}

	public void setNoNightsLeft(String noNightsLeft) {
		this.noNightsLeft = noNightsLeft;
	}
}
