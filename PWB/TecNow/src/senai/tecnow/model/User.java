package senai.tecnow.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class User {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private Long id;
	private String name;
	private String email;
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date dateOfBirth;
	private String password;
	private Gender gender;
	
	public User() {
		// Standard constructor to allow Framework usage (like Spring)
	}

	public User(Long id, String name, String email, Date dateOfBirth, String password, Gender gender) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
		this.gender = gender;
	}
	
	public String getFormattedDate() {
		return sdf.format(dateOfBirth);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", dateOfBirth=" + dateOfBirth + ", password="
				+ password + ", gender=" + gender + "]";
	}
	
}
