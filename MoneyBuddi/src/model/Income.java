package model;

import java.time.LocalDateTime;

import exceptions.InvalidDataException;

public class Income extends Transaction{

	public Income(int id, double amount, Currency currency, Account account, LocalDateTime date, Category category) throws InvalidDataException {
		super(id, amount, currency, account, date, category, TransactionType.INCOME);
		
	}
	
	public Income( double amount, Currency currency, Account account, LocalDateTime date, Category category) throws InvalidDataException {
		super(amount, currency, account, date, category, TransactionType.INCOME);
		
	}
	

}
