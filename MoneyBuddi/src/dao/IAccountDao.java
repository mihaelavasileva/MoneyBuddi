package dao;

import java.util.List;

import model.Account;

public interface IAccountDao {

	void addAccount(Account account) throws Exception;
	void updateAccount(Account account) throws Exception;
	void deleteAccount(Account account) throws Exception;
	Account getAccountByName(String name) throws Exception;
	List<Account> getAccountsForUserByUsername(String username) throws Exception;
}
