package com.senai.bookbucket.model;

public class Book {
	private Long id;
	private String name;
	private Category category;
	public Book(Long id, String name, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
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
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
}
