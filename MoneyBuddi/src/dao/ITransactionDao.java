package dao;

import java.sql.SQLException;

import model.Transaction;
import model.User;

public interface ITransactionDao {

	void addTransaction(Transaction transaction) throws SQLException;
	void deleteTransaction(Transaction transaction) throws SQLException;
	void changeTransaction(Transaction transaction) throws SQLException;
}
