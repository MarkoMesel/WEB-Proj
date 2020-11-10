package dbm;

public class ApartmentAmenityDbModel {
	public String id;
	public String apartmentId;
	public String amenityId;
	
	public ApartmentAmenityDbModel(String id, String apartmentId, String amenityId) {
		super();
		this.id = id;
		this.apartmentId = apartmentId;
		this.amenityId = amenityId;
	}
	
	public ApartmentAmenityDbModel(String[] array) {
		this.id = array[0];
		this.apartmentId = array[1];
		this.amenityId = array[2];
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
	public String getAmenityId() {
		return amenityId;
	}
	public void setAmenityId(String amenityId) {
		this.amenityId = amenityId;
	}
	
}
