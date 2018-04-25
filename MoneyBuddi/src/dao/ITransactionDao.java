package dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import exceptions.InvalidDataException;
import model.Account;
import model.Budget;
import model.Transaction;
import model.User;

public interface ITransactionDao {
                                                         
	void addTransaction(Transaction transaction,Budget budget) throws SQLException;//budget is optional
	void deleteTransaction(Transaction transaction) throws SQLException;
	void changeTransaction(Transaction transaction) throws SQLException;
	Collection<Transaction> getAllTransactionsByUser(User u) throws SQLException,InvalidDataException;
	ArrayList<Transaction> getAllIncomeTransactions(User u) throws Exception;
	ArrayList<Transaction> getAllExpenseTransactions(User u) throws Exception;
	ArrayList<Transaction> getAllTransactionsByUserFiltered(User u, int days) throws SQLException, InvalidDataException;
	ArrayList<Transaction> getAllTransactionsByUserAndDate(User u, LocalDate date)throws SQLException, InvalidDataException;
	ArrayList<Transaction> getIncomeForMonth(User u) throws Exception;
	ArrayList<Transaction> getExpenseForMonth(User u) throws Exception;
}
