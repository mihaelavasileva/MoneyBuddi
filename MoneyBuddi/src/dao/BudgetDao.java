package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import controller.manager.DBManager;
import exceptions.InvalidDataException;
import model.Budget;
import model.User;

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
		connection = DBManager.getInstance().getConnection();
	}

	@Override
	public void addBudget(Budget budget) throws SQLException  {
		
		try(PreparedStatement ps=connection.prepareStatement("INSERT INTO budgets (amount,begin_date,end_date,user_id,currency_id,category_id)"
		+ "VALUES(?,?,?,?,?,?)");){
		ps.setDouble(1, budget.getAmount());
		ps.setDate(2, Date.valueOf(budget.getBeginDate()));
		ps.setDate(3, Date.valueOf(budget.getEndDate()));
		ps.setLong(4, budget.getUser().getId());
		ps.setLong(5, budget.getCurrency().getId());
		ps.setLong(6,budget.getCategory().getId());
		
		
		int rows=ps.executeUpdate();
		if(rows==0) {
			
			throw new SQLException("Cannot add budget in DB");
			}
		
		}	
	}

	@Override
	public void updateBudget(Budget budget) throws SQLException {
		try(PreparedStatement ps=connection.prepareStatement("UPDATE budgets SET amount=?,begin_date=?,end_date=?,user_id=?,currency_id=?,category_id=? "
	    +"WHERE id=?");){
		ps.setDouble(1, budget.getAmount());
		ps.setDate(2, Date.valueOf(budget.getBeginDate()));
		ps.setDate(3, Date.valueOf(budget.getEndDate()));
		ps.setLong(4, budget.getUser().getId());
		ps.setLong(5, budget.getCurrency().getId());
		ps.setLong(6, budget.getCategory().getId());
		ps.setLong(7, budget.getId());
		
		int rows=ps.executeUpdate();
		if(rows==0) {
			
			throw new SQLException("Cannot update budget in DB");
				}
		}
		
	}

	@Override
	public void deleteBudget(Budget budget) throws SQLException {
		
		try(PreparedStatement ps=connection.prepareStatement("DELETE FROM budgets WHERE id=?");){
		ps.setLong(1, budget.getId());
		
		int rows=ps.executeUpdate();
		if(rows==0) {
			throw new SQLException("Budget cannot be deleted from DB");
				}	
		
		}
	}

	@Override
	public Collection<Budget> getAllBudgetsForUser(User user) throws SQLException,InvalidDataException {//can throw SQL OR INVALID DATA EXCEPTION
		Collection<Budget> budgets=new ArrayList<>();
		try(PreparedStatement ps=connection.prepareStatement("SELECT id , amount , begin_date, "
				                                             +"end_date , user_id , currency_id , category_id "
				                                             + "FROM budgets WHERE user_id=?");){
			ps.setInt(1,(int) user.getId());
			try(ResultSet rs=ps.executeQuery()){
				while(rs.next()) {
					budgets.add(new Budget(rs.getLong("id"),
							               CategoryDAO.getInstance().getCategoryByID(rs.getLong("category_id")), 
							               rs.getDouble("amount"), 
							               UserDao.getInstance().getUserById(rs.getLong("user_id")), 
							               CurrencyDAO.getInstance().getCurrencyById(rs.getLong("currency_id")),
							               rs.getDate("begin_date").toLocalDate(), 
							               rs.getDate("end_date").toLocalDate()));
					
					                        
					
				}
			}
		}
		return budgets;
	}

	@Override
	public Budget getBudgetById(long id) throws Exception {
		Budget b=null;
		try(PreparedStatement ps=connection.prepareStatement("SELECT id , amount , begin_date,"
								                             + "end_date , user_id , currency_id , category_id " 
								                             + "FROM budgets WHERE id=?" )){
			ps.setLong(1, id);
			try(ResultSet rs=ps.executeQuery()){
				if(rs.next()) {
					b=new Budget(rs.getLong("id"),
				               CategoryDAO.getInstance().getCategoryByID(rs.getLong("category_id")), 
				               rs.getDouble("amount"), 
				               UserDao.getInstance().getUserById(rs.getLong("user_id")), 
				               CurrencyDAO.getInstance().getCurrencyById(rs.getLong("currency_id")),
				               rs.getDate("begin_date").toLocalDate(), 
				               rs.getDate("end_date").toLocalDate());
					return b;
				}
			}
		}
		return null;
	}
	
	
	
	
	
	
	
	
	

}
