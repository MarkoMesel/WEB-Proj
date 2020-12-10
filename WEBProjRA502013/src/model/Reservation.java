package model;

public class Reservation {
	private Integer id;
	private Apartment apartment;
	private String date;
	private Integer numberOfNights;
	private String totalPrice;
	private String reservationMessage;
	private Guest guest;
	private ReservationStatus status;
	
	public Reservation(Integer id, Apartment apartment, String reservationDate, Integer numberOfNights, String totalPrice,
			String reservationMessage, Guest guest, ReservationStatus status) {
		super();
		this.id = id;
		this.apartment = apartment;
		this.date = reservationDate;
		this.numberOfNights = numberOfNights;
		this.totalPrice = totalPrice;
		this.reservationMessage = reservationMessage;
		this.guest = guest;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Apartment getApartment() {
		return apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getNumberOfNights() {
		return numberOfNights;
	}

	public void setNumberOfNights(Integer numberOfNights) {
		this.numberOfNights = numberOfNights;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getReservationMessage() {
		return reservationMessage;
	}

	public void setReservationMessage(String reservationMessage) {
		this.reservationMessage = reservationMessage;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}
	
}
