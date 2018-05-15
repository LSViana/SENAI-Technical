package senai.sstorage.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "patrimony_item")
public class PatrimonyItem {
	
	@Id
	private Long id;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "id_patrimony")
	@javax.validation.constraints.NotNull
	private Patrimony patrimony;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "id_environment")
	@javax.validation.constraints.NotNull
	private Environment environment;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;
	
	@Column(nullable = false)
	private ItemState state = ItemState.ACTIVE;
	
	@Column(nullable = false)
	private Date lastMovement;

	public ItemState getState() {
		return state;
	}

	public void setState(ItemState state) {
		this.state = state;
	}

	public Date getLastMovement() {
		return lastMovement;
	}

	public void setLastMovement(Date lastMovement) {
		this.lastMovement = lastMovement;
	}

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
