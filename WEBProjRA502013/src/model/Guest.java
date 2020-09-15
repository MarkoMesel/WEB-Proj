package model;

import java.util.ArrayList;

public class Guest extends User {
	private ArrayList<Apartment> reservedApartments;
	private ArrayList<Reservation> reservations;

	public Guest(int id, String username, String password, String firstName, String lastName, Gender gender, Boolean enabled) {
		super(id, username, password, firstName, lastName, gender, enabled);
		this.role = Role.GUEST;
		reservedApartments = new ArrayList<Apartment>();
		reservations = new ArrayList<Reservation>();
	}

	public ArrayList<Apartment> getReservedApartments() {
		return reservedApartments;
	}

	public void setReservedApartments(ArrayList<Apartment> reservedApartments) {
		this.reservedApartments = reservedApartments;
	}

	public ArrayList<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(ArrayList<Reservation> reservations) {
		this.reservations = reservations;
	}
}
