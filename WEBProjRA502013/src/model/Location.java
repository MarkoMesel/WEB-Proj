package model;

public class Location {
	private Integer id;
	private Float latitude;
	private Float longitude;
	private String streetName;
	private String streetNumber;
	private String city;
	private String postNumber;
	private Boolean enabled;
	
	public Location(Integer id, Float latitude, Float longitude, String streetName, String streetNumber, String city,
			String postNumber, boolean enabled) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.city = city;
		this.postNumber = postNumber;
		this.enabled = enabled;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public Float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}
