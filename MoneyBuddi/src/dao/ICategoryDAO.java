package dao;

import java.sql.SQLException;
import java.util.Collection;

import exceptions.InvalidDataException;
import model.Category;
import model.Transaction.TransactionType;
import model.User;

public interface ICategoryDAO {
	
	
	void addCategory(Category category) throws SQLException;
	
	void deleteCategory(long id) throws SQLException; //User can only delete categories that he created and not the default ones
	
	Category getCategoryByID(long id) throws SQLException,InvalidDataException;
	
	Collection<Category> getAllCategoriesByUser(User user) throws SQLException,InvalidDataException;
	
	Collection<Category> getAllCategoriesByUserAndType(User user,TransactionType type) throws SQLException,InvalidDataException;
	
	
	

}
