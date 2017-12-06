package br.senai.sp.informatica.model;

public class User {	
	private int Id;
	private String Name;
	private String Username;
	private String Password;
	public User(int id, String name) {
		super();
		Id = id;
		Name = name;
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
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}	
}
