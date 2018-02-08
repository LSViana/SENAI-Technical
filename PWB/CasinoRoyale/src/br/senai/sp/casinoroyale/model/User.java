package br.senai.sp.casinoroyale.model;

public class User {
	private Long id;
	private String firstName;
	private String lastName;
	private GenderClass.Gender gender;
	private Boolean smoker;
	
	public User(Long id, String firstName, String lastName, GenderClass.Gender gender, Boolean smoker) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.smoker = smoker;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public GenderClass.Gender getGender() {
		return gender;
	}
	public void setGender(GenderClass.Gender gender) {
		this.gender = gender;
	}
	public Boolean getSmoker() {
		return smoker;
	}
	public void setSmoker(Boolean smoker) {
		this.smoker = smoker;
	}
}
