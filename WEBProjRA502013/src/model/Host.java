package model;

import java.util.ArrayList;

public class Host extends User {
	private ArrayList<Apartment> apartmentsUpForReservation;
	
	public Host(int id, String username, String password, String firstName, String lastName, Gender gender, Boolean enabled) {
		super(id, username, password, firstName, lastName, gender, enabled);
		this.role = Role.HOST;
		this.apartmentsUpForReservation = new ArrayList<Apartment>();
	}

	public ArrayList<Apartment> getApartmentsUpForReservation() {
		return apartmentsUpForReservation;
	}

	public void setApartmentsUpForReservation(ArrayList<Apartment> apartmentsUpForReservation) {
		this.apartmentsUpForReservation = apartmentsUpForReservation;
	}
}
