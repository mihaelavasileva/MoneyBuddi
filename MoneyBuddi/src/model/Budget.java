package model;

import java.time.LocalDate;

public class Budget {
	
	
	private int id;
	
	private double amount;
	private User user;
	private Currency currency;
	private LocalDate beginDate;
	private LocalDate endDate;
	
	
	//TODO ADD validations in setters 
	
	
	public Budget(int id,double amount, User user, Currency currency, LocalDate beginDate, LocalDate endDate) {
		this(amount, user, currency, beginDate, endDate);
		this.setId(id);
		
	}
	
	public Budget(double amount, User user, Currency currency, LocalDate beginDate, LocalDate endDate) {
		this.setAmount(amount);
		this.setUser(user);
		this.setCurrency(currency);
		this.setBeginDate(beginDate);
		this.setEndDate(endDate);
		if(this.getEndDate().isBefore(this.beginDate)) {
			//TODO 
		}
	}



	public int getId() {
		return id;
	}
	

	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public double getAmount() {
		return amount;
	}
	
	
	public void setAmount(double amount) {
		if(amount<=0) {
			//TODO 
		}
		this.amount = amount;
	}
	
	
	public User getUser() {
		return user;
	}
	
	
	
	public void setUser(User user) {
		if(user==null) {
			//TODO
		}
		this.user = user;
	}
	
	
	public Currency getCurrency() {
		return currency;
	}
	
	
	
	public void setCurrency(Currency currency) {
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
