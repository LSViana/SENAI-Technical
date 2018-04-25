package senai.sstorage.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.DigestUtils;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 20, nullable = false, unique = false)
	@NotNull
	@Size(min = 1, max = 30)
	private String firstName;
	
	@Column(length = 40, nullable = false, unique = false)
	@NotNull
	@Size(min = 1, max = 40)
	private String lastName;
	
	@Column(length = 64, nullable = false, unique = true)
	@NotNull
	@Email
	private String email;
	
	@Column(length = 64, nullable = false, unique = false)
	@NotNull
	@Size(min = 6, max = 24)
	private String password;
	
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
	
	public boolean isAdministrator() {
		return type == UserType.ADMINISTRATOR;
	}
	
	public void hashPassword() {
		this.password = DigestUtils.md5DigestAsHex(this.password.getBytes());
	}

}
