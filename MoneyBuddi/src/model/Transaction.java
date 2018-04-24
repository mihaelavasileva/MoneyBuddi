package model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import exceptions.InvalidDataException;

public abstract class Transaction {

	public enum TransactionType{
		//transaction_type, transaction is income or expense
		INCOME(1),EXPENSE(2);
		private int id;
		private TransactionType(int id) {
			this.id=id;
		}
		public int getId() {
			return id;
		}
	}
	
	private long id;
	private double amount;
	private Currency currency;
	private Account account;
	private LocalDate date;
	private Category category;
	private TransactionType type;
	
	public Transaction(double amount, Currency currency, Account account, LocalDate date2, 
			Category category, TransactionType type) throws InvalidDataException {
		this.setAmount(amount);
		this.setCurrency(currency);
		this.setAccount(account);
		this.setDate(date2);
		this.setCategory(category);
		this.setType(type);
	}
	
	public Transaction(long id, double amount, Currency currency, Account account, 
			LocalDate date, Category category, TransactionType type) throws InvalidDataException {
		this(amount,currency,account,date,category,type);
		this.setId(id);
	}

	//=====getters

	public TransactionType getType() {
		return type;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public Date getDate() {
		return Date.valueOf(date);
	}
	
	public Account getAccount() {
		return account;
	}

	public long getId() {
		return id;
	}
	
	public Currency getCurrency() {
		return currency;
	}
	
	public double getAmount() {
		return amount;
	}
	
	//======setters
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setAmount(double amount) throws InvalidDataException {
		if(amount<=0) {
			throw new InvalidDataException("Transaction amount can't be negative number");
		}
		this.amount = amount;
	}
	
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	public void setAccount(Account account) throws InvalidDataException {
		if(account==null) {
			throw new InvalidDataException ("Srry account can't be null");
		}
		this.account = account;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public void setCategory(Category category) throws InvalidDataException {
		if(category==null) {
			throw new InvalidDataException("Category cant be null");
		}
		this.category = category;
	}
	
	public void setType(TransactionType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return id+" "+type+" "+ amount+" "+currency.getType()+" "+account.getName()+" "+date+" "+category.getCategory();
	}
	
	
}
