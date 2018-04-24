package dao;

import java.sql.SQLException;
import java.util.List;

import exceptions.InvalidDataException;
import model.Account;
import model.Currency;
import model.User;

public interface IAccountDao {

	void addAccount(Account account) throws SQLException;
	
	void updateAccount(Account account) throws SQLException;
	
	void deleteAccount(Account account) throws SQLException;
	
	Account getAccountById(long id) throws SQLException,InvalidDataException;
	
	Account getAccountByName(String name, User u) throws Exception;
	
	List<Account> getAllAccountsForUser(User u) throws Exception;
}
