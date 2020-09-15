package controller;

import java.util.ArrayList;

import model.Guest;
import model.User;

public class ContainerController {
	public static ArrayList<User> users = new ArrayList<User>();

	public static void populateUserList() {
		users = new ArrayList<User>();
		users = DatabaseController.getUsersFromDatabase();
	}

	public static void saveUserList() {
		DatabaseController.saveUsersToDatabase();
	}

	public static void addUser(Guest guest) {
		users.add(guest);
	}

	public static User findUserByUsernameAndPassword(String username, String password) {
		return users.stream()
			.filter(
				user -> 
					username.equals(user.getUsername())
					&& password.equals(user.getPassword()))
			.findFirst()
			.orElse(null);
	}
}
