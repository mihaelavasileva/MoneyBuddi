package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Currency;
import model.User;

public class AccountDao implements IAccountDao {

	private static AccountDao instance;
	private Connection connection;

	public static AccountDao getInstance() {
		if (instance == null) {
			instance = new AccountDao();
		}
		return instance;
	}

	private AccountDao() {
		// connection = DBManager.getInstance().getConnection();
	}
	
	@Override
	public void addAccount(Account account) throws SQLException {
		PreparedStatement s = null;
		try {
			s = connection.prepareStatement("INSERT INTO accounts (name, balance,"
					+ "user_id, currency_id)"
					+ "VALUES(?,?,?,?)");
			s.setString(1, account.getName());
			s.setDouble(2, account.getBalance());
			s.setLong(3, account.getUser().getId());
			s.setLong(4, account.getCurrencyId());
			s.executeUpdate();
		} finally {
			s.close();
		}

		System.out.println("Account added in DB. ");
	}

	@Override
	public void updateAccount(Account account) throws SQLException {
		PreparedStatement ps=connection.prepareStatement("UPDATE accounts SET"
				+ "name=?, balance=?, user_id=?, currency_id=?"
				+ "WHERE id=?");
		ps.setString(1, account.getName());
		ps.setDouble(2, account.getBalance());
		ps.setInt(3, account.getUser().getId());
		ps.setInt(4, account.getCurrencyId());
		ps.setInt(5, account.getId());
	}

	@Override
	public void deleteAccount(Account account) throws SQLException {
		String sql="DELETE FROM accounts WHERE id=?";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setLong(1, account.getId());
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public Account getAccountByName(String name, User u) throws Exception {
		Account account = null;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("SELECT id,name,balance"
					+ "user_id, currency_id"
					+ "FROM accounts WHERE name=? AND user_id=?");
			ps.setString(1, name);
			ps.setLong(2, u.getId());
			
			ResultSet rs=ps.executeQuery();
			account=new Account(rs.getInt(1),//account id
								rs.getString(2),//account name
								rs.getDouble(3),//account balance
								rs.getInt(4),//user_id
								rs.getInt(5));//currency_id
			
		} finally {
			ps.close();
		}
		return account;
	}

	@Override
	public List<Account> getAllAccountsForUser(User u) throws Exception {
		ArrayList<Account> accounts=new ArrayList<>();
		PreparedStatement ps=null;
		try {
			ps=connection.prepareStatement("SELECT id, name"
					+ "balance,user_id, currency_id FROM accounts WHERE user_id=?");
			ps.setLong(1, u.getId());
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				accounts.add(new Account(rs.getInt(1),//account id
										 rs.getString(2),//account name
										 rs.getDouble(3),//account balance
										 rs.getInt(4),//user_id
									   	 rs.getInt(5)));//currency_id
			}
		}finally {
			ps.close();
		}
		
		return accounts;
	}

}
