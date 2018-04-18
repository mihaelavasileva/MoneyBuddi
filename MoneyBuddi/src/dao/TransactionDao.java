package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import controller.manager.DBManager;
import model.Transaction;

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
	public void addTransaction(Transaction transaction) throws SQLException {
		PreparedStatement s = connection.prepareStatement(
				"INSERT INTO transactions (amount, date, currency_id,"
				+ "account_id, category_id,transaction_type_id"
				+ "VALUES (?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
		s.setDouble(1, transaction.getAmount());
		s.setDate(2, Date.valueOf(LocalDate.now()));
		s.setInt(3, transaction.getCurrency().getId());
		s.setInt(4, transaction.getAccount().getId());
		s.setInt(5, transaction.getCategory().getId());
		s.setInt(6, transaction.getType().getId());

		int rows = s.executeUpdate();
		if (rows == 0) {
			// if user is not inserted, throw exception
			throw new SQLException("Transaction was not inserted in DB.");
		}

		// retrieve user`s id
		ResultSet generatedKey = s.getGeneratedKeys();
		generatedKey.next();
		transaction.setId((int)generatedKey.getLong(1));

		s.close();
		System.out.println("Transaction successfully added in DB");
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
					+ "currency_id=?, account_id=?, category_id=?, transaction_type_id=?"
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

}
