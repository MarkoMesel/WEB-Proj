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
}
