package tablemodel;

public class AmenityTableModel {
	public String id;
	public String name;
	public String details;
	public String enabled;
	public String checked;
	
	public AmenityTableModel(String id, String name, String details, String enabled, String checked) {
		super();
		this.id = id;
		this.name = name;
		this.details = details;
		this.enabled = enabled;
		this.checked = checked;
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

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}
}
