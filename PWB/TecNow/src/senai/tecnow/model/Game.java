package senai.tecnow.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Game {
	
	private Long id;
	private String name;
	private GameCategory category;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateRegister;
	private User user;
	
	public Game() {
		// Standard constructor to allow Framework usage (like Spring)
	}

	public Game(Long id, String name, GameCategory category, Date dateRegister, User user) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.dateRegister = dateRegister;
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

	public GameCategory getCategory() {
		return category;
	}

	public void setCategory(GameCategory category) {
		this.category = category;
	}

	public Date getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(Date dateRegister) {
		this.dateRegister = dateRegister;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
