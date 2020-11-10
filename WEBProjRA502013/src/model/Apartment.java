package model;

import java.util.ArrayList;

public class Apartment {
	private Integer id;
	private Type type;
	private Integer roomCount;
	private Integer guestCount;
	private Location location;
	private Host host;
	private String price;
	private String checkInTime;
	private String checkOutTime;
	private Status status;
	private ArrayList<String> datesForReservation;
	private ArrayList<Amenity> amenities;
	
	public Apartment(int id, Type type, int roomCount, int guestCount, Location location, Host host,
			String price, String checkInTime, String checkOutTime, Status status) {
		super();
		this.id = id;
		this.type = type;
		this.roomCount = roomCount;
		this.guestCount = guestCount;
		this.location = location;
		this.host = host;
		this.price = price;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.status = status;
		this.datesForReservation = new ArrayList<String>();
		this.amenities = new ArrayList<Amenity>();
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}

	public String getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(String checkOutTime) {
		this.checkOutTime = checkOutTime;
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

	public ArrayList<Amenity> getAmenities() {
		return amenities;
	}

	public void setAmenities(ArrayList<Amenity> amenities) {
		this.amenities = amenities;
	}
	
}
