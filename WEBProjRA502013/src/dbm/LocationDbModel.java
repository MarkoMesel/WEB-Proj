package dbm;

public class LocationDbModel {
	public String id;
	public String geoWidth;
	public String geoLength;
	public String streetName;
	public String streetNumber;
	public String city;
	public String postNumber;
	
	public LocationDbModel(String id, String geoWidth, String geoLength, String streetName, String streetNumber,
			String city, String postNumber) {
		this.id = id;
		this.geoWidth = geoWidth;
		this.geoLength = geoLength;
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.city = city;
		this.postNumber = postNumber;
	}
	
	public LocationDbModel(String[] array) {
		this.id = array[0];
		this.geoWidth = array[1];
		this.geoLength = array[2];
		this.streetName = array[3];
		this.streetNumber = array[4];
		this.city = array[5];
		this.postNumber = array[6];
	}
	
	
}
