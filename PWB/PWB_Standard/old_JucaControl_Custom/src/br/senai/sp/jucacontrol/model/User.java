package br.senai.sp.jucacontrol.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Notifies Hibernate it is a model class
 * @author LSViana
 */
@Entity(name="User")
public class User {
	@Id()
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="firstName", length=32, nullable=false)
	private String firstName;
	@Column(name="lastName", length=32, nullable=false)
	private String lastName;
	@Column(name="email", length=64, nullable=false, unique=true)
	private String email;
	@Column(name="password", length=32, nullable=false)
	private String password;
	@Column(name="type", length=4, nullable=false)
	private UserType type;
	public User() {
		// Standard Constructors
	}
	public User(Long id, String firstName, String lastName, String email, String password, UserType type) {
		this();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.type = type;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
}
