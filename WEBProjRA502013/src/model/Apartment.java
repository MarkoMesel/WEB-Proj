package model;

import java.util.ArrayList;

public class Apartment {
	private Integer id;
	private Type type;
	private Integer roomCount;
	private Integer guestCount;
	private Location location;
	private Host host;
	private Integer price;
	private String bookingTime;
	private String cancelTime;
	private Status status;
	private ArrayList<String> datesForReservation;
	
	public Apartment(int id, Type type, int roomCount, int guestCount, Location location, Host host,
			int price, String bookingTime, String cancelTime, Status status) {
		super();
		this.id = id;
		this.type = type;
		this.roomCount = roomCount;
		this.guestCount = guestCount;
		this.location = location;
		this.host = host;
		this.price = price;
		this.bookingTime = bookingTime;
		this.cancelTime = cancelTime;
		this.status = status;
		datesForReservation = new ArrayList<String>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Integer getRoomCount() {
		return roomCount;
	}

	public void setRoomCount(Integer roomCount) {
		this.roomCount = roomCount;
	}

	public Integer getGuestCount() {
		return guestCount;
	}

	public void setGuestCount(Integer guestCount) {
		this.guestCount = guestCount;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Host getHost() {
		return host;
	}

	public void setHost(Host host) {
		this.host = host;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}

	public String getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public ArrayList<String> getDatesForReservation() {
		return datesForReservation;
	}

	public void setDatesForReservation(ArrayList<String> datesForReservation) {
		this.datesForReservation = datesForReservation;
	}
}
