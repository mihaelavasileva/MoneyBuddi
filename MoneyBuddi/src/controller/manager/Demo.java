package controller.manager;

import java.sql.SQLException;
import java.time.LocalDate;

import dao.AccountDao;
import dao.BudgetDao;
import dao.CategoryDAO;
import dao.CurrencyDAO;
import dao.TransactionDao;
import dao.TransactionTypeDAO;
import dao.UserDao;
import exceptions.InvalidDataException;
import model.Account;
import model.Budget;
import model.Category;
import model.Currency;
import model.Currency.CurrencyType;
import model.Income;
import model.Transaction;
import model.Transaction.TransactionType;
import model.User;

public class Demo {
	
	
	public static void main(String[] args) throws Exception {
		
		//UserDAO 
		//   |
		//   v
		
		//User u=new User("Pesho","Pesho","Pesho", 20);
		//UserDao.getInstance().saveUser(u);									//WORKS
		//System.out.println(UserDao.getInstance().checkIfEmailExists("Lesho"));//WORKS
		//System.out.println(UserDao.getInstance().checkIfEmailExists("Pesho"));//WORKS
	    // User x=UserDao.getInstance().getUserById(3);							//WORKS
		//x.setAge(30);
		//User x=UserDao.getInstance().getUserByUsername("Pesho");			    //WORKS
		//UserDao.getInstance().deleteUser(x);                                  //WORKS
		//UserDao.getInstance().updateUser(x);								    //WORKS
		//UserDao.getInstance().getUserByUsernameAndPassword("Pesho", "Pesho"); //WORKS
		//System.out.println( UserDao.getInstance().validate("Pesho","Pesho")); //WORKS
		
		
		//CategoryDAO 
		//    |
	    //    v
		
		
		//Category category=new Category("bar", TransactionType.EXPENSE,3);
	
		//CategoryDAO.getInstance().addCategory(category);                      //WORKS
		
	   
		
		//for(Category y:CategoryDAO.getInstance().getAllCategoriesByUser(x)) {
		//	System.out.println(y.getCategory());
		//	System.out.println(y.getType());
		//}	
	    //WORKS
	    
	    //for(Category z: CategoryDAO.getInstance().getAllCategoriesByUserAndType(x, TransactionType.INCOME)) {
	    //	System.out.println(z.getCategory());
		//	System.out.println(z.getType());
	    //}
	    //WORKS
	    
	    //System.out.println( CategoryDAO.getInstance().getCategoryByID(2));    //WORKS
	    
	    //TransactionTypeDAO 
	  	//       |
	  	//       v
	    
	    //System.out.println(TransactionTypeDAO.getInstance().getIdByTranscationType(TransactionType.EXPENSE));//WORKS
	    //System.out.println(TransactionTypeDAO.getInstance().getIdByTranscationType(TransactionType.INCOME)); //WORKS
	    //System.out.println(TransactionTypeDAO.getInstance().getTypeById(2)); //WORKS
	    
	    //CurrencyDAO 
	  	//    |
	  	//    v
	    // System.out.println(CurrencyDAO.getInstance().getCurrencyById(1)); //WORKS
		// System.out.println(CurrencyDAO.getInstance().getCurrencyByType(CurrencyType.BGN));//WORKS
		/*System.out.println(CurrencyConverter.convert(20,CurrencyDAO.getInstance().
				getCurrencyByType(CurrencyType.BGN),CurrencyDAO.
				getInstance().getCurrencyByType(CurrencyType.RUB)));*///WORKS
		
		//BudgetDAO 
	  	//    |
	  	//    v
		
	    /* User user=UserDao.getInstance().getUserById(3);
	     Category cat=null;
	     
	     for(Category y:CategoryDAO.getInstance().getAllCategoriesByUser(user)) {
	    	 
	 			cat=y;
	 				
	 		}	
	    
		Currency currency=CurrencyDAO.getInstance().getCurrencyByType(Currency.CurrencyType.EUR);
		
		Budget b=new Budget(cat, 5000, user, currency, LocalDate.now(), LocalDate.now().plusDays(30));
		
		//BudgetDao.getInstance().addBudget(b);//WORKS
		
		Budget budget=null;
		for(Budget bud:BudgetDao.getInstance().getAllBudgetsForUser(user)) {//WORKS
			budget=bud;
		}
		budget.setAmount(300);
		BudgetDao.getInstance().updateBudget(budget);//WORKS
		BudgetDao.getInstance().deleteBudget(budget);//WORKS
		
		*/
	    
	    //AccountDAO 
	  	//    |
	  	//    v
	  
		//Account acc=new Account("Account16", 232312,
		//						  UserDao.getInstance().getUserById(3) ,
		//						  CurrencyDAO.getInstance().getCurrencyByType(CurrencyType.GBP));
		//AccountDao.getInstance().addAccount(acc);//WORKS
		//System.out.println(AccountDao.getInstance().getAccountById(acc.getId()));WORKS
		//acc.setBalance(500);
		//AccountDao.getInstance().updateAccount(acc);//WORKS
		
		//AccountDao.getInstance().deleteAccount(acc);//WORKS
		//Account account=AccountDao.getInstance().getAccountByName("Account14", UserDao.getInstance().getUserById(3));//WORKS
		//for(Account a:AccountDao.getInstance().getAllAccountsForUser(UserDao.getInstance().getUserById(3))) { //WORKS
		//	System.out.println(a.getName());
		//}
		
		//TransactionDAO
		//      | 
		//      V
		
		/* Income x=new Income(800,
				 			CurrencyDAO.getInstance().getCurrencyByType(CurrencyType.BGN),
				 			AccountDao.getInstance().getAccountById(1), 
				 			LocalDate.now(), 
				 			CategoryDAO.getInstance().getCategoryByID(12));
		 TransactionDao.getInstance().addTransaction(x);//WORKS*/
		
		/*
		 x.setAmount(200);
		 Thread.sleep(5000);
		 TransactionDao.getInstance().changeTransaction(x);//WORKS
		 Thread.sleep(5000);
		 TransactionDao.getInstance().deleteTransaction(x);//WORKS
		 */
		
		//for(Transaction t:TransactionDao.getInstance().getAllTransactionsByUser(UserDao.getInstance().getUserById(3))) {
		//	System.out.println(t);//WORKS
		//}
		
		//for(TransactionType t:TransactionTypeDAO.getInstance().getAllTransactionTypes()) {
		//	System.out.println(t);
		//}
			
	}

}
