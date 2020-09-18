package dbm;

public class UserDbModel {
	public String id;
	public String username;
	public String password;
	public String firstName;
	public String lastName;
	public String gender;
	public String role;
	public String enabled;
	
	public UserDbModel(String id, String username, String password, String firstName, String lastName, String gender,
			String role, String enabled) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.role = role;
		this.enabled = enabled;
	}
	
	public UserDbModel(String[] array) {
		this.id = array[0];
		this.username = array[1];
		this.password = array[2];
		this.firstName = array[3];
		this.lastName = array[4];
		this.gender = array[5];
		this.role = array[6];
		this.enabled = array[7];
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
}
