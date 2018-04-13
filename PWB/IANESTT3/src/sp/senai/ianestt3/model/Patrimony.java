package sp.senai.ianestt3.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Patrimony {
	
	@Id
	private Long id;	
	@Column(nullable = false, unique = true)
	@NotNull
	@Size(min = 1, max = 40)
	private String name;	
	@Column(nullable = false)
	private Date date;
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
}
