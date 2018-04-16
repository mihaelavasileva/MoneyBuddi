package model;

import com.sun.media.sound.InvalidDataException;

public class Account {
	
	private long id;
	private String name;
	private double balance;
	private User user;

	public Account(long id, String name, double balance, User user) throws InvalidDataException {
		this.setId(id);
		this.setName(name);
		this.setBalance(balance);
		this.setUser(user);
	}
	
	//====getters
	public double getBalance() {
		return balance;
	}
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public User getUser() {
		return user;
	}
	//====setters
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setUser(User user) throws InvalidDataException {
		if(user == null) {
			throw new InvalidDataException("User cannot be null.");
		}
		this.user = user;
	}
	public void setName(String name) throws InvalidDataException {
		if(name==null || name.isEmpty()) {
			throw new InvalidDataException("The name of the transaction cannot be null or empty.");
		}
		this.name = name;
	}
}
