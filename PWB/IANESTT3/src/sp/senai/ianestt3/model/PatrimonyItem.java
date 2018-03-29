package sp.senai.ianestt3.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PatrimonyItem {
	@Id
	private Long id;
	@ManyToOne
	@JoinColumn(nullable = false, name="user_id")
	private User user;
	@ManyToOne
	@JoinColumn(nullable = false, name="patrimony_id")
	private Patrimony patrimony;
	@ManyToOne
	@JoinColumn(nullable = false, name="environment_id")
	private Environment environment;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Patrimony getPatrimony() {
		return patrimony;
	}
	public void setPatrimony(Patrimony patrimony) {
		this.patrimony = patrimony;
	}
	public Environment getEnvironment() {
		return environment;
	}
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
}
