package model;

import java.time.LocalDateTime;

import exceptions.InvalidDataException;

public class Expense extends Transaction {

	//constructor without id
	public Expense(double amount, Currency currency, Account account, LocalDateTime date, Category category) throws InvalidDataException {
		super(amount, currency, account, date, category,TransactionType.EXPENSE);
		
	}
	
	//constructor with id
	public Expense(int id,double amount, Currency currency, Account account, LocalDateTime date, Category category) throws InvalidDataException {
		super(id,amount, currency, account, date, category,TransactionType.EXPENSE);
		
	}

}
