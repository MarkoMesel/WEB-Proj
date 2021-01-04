package dbm;

public class CommentDbModel {
	public String id;
	public String guestId;
	public String apartmentId;
	public String rating;
	public String hidden;
	public String message;
	
	public CommentDbModel(String id, String guestId, String apartmentId, String message, String rating, String hidden) {
		super();
		this.id = id;
		this.guestId = guestId;
		this.apartmentId = apartmentId;
		this.message = message;
		this.rating = rating;
		this.hidden = hidden;
	}
	
	public CommentDbModel(String[] array) {
		this.id = array[0];
		this.guestId = array[1];
		this.apartmentId = array[2];
		this.rating = array[3];
		this.hidden = array[4];
		this.message = array[5];
	}
}
