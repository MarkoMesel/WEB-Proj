package model;

public class Location {
	private Integer id;
	private Float geoWidth;
	private Float geoLength;
	private String streetName;
	private String streetNumber;
	private String city;
	private String postNumber;
	
	public Location(Integer id, Float geoWidth, Float geoLength, String streetName, String streetNumber, String city,
			String postNumber) {
		super();
		this.id = id;
		this.geoWidth = geoWidth;
		this.geoLength = geoLength;
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.city = city;
		this.postNumber = postNumber;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getGeoWidth() {
		return geoWidth;
	}
	public void setGeoWidth(float geoWidth) {
		this.geoWidth = geoWidth;
	}
	public float getGeoLength() {
		return geoLength;
	}
	public void setGeoLength(float geoLength) {
		this.geoLength = geoLength;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostNumber() {
		return postNumber;
	}
	public void setPostNumber(String postNumber) {
		this.postNumber = postNumber;
	}
}
