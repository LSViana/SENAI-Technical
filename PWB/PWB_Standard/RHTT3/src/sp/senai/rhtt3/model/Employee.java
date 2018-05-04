package sp.senai.rhtt3.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Employee {
	
	@Id
	private Long id;
	@Column(length = 80, nullable = false)
	private String name;
	@OneToOne
	@JoinColumn(name = "useraddress", nullable = false)
	private Address address;
	@ManyToMany
	@JoinTable(
			name = "allocation",
			joinColumns = @JoinColumn(name = "employee"), // Foreign key of this entity
			inverseJoinColumns = @JoinColumn(name = "sector") // Foreign key of the other entity
			)
	private List<Sector> sector;
	@ManyToOne
	@JoinColumn(name = "cargo", nullable = false)
	private Cargo cargo;
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Sector> getSector() {
		return sector;
	}
	public void setSector(List<Sector> sector) {
		this.sector = sector;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
}