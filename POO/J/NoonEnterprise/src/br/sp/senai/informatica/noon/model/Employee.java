package br.sp.senai.informatica.noon.model;

import java.text.DecimalFormat;

public class Employee {
	private long Id;
	private String Name;
	private String Email;
	private String CPF;
	private String Password;
	public Employee(long id, String name, String email, String cPF, String password) {
		super();
		Id = id;
		Name = name;
		Email = email;
		CPF = cPF;
		Password = password;
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
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
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
}
