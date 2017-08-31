package br.com.ozcorp.business;
import java.text.NumberFormat;
public class Employee {
	private final static NumberFormat format = NumberFormat.getCurrencyInstance();
	
	private String name;
	private String rg;
	private long cpf;
	private String enrollment;
	private String email;
	private String password;
	private Role post;
	private BloodType blood;
	private Gender gender;
	
	protected Employee(String name, String rg, long cpf, String enrollment, String email, String password, Role post,
			BloodType blood, Gender gender) {
		super();
		this.name = name;
		this.rg = rg;
		this.cpf = cpf;
		this.enrollment = enrollment;
		this.email = email;
		this.password = password;
		this.post = post;
		this.blood = blood;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public String getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(String enrollment) {
		this.enrollment = enrollment;
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

	public Role getPost() {
		return post;
	}

	public void setPost(Role post) {
		this.post = post;
	}

	public BloodType getBlood() {
		return blood;
	}

	public void setBlood(BloodType blood) {
		this.blood = blood;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString()
    {
        return String.format("Employee's Data:\n%-17s%s\n%-17s%s\n%-17s%s\n%-17s%s\n%-17s%s\n%-17s%s\n%-17s%s\n%-17s%s\n%-17s%s\n%-17s%s\n",
        		"Name:", name,
        		"RG:", rg,
        		"CPF:", cpf,
        		"Enrollment:", enrollment,
        		"Email:", email,
        		"Password:", password,
        		"Post:", post,
        		"Base Salary:", format.format(post.getBaseSalary()), "Blood:", blood, "Gender:", gender);
    }
}
