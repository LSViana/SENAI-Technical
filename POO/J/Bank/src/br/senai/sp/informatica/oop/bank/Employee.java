package br.senai.sp.informatica.oop.bank;
import java.text.NumberFormat;
public class Employee {
	private static NumberFormat formatter = NumberFormat.getCurrencyInstance();
	// Properties' area
	private String name;
	private String password;
	private double salary;
	private boolean active;
	// Constructors' area
	// Standard constructor to allow standard creation
	public Employee() {
		// Just for tests
	}
	// Auto-generated constructor
	public Employee(String name, String password, double salary, boolean active) {
		super();
		setName(name);
		setPassword(password);
		setSalary(salary);
		setActive(active);
	}
	// Getters and Setters' area
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name.isEmpty() || name.length() < 3)
			throw new IllegalArgumentException("The name must be at least 3 characters long.");
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		if(password.isEmpty() || password.length() < 8)
			throw new IllegalArgumentException("The password must be at least 8 characters long.");
		this.password = password;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		if(salary <= 0)
			throw new IllegalArgumentException("The value for salary must be a positive value.");
		this.salary = salary;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	// Overwritten methods
	public String toString() {
		return String.format("Name: %s - Password: %s - Salary: %s - Active: %s",
				name, password, formatter.format(salary), active ? "Yes" : "No");
	}
}
