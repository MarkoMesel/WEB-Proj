package dbm;

public class ApartmentContentDbModel {
	public String id;
	public String apartmentId;
	public String contentId;
	
	public ApartmentContentDbModel(String id, String apartmentId, String contentId) {
		this.id = id;
		this.apartmentId = apartmentId;
		this.contentId = contentId;
	}
	
	public ApartmentContentDbModel(String[] array) {
		this.id = array[0];
		this.apartmentId = array[1];
		this.contentId = array[2];
	}
}
