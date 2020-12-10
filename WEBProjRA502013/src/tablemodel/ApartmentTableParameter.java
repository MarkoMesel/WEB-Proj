package tablemodel;

public enum ApartmentTableParameter {
	TYPE("Type"),
	ROOM_COUNT("Room Count"),
	GUEST_COUNT("Guest Count"),
	LOCATION("Location"),
	HOST("Host"),
	PRICE("Price"),
	BOOKING_TIME("Booking Time"),
	CANCEL_TIME("Cancel Time"),
	STATUS("Status");

	private String label;

	ApartmentTableParameter(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public static ApartmentTableParameter valueOfLabel(String label) {
	    for (ApartmentTableParameter e : values()) {
	        if (e.label.equals(label)) {
	            return e;
	        }
	    }
	    return null;
	}
}
