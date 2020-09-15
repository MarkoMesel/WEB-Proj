package dbm;

public class PicturesDbModel {
	public String id;
	public String apartmentId;
	public String pictureUrl;
	
	public PicturesDbModel(String id, String apartmentId, String pictureUrl) {
		this.id = id;
		this.apartmentId = apartmentId;
		this.pictureUrl = pictureUrl;
	}
	
	public PicturesDbModel(String[] array) {
		this.id = array[0];
		this.apartmentId = array[1];
		this.pictureUrl = array[2];
	}	
}
