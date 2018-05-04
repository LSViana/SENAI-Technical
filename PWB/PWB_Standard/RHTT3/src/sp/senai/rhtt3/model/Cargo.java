package sp.senai.rhtt3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cargo {
	
	@Id
	private Long id;
	@Column(length = 64, nullable = false)
	private String name;
	@Column(nullable = false)
	private Double salary;
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
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	// LONG = BIGINT
	// INTEGER = INT
	// DOUBLE, FLOAT = NUMERIC
	// PRECISION = QUANTIDADE DE CASAS DE NÚMEROS INTEIROS
	// SCALE = QUANTIDADE DE CASAS DE NÚMEROS DECIMAIS

}
