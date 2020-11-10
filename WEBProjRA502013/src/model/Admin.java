package model;

public class Admin extends User {
	public Admin(int id, String username, String password, String firstName, String lastName, Gender gender, Boolean enabled) {
		super(id, username, password, firstName, lastName, gender, enabled);
		this.role = Role.ADMIN;
	}
	
}
