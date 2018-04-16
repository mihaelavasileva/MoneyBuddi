package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Account;
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
	public void addAccount(Account account, User u) throws SQLException {
		PreparedStatement s = null;
		try {
			s = connection.prepareStatement("INSERT INTO accounts (name, balance,"
					+ "user_id, currency_id)"
					+ "VALUES()");
			//TODO decide how to get currency_id!!!!
			s.executeUpdate();
		} finally {
			s.close();
		}

		System.out.println("Deleted user from DB. ");
	}

	@Override
	public void updateAccount(Account account) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAccount(Account account) throws SQLException {
		String sql="DELETE FROM accounts WHERE id=?";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setLong(1, account.getId());
		ps.executeUpdate();
	}

	@Override
	public Account getAccountByName(String name, User u) throws Exception {
		Account account = null;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("SELECT id,balance"
					+ "FROM accounts WHERE name=? AND user_id=?");
			ps.setString(1, name);
			ps.setLong(2, u.getId());
			
			ResultSet rs=ps.executeQuery();
			account=new Account(rs.getLong(1),
								name,
								rs.getDouble(2),
								u);
			
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
					+ "balance FROM accounts WHERE user_id=?");
			ps.setLong(1, u.getId());
			
			ResultSet result=ps.executeQuery();
			while(result.next()) {
				accounts.add(new Account(result.getLong(1),//add id to account
						                 result.getString(2),//name
						                 result.getDouble(3),//amount
						                 u));
			}
		}finally {
			ps.close();
		}
		
		return accounts;
	}

}
