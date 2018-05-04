package br.senai.sp.keep.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {
	private Long id;
	private String title;
	private String description;
	private Boolean highPriority;
	private LocalDate conclusionDate;
	
	public Task(Long id, String title, String description, Boolean highPriority, LocalDate conclusionDate) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.highPriority = highPriority;
		this.conclusionDate = conclusionDate;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Task) {
			return getId() == ((Task)obj).getId();
		}
		return false;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getHighPriority() {
		return highPriority;
	}
	public void setHighPriority(Boolean highPriority) {
		this.highPriority = highPriority;
	}
	public LocalDate getConclusionDate() {
		return conclusionDate;
	}
	public void setConclusionDate(LocalDate conclusionDate) {
		this.conclusionDate = conclusionDate;
	}
	public String getHighPriorityText() {
		return highPriority ? "checked" : "unchecked"; 
	}
}