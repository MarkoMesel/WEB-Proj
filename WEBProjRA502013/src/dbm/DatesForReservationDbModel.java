package dbm;

public class DatesForReservationDbModel {
	public String id;
	public String date;
	public String apartmentId;
	
	public DatesForReservationDbModel(String id, String date, String apartmentId) {
		this.id = id;
		this.date = date;
		this.apartmentId = apartmentId;
	}
	
	public DatesForReservationDbModel(String[] array) {
		this.id = array[0];
		this.date = array[1];
		this.apartmentId = array[2];
	}
	
}
