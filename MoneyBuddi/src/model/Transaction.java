package model;

import java.time.LocalDateTime;

import exceptions.InvalidDataException;

public abstract class Transaction {
	
	
	
	
	public enum TransactionType{//finance_operation_type
		INCOME,EXPENSE;
	}
	
	
	
	private int id;
	private double amount;
	private Currency currency;
	private Account account;
	private LocalDateTime date;
	private Category category;
	private String description;//not obligatory?
	private TransactionType type;
	
	
	
	
	public Transaction(int id, double amount, Currency currency, Account account, LocalDateTime date, Category category,
			TransactionType type) throws InvalidDataException {
		this(amount,currency,account,date,category,type);
		this.setId(id);
		
		
	}
	
	

	public Transaction(double amount, Currency currency, Account account, LocalDateTime date, Category category,
			TransactionType type) throws InvalidDataException {
		
		this.setAmount(amount);
		this.setCurrency(currency);
		this.setAccount(account);
		this.setDate(date);
		this.setCategory(category);
		this.setType(type);
	}
	
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if(description.trim().equals("")) {
			//TODO
		}
		this.description = description;
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
	
	
	
	public void setAmount(double amount) throws InvalidDataException {
		if(amount<=0) {
			throw new InvalidDataException("Transaction amount can'b be negative");
		}
		this.amount = amount;
	}
	
	
	
	public Currency getCurrency() {
		return currency;
	}
	
	
	
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	
	
	public Account getAccount() {
		return account;
	}
	
	
	
	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	
	public LocalDateTime getDate() {
		return date;
	}
	
	
	
	public void setDate(LocalDateTime date) {
		this.date = date;
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
	
	
	
	public TransactionType getType() {
		return type;
	}
	
	
	
	public void setType(TransactionType type) {
		this.type = type;
	}
	
	
	
	

}
