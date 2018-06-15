package senai.sstorage.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "patrimony")
public class Patrimony {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 40, nullable = false, unique = false)
	@NotNull
	@Size(min = 1, max = 40)
	private String name;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "id_user")
	private User user;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "id_patrimony_category")
	@NotNull
	private PatrimonyCategory patrimonyCategory;

//	@NotNull
//	@Lob
//	private String imageB64;

	@Column(nullable = false)
	private Date datetime;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PatrimonyCategory getPatrimonyCategory() {
		return patrimonyCategory;
	}

	public void setPatrimonyCategory(PatrimonyCategory patrimonyCategory) {
		this.patrimonyCategory = patrimonyCategory;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

//	public String getImageB64() {
//		return imageB64;
//	}
//
//	public void setImageB64(String imageB64) {
//		this.imageB64 = imageB64;
//	}

}
