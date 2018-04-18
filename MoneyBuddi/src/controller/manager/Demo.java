package controller.manager;

import java.sql.SQLException;

import dao.CategoryDAO;
import dao.CurrencyDAO;
import dao.TransactionDao;
import dao.TransactionTypeDAO;
import dao.UserDao;
import exceptions.InvalidDataException;
import model.Category;
import model.Transaction.TransactionType;
import model.User;

public class Demo {
	
	
	public static void main(String[] args) throws InvalidDataException, SQLException {
		
		//UserDAO 
		//   |
		//   v
		
		//User u=new User("Pesho","Pesho","Pesho", 20);
		//UserDao.getInstance().saveUser(u);									//WORKS
		//System.out.println(UserDao.getInstance().checkIfEmailExists("Lesho"));//WORKS
		//System.out.println(UserDao.getInstance().checkIfEmailExists("Pesho"));//WORKS
	    //User x=UserDao.getInstance().getUserById(3);							//WORKS
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
		//
	   
		
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
	    
	    //AccountDAO 
	  	//    |
	  	//    v
	    // next
	  		
		
	}

}
