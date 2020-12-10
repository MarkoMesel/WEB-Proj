package tablemodel;

public class CommentTableModel {
	String id;
	String guest;
	String message;
	String rating;
	String hidden;
	
	public CommentTableModel(String id, String guest, String message, String rating, String hidden) {
		super();
		this.id = id;
		this.guest = guest;
		this.message = message;
		this.rating = rating;
		this.hidden = hidden;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
}
