package dbm;

public class ApartmentPairingDbModel {
	public String id;
	public String apartmentId;
	public String strPair;
	
	public ApartmentPairingDbModel(String id, String apartmentId, String strPair) {
		super();
		this.id = id;
		this.apartmentId = apartmentId;
		this.strPair = strPair;
	}
	
	public ApartmentPairingDbModel(String[] array) {
		this.id = array[0];
		this.apartmentId = array[1];
		this.strPair = array[2];
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApartmentId() {
		return apartmentId;
	}
	public void setApartmentId(String apartmentId) {
		this.apartmentId = apartmentId;
	}
	public String getStrPair() {
		return strPair;
	}
	public void setStrPair(String strPair) {
		this.strPair = strPair;
	}
	
}
