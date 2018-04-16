package model;

import java.time.LocalDateTime;

public class Expense extends Transaction {

	public Expense(double amount, Currency currency, Account account, LocalDateTime date, Category category) {
		super(amount, currency, account, date, category,TransactionType.EXPENSE);
		
	}
	
	public Expense(int id,double amount, Currency currency, Account account, LocalDateTime date, Category category) {
		super(id,amount, currency, account, date, category,TransactionType.EXPENSE);
		
	}

}
