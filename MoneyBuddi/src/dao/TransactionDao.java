package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import controller.manager.DBManager;
import exceptions.InvalidDataException;
import model.Account;
import model.Expense;
import model.Income;
import model.Transaction;
import model.Transaction.TransactionType;
import model.User;

public class TransactionDao implements ITransactionDao{

	private static TransactionDao instance;
	private Connection connection;

	public synchronized static TransactionDao getInstance() {
		if (instance == null) {
			instance = new TransactionDao();
		}
		return instance;
	}

	private TransactionDao() {
		connection = DBManager.getInstance().getConnection();
	}
	
	@Override
	       
	public synchronized void addTransaction(Transaction transaction) throws SQLException {
		try {
			connection.setAutoCommit(false);
		PreparedStatement s = connection.prepareStatement(
				"INSERT INTO transactions (amount, date, currency_id,"
				+ "account_id, category_id,transaction_type_id) "
				+ "VALUES (?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
		
		
		s.setDouble(1, transaction.getAmount());
		s.setDate(2, Date.valueOf(LocalDate.now()));
		s.setInt(3, transaction.getCurrency().getId());
		s.setInt(4, transaction.getAccount().getId());
		s.setInt(5, transaction.getCategory().getId());
		s.setInt(6, TransactionTypeDAO.getInstance().getIdByTranscationType(transaction.getType()));

		int rows = s.executeUpdate();
		if (rows == 0) {
			// if transaction is not inserted, throw exception
			throw new SQLException("Transaction was not inserted in DB.");
		}

		// retrieve transaction`s id
		ResultSet generatedKey = s.getGeneratedKeys();
		generatedKey.next();
		transaction.setId((int)generatedKey.getLong(1));
		
		
		Account acc=transaction.getAccount();
		if(transaction.getType().equals(TransactionType.EXPENSE)) {
			acc.setBalance(acc.getBalance()-transaction.getAmount());
		}else if(transaction.getType().equals(TransactionType.INCOME)) {
			acc.setBalance(acc.getBalance()+transaction.getAmount());
		}
		AccountDao.getInstance().updateAccount(acc);//not 100% if this works 
		connection.commit();
		
		s.close();
		System.out.println("Transaction successfully added in DB");
		}catch(SQLException e) {
			connection.rollback();
			throw new SQLException("Srry transaction can't be executed");
		}finally {
			connection.setAutoCommit(true);
		}
		
	}

	@Override
	public void deleteTransaction(Transaction transaction) throws SQLException {
		PreparedStatement s = null;
		try {
			s = connection.prepareStatement("DELETE FROM transactions WHERE id=?");
			s.setInt(1, transaction.getId());
			s.executeUpdate();
		} catch (Exception e) {
			throw new SQLException("Could not delete transaction.");
		} finally {
			s.close();
		}

		System.out.println("Deleted transaction from DB. ");
	}

	@Override
	public void changeTransaction(Transaction transaction) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("UPDATE transactions SET amount=?, date=?,"
					+ "currency_id=?, account_id=?, category_id=?, transaction_type_id=? "
					+ "WHERE id=?");
			ps.setDouble(1, transaction.getAmount());
			ps.setDate(2, transaction.getDate());
			ps.setInt(3, transaction.getCurrency().getId());
			ps.setInt(4, transaction.getAccount().getId());
			ps.setInt(5, transaction.getCategory().getId());
			ps.setInt(6, transaction.getType().getId());
			ps.setInt(7, transaction.getId());

			ps.executeUpdate();
		} catch (Exception e) {
			throw new SQLException("Could not update transaction.");
		} finally {
			ps.close();
		}

		System.out.println("Transaction updated.");
	}

	@Override
	public ArrayList<Transaction> getAllTransactionsByUser(User u) throws SQLException, InvalidDataException {
		ArrayList<Transaction> transactions=new ArrayList();
		try(PreparedStatement ps=connection.prepareStatement("SELECT id,amount,date,currency_id,account_id,category_id,"
															+"transaction_type_id FROM transactions " 
															+"Where account_id in(select id from accounts where user_id=?)")){
			ps.setInt(1, u.getId());
			try(ResultSet rs=ps.executeQuery()){
				while(rs.next()) {
					if(TransactionTypeDAO.getInstance().getTypeById(rs.getInt("transaction_type_id")).equals(TransactionType.EXPENSE)) {
					transactions.add(    new Expense(rs.getInt("id"),
							                         rs.getDouble("amount"),
							                         CurrencyDAO.getInstance().getCurrencyById(rs.getInt("currency_id")),
							                         AccountDao.getInstance().getAccountById(rs.getInt("account_id")),
							                         rs.getDate("date").toLocalDate(),
							                         CategoryDAO.getInstance().getCategoryByID(rs.getInt("category_id"))));
					}else if(TransactionTypeDAO.getInstance().getTypeById(rs.getInt("transaction_type_id")).equals(TransactionType.INCOME)){
						transactions.add(    new Income(rs.getInt("id"),
		                                                rs.getDouble("amount"),
		                                                CurrencyDAO.getInstance().getCurrencyById(rs.getInt("currency_id")),
		                                                AccountDao.getInstance().getAccountById(rs.getInt("account_id")),
		                                                rs.getDate("date").toLocalDate(),
		                                                CategoryDAO.getInstance().getCategoryByID(rs.getInt("category_id"))));
						
					}
				}
			}
			
			
		}
		return transactions;
	}

}
