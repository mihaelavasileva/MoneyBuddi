package controller.manager;

import java.sql.SQLException;

import dao.TransactionDao;
import exceptions.InvalidDataException;
import model.Account;
import model.Budget;
import model.Transaction;
import model.Transaction.TransactionType;

public class TransactionManager {
	
	private static TransactionManager instance;
	
	public synchronized static TransactionManager getInstance() {
		
		if(instance==null) {
			instance=new TransactionManager();
		}
		return instance;
			
	}
	
	
	private TransactionManager() {
		
	}
	
	
	public void addTransaction(Transaction transaction,Budget budget) throws InvalidDataException, SQLException {
	
		
		double  accountAmount=CurrencyConverter.convert(
					transaction.getAmount(),
					transaction.getCurrency(), 
					transaction.getAccount().getCurrency()
					);
		double budgetAmount=0;
		 if(budget!=null) {
		 budgetAmount=CurrencyConverter.convert(
					transaction.getAmount(),
					transaction.getCurrency(), 
					budget.getCurrency()
					);
		 }
		 
		Account acc = transaction.getAccount();
		if (transaction.getType().equals(TransactionType.EXPENSE)) {
			
			acc.setBalance(acc.getBalance() - accountAmount);
			
			if(budget!=null) { 
				
				budget.setAmount(budget.getAmount()-budgetAmount);
			}
		} else if (transaction.getType().equals(TransactionType.INCOME)) {
			
				acc.setBalance(acc.getBalance() + accountAmount);
				
			if(budget!=null) {
			     
					 budget.setAmount(budget.getAmount()+budgetAmount);
				}
			
		  }
		
			TransactionDao.getInstance().addTransaction(transaction, budget);
			
			System.out.println("Transaction "+transaction.getCurrency().getType().toString()+" "+transaction.getAmount());
			System.out.println("Account " +transaction.getAccount().getCurrency().getType().toString()+" "+accountAmount);	
			if(budget!=null) {
		    System.out.println("Budget "+budget.getCurrency().getType().toString()+" "+budgetAmount);
			}
		
	}
	
	
	

}
