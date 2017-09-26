package br.sp.senai.informatica.noon.model;

public class Employee {
	private int Id;
	private String Name;
	private String Email;
	private String Password;
	public Employee(int id, String name, String email, String password) {
		super();
		Id = id;
		Name = name;
		Email = email;
		Password = password;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
}
