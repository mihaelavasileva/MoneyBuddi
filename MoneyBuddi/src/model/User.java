package model;

import exceptions.InvalidDataException;

public class User {

	private long id;
	private int age;
	private String username;
	private String password;
	private String email;
	
	//constructor without id
	public User(String username, String password, String email,int age) throws InvalidDataException {
		this.setAge(age);
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
	}
	//constructor with id
	public User(long id, String username, String password, String email,int age) throws InvalidDataException {
		this(username, password, email, age);
		this.setId(id);
	}
	
	//====getters
	public long getId() {
		return id;
	}
	public int getAge() {
		return age;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	
	//=====setters
	public void setId(long id) {
		this.id = id;
	}
	public void setAge(int age) throws InvalidDataException{
		if(age<14 || age>100) {
			throw new InvalidDataException("The age must be a number between 14 and 100.");
		}
		this.age = age;
	}
	public void setUsername(String username) throws InvalidDataException{
		if(username==null || username.isEmpty()) {
			throw new InvalidDataException("Username cannot be null or empty.");
		}
		this.username = username;
	}
	public void setPassword(String password) throws InvalidDataException {
		if(password==null || password.isEmpty()) {
			throw new InvalidDataException("Password cannot be null or empty.");
		}
		this.password = password;
	}
	public void setEmail(String email)throws InvalidDataException {
		if(email==null || email.isEmpty()) {
			throw new InvalidDataException("Email cannot be null or empty.");
		}
		this.email = email;
	}
	
	
	
}
