package dao;

import java.sql.SQLException;

import model.Budget;

public interface IBudgetDAO {
	
	void addBudget(Budget budget) throws SQLException;

}
