package dbm;

public class AmenityDbModel {
	public String id;
	public String name;
	public String details;
	
	public AmenityDbModel(String id, String name, String details) {
		super();
		this.id = id;
		this.name = name;
		this.details = details;
	}

	public AmenityDbModel(String[] array) {
		this.id = array[0];
		this.name = array[1];
		this.details = array[2];
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
