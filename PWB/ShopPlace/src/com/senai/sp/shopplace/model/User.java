package com.senai.sp.shopplace.model;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.DigestUtils;

public class User {	
	private Long id;
	private String name;
	private String email;
	private String password;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	private static SimpleDateFormat simpleDateFormat;
	static {
		initializeFormatter();
	}
	public User() {
		// Standard constructor to allow Spring Framework to create an instance of model
	}
	public User(Long id, String name, String email, String password, Date dateOfBirth) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getFormattedDateOfBirth() {
		if(simpleDateFormat == null)
			initializeFormatter();
		String result = simpleDateFormat.format(dateOfBirth);
		return result;
	}
	private static void initializeFormatter() {
		simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}
	public static String hashPassword(String password) throws UnsupportedEncodingException {
		return DigestUtils.md5DigestAsHex(password.getBytes("UTF-8"));
	}
	public void hashPassword() {
		try {
			this.password = hashPassword(password);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}	
}
