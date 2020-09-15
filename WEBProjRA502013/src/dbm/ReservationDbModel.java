package dbm;

public class ReservationDbModel {
	public String id;
	public String apartmentId;
	public String date;
	public String nightsCount;
	public String totalPrice;
	public String reservationMessage;
	public String guestId;
	public String status;
	
	public ReservationDbModel(String id, String apartmentId, String date, String nightsCount, String totalPrice,
			String reservationMessage, String guestId, String status) {
		this.id = id;
		this.apartmentId = apartmentId;
		this.date = date;
		this.nightsCount = nightsCount;
		this.totalPrice = totalPrice;
		this.reservationMessage = reservationMessage;
		this.guestId = guestId;
		this.status = status;
	}
	
	public ReservationDbModel(String[] array) {
		this.id = array[0];
		this.apartmentId = array[1];
		this.date = array[2];
		this.nightsCount = array[3];
		this.totalPrice = array[4];
		this.reservationMessage = array[5];
		this.guestId = array[6];
		this.status = array[7];
	}
}
