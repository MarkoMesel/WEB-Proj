package model;

public class Comment {
	private Integer id;
	private Guest guest;
	private Apartment apartment;
	private String message;
	private Double rating;
	private Boolean hidden;
	
	public Comment(Integer id, Guest guest, Apartment apartment, String message, Double rating, Boolean hidden) {
		super();
		this.id = id;
		this.guest = guest;
		this.apartment = apartment;
		this.message = message;
		this.rating = rating;
		this.hidden = hidden;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Apartment getApartment() {
		return apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}
	
}
