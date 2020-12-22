package tablemodel;

public enum ReservationTableParameter {
	APARTMENT("Apartment"),
	DATE("Reservation Date"),
	NIGHTS("Night Count"),
	PRICE("Price"),
	MESSAGE("Message"),
	GUEST("Guest"),
	STATUS("Status");

	private String label;

	ReservationTableParameter(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public static ReservationTableParameter valueOfLabel(String label) {
	    for (ReservationTableParameter e : values()) {
	        if (e.label.equals(label)) {
	            return e;
	        }
	    }
	    return null;
	}
}
