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
	private ApartmentStatus status;
	private ArrayList<String> busyDates;
	private ArrayList<Amenity> amenities;
	private Boolean enabled;
	
	public Apartment(int id, Type type, int roomCount, int guestCount, Location location, Host host,
			String price, String checkInTime, String checkOutTime, ApartmentStatus status, boolean enabled) {
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
		this.busyDates = new ArrayList<String>();
		this.amenities = new ArrayList<Amenity>();
		this.enabled = enabled;
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

	public ApartmentStatus getStatus() {
		return status;
	}

	public void setStatus(ApartmentStatus status) {
		this.status = status;
	}

	public ArrayList<String> getBusyDates() {
		return busyDates;
	}

	public void setBusyDates(ArrayList<String> busyDates) {
		this.busyDates = busyDates;
	}

	public ArrayList<Amenity> getAmenities() {
		return amenities;
	}

	public void setAmenities(ArrayList<Amenity> amenities) {
		this.amenities = amenities;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}
