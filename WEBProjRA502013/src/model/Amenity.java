package model;

public class Amenity {
	public Integer id;
	public String name;
	public String details;
	
	public Amenity(int id, String name, String details) {
		super();
		this.id = id;
		this.name = name;
		this.details = details;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
}
