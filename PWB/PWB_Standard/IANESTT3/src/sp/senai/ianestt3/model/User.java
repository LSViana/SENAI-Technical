package sp.senai.ianestt3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.DigestUtils;

@Entity
public class User {
	
	@Id
	private Long id;
	@Column(nullable = false, length = 32)
	@NotNull
	@Size(min = 1, max = 20)
	private String firstName;
	@Column(nullable = false, length = 32)
	@NotNull
	@Size(min = 1, max = 40)
	private String lastName;
	@Column(nullable = false, length = 64, unique = true)
	@NotNull
	@Email
	private String email;
	@Column(nullable = false, length = 32)
	@NotNull
	@Size(min = 6, max = 20)
	private String password;
	@Column(nullable = false)
	@NotNull
	private UserType type;
	
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
	
	public void hashPassword() {
		this.password = DigestUtils.md5DigestAsHex(this.password.getBytes());
	}
}
