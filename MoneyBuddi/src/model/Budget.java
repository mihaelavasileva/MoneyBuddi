package model;

import java.time.LocalDate;

import exceptions.InvalidDataException;

public class Budget {
	
	private long id;
	private double amount;
	private User user;
	private Category category;
	private Currency currency;
	private LocalDate beginDate;
	private LocalDate endDate;
	
	
	//TODO ADD validations in setters 
	
	
	public Budget(long id,Category category,double amount, User user, Currency currency, LocalDate beginDate, LocalDate endDate) throws InvalidDataException {
		this(category,amount, user, currency, beginDate, endDate);
		this.setId(id);
		
	}
	
	public Budget(Category category,double amount, User user, Currency currency, LocalDate beginDate, LocalDate endDate) throws InvalidDataException {
		this.setCategory(category);
		this.setAmount(amount);
		this.setUser(user);
		this.setCurrency(currency);
		this.setBeginDate(beginDate);
		this.setEndDate(endDate);
		if(this.getEndDate().isBefore(this.beginDate)) {
			throw new InvalidDataException("Begin date cannot be after end date");
		}
	}



	public Category getCategory() {
		return category;
	}
	

	public void setCategory(Category category) throws InvalidDataException {
		if(category==null) {
			throw new InvalidDataException("Category cant be null");
		}
		this.category = category;
	}
	

	public long getId() {
		return id;
	}
	

	public void setId(long id) {
		this.id = id;
	}
	
	
	public double getAmount() {
		return amount;
	}
	
	
	public void setAmount(double amount) throws InvalidDataException {
		if(amount<=0) {
		  throw new InvalidDataException("Budget amount can't be negative");
		}
		this.amount = amount;
	}
	
	
	public User getUser() {
		return user;
	}
	
	
	
	public void setUser(User user) throws InvalidDataException {
		if(user==null) {
			throw new InvalidDataException("User cannot be null");
		}
		this.user = user;
	}
	
	
	public Currency getCurrency() {
		return currency;
	}
	
	
	
	public void setCurrency(Currency currency) throws InvalidDataException {
		if(currency==null) {
			throw new InvalidDataException("Currency cant be null");
		}
		this.currency = currency;
	}
	
	
	public LocalDate getBeginDate() {
		return beginDate;
	}
	
	
	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}
	
	
	public LocalDate getEndDate() {
		return endDate;
	}
	
	
	
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
}
