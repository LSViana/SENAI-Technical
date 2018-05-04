package sp.senai.ianestt3.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Movement {
	@Id
	private Long id;
	@Column(nullable = false)
	private Date date;
	@ManyToOne
	private Patrimony patrimony;
	@ManyToOne
	private User user;
	@ManyToOne
	private Environment originEnvironment;
	@ManyToOne
	private Environment destinyEnvironment;	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Patrimony getPatrimony() {
		return patrimony;
	}
	public void setPatrimony(Patrimony patrimony) {
		this.patrimony = patrimony;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
}
