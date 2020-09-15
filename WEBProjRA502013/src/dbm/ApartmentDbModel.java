package dbm;

public class ApartmentDbModel {
	public String id;
	public String type;
	public String roomsCount;
	public String guestCount;
	public String locationId;
	public String hostId;
	public String price;
	public String bookingTime;
	public String cancelTime;
	public String status;
	
	public ApartmentDbModel(String id, String type, String roomsCount, String guestCount, String locationId,
			String hostId, String price, String bookingTime, String cancelTime, String status) {
		this.id = id;
		this.type = type;
		this.roomsCount = roomsCount;
		this.guestCount = guestCount;
		this.locationId = locationId;
		this.hostId = hostId;
		this.price = price;
		this.bookingTime = bookingTime;
		this.cancelTime = cancelTime;
		this.status = status;
	}
	
	public ApartmentDbModel(String[] array) {
		this.id = array[0];
		this.type = array[1];
		this.roomsCount = array[2];
		this.guestCount = array[3];
		this.locationId = array[4];
		this.hostId = array[5];
		this.price = array[6];
		this.bookingTime = array[7];
		this.cancelTime = array[8];
		this.status = array[9];
	}
	
}
