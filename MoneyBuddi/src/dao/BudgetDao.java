package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import exceptions.CannotInsertUserInDBException;
import model.Budget;

public class BudgetDao implements IBudgetDAO{
	
	
	private static BudgetDao instance;
	private Connection connection;

	public synchronized static BudgetDao getInstance() {
		if (instance == null) {
			instance = new BudgetDao();
		}
		return instance;
	}

	private BudgetDao() {
		//connection = DBManager.getInstance().getConnection();
	}

	@Override
	public void addBudget(Budget budget) throws SQLException  {
		
		PreparedStatement ps=connection.prepareStatement("INSERT INTO budgets (amount,begin_date,end_date,user_id,currency_id,category_id)"
		+ "VALUES(?,?,?,?,?,?)");
		ps.setDouble(1, budget.getAmount());
		ps.setDate(2, Date.valueOf(budget.getBeginDate()));
		ps.setDate(3, Date.valueOf(budget.getEndDate()));
		ps.setInt(4, (int)budget.getUser().getId());
		ps.setInt(5, budget.getCurrency().getId());
		ps.setInt(6,budget.getCategory().getId());
		
		int rows=0;
		ps.executeUpdate();
		if(rows==0) {
			//if user is not inserted, throw exception
			throw new SQLException("Cannot add budget in DB");
		}
		
		
	}
	
	
	

}
