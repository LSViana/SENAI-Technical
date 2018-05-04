package br.senai.sp.jucacontrol.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name="Occurrency")
public class Occurrency {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="title", nullable=false, length=64)
	private String title;
	@Column(name="description", nullable=false, length=512)
	private String description;
	@Column(name="dateRegister", nullable=false)
	private Date dateRegister;
	@Column(name="dateModify", nullable=false)
	private Date dateModify;
	@Column(name="dateConclusion", nullable=true)
	private Date dateConclusion;
	@ManyToOne(targetEntity=OccurrencyCategory.class, optional=false)
	private OccurrencyCategory category;
	@ManyToOne(targetEntity=User.class, optional=false)
	private User attendant;
	@ManyToOne(targetEntity=User.class, optional=false)
	private User emitter;
	public Occurrency() {
		// Standard Constructor
	}
	public Occurrency(Long id, String title, String description, Date dateRegister, Date dateModify,
			Date dateConclusion, OccurrencyCategory category, User attendant, User emitter) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.dateRegister = dateRegister;
		this.dateModify = dateModify;
		this.dateConclusion = dateConclusion;
		this.category = category;
		this.attendant = attendant;
		this.emitter = emitter;
	}
	
}
