package model;

import com.sun.media.sound.InvalidDataException;

public class Account {
	
	private int id;
	private String name;
	private double balance;
	private User user;
	private Currency currency;

	private Account(int id, String name, double balance)throws InvalidDataException {
		this.setId(id);
		this.setName(name);
		this.setBalance(balance);
	}
	public Account(int id, String name, double balance, User user, 
			Currency currency) throws InvalidDataException {
		this(id, name, balance);
		this.setUser(user);
		this.currency.setId(currency.getId());
	}
	
	public Account(int id, String name, double balance, int user_id, int currency_id)
			throws InvalidDataException {
		this(id, name, balance);
		this.user.setId(user_id);
		this.currency.setId(currency_id);
	}
	
	//====getters
	public double getBalance() {
		return balance;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public User getUser() {
		return user;
	}
	public int getCurrencyId() {
		return currency.getId();
	}
	//====setters
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public void setId(long id) {
		this.id = (int)id;
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
	public void setCurrencyId(int id) {
		this.currency.setId(id);;
	}
}
