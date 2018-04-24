package senai.sstorage.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.internal.NotNull;

@Entity
@Table(name = "patrimony_item")
public class PatrimonyItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_patrimony")
	@NotNull
	private Patrimony patrimony;
	
	@ManyToOne
	@JoinColumn(name = "id_environment")
	@NotNull
	private Environment environment;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
