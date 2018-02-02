package br.senai.sp.keep.model;

import java.time.LocalDateTime;

public class Task {
	private Long id;
	private String title;
	private String description;
	private Boolean highPriority;
	private LocalDateTime doneDate;
	
	public Task(Long id, String title, String description, Boolean highPriority, LocalDateTime conclusionDate) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.highPriority = highPriority;
		this.doneDate = conclusionDate;
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
	public LocalDateTime getDoneDate() {
		return doneDate;
	}
	public void setDoneDate(LocalDateTime doneDate) {
		this.doneDate = doneDate;
	}	
}