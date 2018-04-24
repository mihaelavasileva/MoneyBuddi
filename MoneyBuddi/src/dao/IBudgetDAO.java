package dao;

import java.sql.SQLException;
import java.util.Collection;

import model.Budget;
import model.User;

public interface IBudgetDAO {
	
	void addBudget(Budget budget) throws SQLException;
	
	void updateBudget(Budget budget) throws SQLException;
	
	void deleteBudget(Budget budget) throws SQLException;
	
	Budget getBudgetById(long id)throws Exception;
	
	Collection <Budget> getAllBudgetsForUser(User user) throws Exception;

}
