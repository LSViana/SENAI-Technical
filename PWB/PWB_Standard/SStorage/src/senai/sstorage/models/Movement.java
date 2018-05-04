package senai.sstorage.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "movement")
public class Movement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(nullable = false, name = "id_patrimony_item")
	private PatrimonyItem patrimonyItem;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "id_originEnvironment")
	private Environment originEnvironment;

	@ManyToOne
	@JoinColumn(nullable = false, name = "id_destinyEnvironment")
	private Environment destinyEnvironment;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "id_user")
	private User user;
	
	@Column(nullable = false)
	private Date dateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PatrimonyItem getPatrimonyItem() {
		return patrimonyItem;
	}

	public void setPatrimonyItem(PatrimonyItem patrimonyItem) {
		this.patrimonyItem = patrimonyItem;
	}

	public Environment getOriginEnvironment() {
		return originEnvironment;
	}

	public void setOriginEnvironment(Environment originEnvironment) {
		this.originEnvironment = originEnvironment;
	}

	public Environment getDestinyEnvironment() {
		return destinyEnvironment;
	}

	public void setDestinyEnvironment(Environment destinyEnvironment) {
		this.destinyEnvironment = destinyEnvironment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

}
