package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import exceptions.CannotInsertUserInDBException;
import model.User;

public class UserDao implements IUserDao{

	private static UserDao instance;
	private Connection connection;

	public static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	private UserDao() {
		//connection = DBManager.getInstance().getConnection();
	}
	
	@Override
	public void saveUser(User u) throws Exception {
		PreparedStatement s = connection.prepareStatement("INSERT INTO users (username, password, email, age)"
				+ " VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
		s.setString(1, u.getUsername());
		s.setString(2, u.getPassword());
		s.setString(3, u.getEmail());
		s.setInt(4, u.getAge());
		
		int rows=s.executeUpdate();
		if(rows==0) {
			//if user is not inserted, throw exception
			throw new CannotInsertUserInDBException("User was not inserted in DB.");
		}
		
		 try (ResultSet generatedKeys = s.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                u.setId(generatedKeys.getLong(1));
	            }
	            else {
	                //throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
	}

	@Override
	public void deleteUser(User u) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User u) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUserById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUsername(String username) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
