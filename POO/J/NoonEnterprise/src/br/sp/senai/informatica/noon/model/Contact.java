package br.sp.senai.informatica.noon.model;

import java.time.LocalDate;

public class Contact {
	private int id;
	private String name;
	private String email;
	private String address;
	private LocalDate dateOfBirthday;
	
	public Contact() {
		// Standard Constructor
	}
	
	public Contact(int id, String name, String email, String adress, LocalDate dateOfBirthday) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = adress;
		this.dateOfBirthday = dateOfBirthday;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LocalDate getDateOfBirthday() {
		return dateOfBirthday;
	}
	public void setDateOfBirthday(LocalDate dateOfBirthday) {
		this.dateOfBirthday = dateOfBirthday;
	}
	
}