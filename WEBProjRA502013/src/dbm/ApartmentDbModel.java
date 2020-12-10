package dbm;

public class ApartmentDbModel {
	public String id;
	public String type;
	public String roomCount;
	public String guestCount;
	public String locationId;
	public String hostId;
	public String price;
	public String checkInTime;
	public String checkOutTime;
	public String status;
	public String enabled;
	
	public ApartmentDbModel(String id, String type, String roomCount, String guestCount, String locationId,
			String hostId, String price, String checkInTime, String checkOutTime, String status, String enabled) {
		this.id = id;
		this.type = type;
		this.roomCount = roomCount;
		this.guestCount = guestCount;
		this.locationId = locationId;
		this.hostId = hostId;
		this.price = price;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.status = status;
		this.enabled = enabled;
	}
	
	public ApartmentDbModel(String[] array) {
		this.id = array[0];
		this.type = array[1];
		this.roomCount = array[2];
		this.guestCount = array[3];
		this.locationId = array[4];
		this.hostId = array[5];
		this.price = array[6];
		this.checkInTime = array[7];
		this.checkOutTime = array[8];
		this.status = array[9];
		this.enabled = array[10];
	}
	
}
