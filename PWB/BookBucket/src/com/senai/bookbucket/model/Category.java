package com.senai.bookbucket.model;

public class Category {
	private Long id;
	private String name;
	private User user;
	public Category(Long id, String name, User user) {
		super();
		this.id = id;
		this.name = name;
		this.user = user;
	}
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
}
