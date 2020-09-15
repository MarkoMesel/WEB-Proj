package dbm;

public class ApartmentCommentDbModel {
	public String id;
	public String userId;
	public String apartmentId;
	public String message;
	public String rating;
	
	public ApartmentCommentDbModel(String id, String userId, String apartmentId, String message, String rating) {
		this.id = id;
		this.userId = userId;
		this.apartmentId = apartmentId;
		this.message = message;
		this.rating = rating;
	}
	
	public ApartmentCommentDbModel(String[] array) {
		this.id = array[0];
		this.userId = array[1];
		this.apartmentId = array[2];
		this.message = array[3];
		this.rating = array[4];
	}
}
