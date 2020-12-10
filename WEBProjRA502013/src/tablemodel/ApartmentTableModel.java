package tablemodel;

public class ApartmentTableModel {
	public String id;
	public String type;
	public String roomCount;
	public String guestCount;
	public String location;
	public String host;
	public String price;
	public String bookingTime;
	public String cancelTime;
	public String status;
	
	public ApartmentTableModel(String id, String type, String roomCount, String guestCount, String location,
			String host, String price, String bookingTime, String cancelTime, String status) {
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
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRoomCount() {
		return roomCount;
	}

	public void setRoomCount(String roomCount) {
		this.roomCount = roomCount;
	}

	public String getGuestCount() {
		return guestCount;
	}

	public void setGuestCount(String guestCount) {
		this.guestCount = guestCount;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
