package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controller.manager.DBManager;
import exceptions.InvalidDataException;

import java.sql.Connection;

import model.User;
import util.security.BCrypt;

public class UserDao implements IUserDao {

	private static UserDao instance;
	private Connection connection;

	public static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	private UserDao() {
		connection = DBManager.getInstance().getConnection();
	}

	@Override
	public synchronized void saveUser(User u) throws SQLException {
		PreparedStatement s = connection.prepareStatement(
				"INSERT INTO users (username, " + "password, email, age)" 
		+ " VALUES (?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
		s.setString(1, u.getUsername());
		s.setString(2, u.getPassword());
		s.setString(3, u.getEmail());
		s.setInt(4, u.getAge());

		int rows = s.executeUpdate();
		if (rows == 0) {
			// if user is not inserted, throw exception
			throw new SQLException("User was not inserted in DB.");
		}

		// retrieve user`s id
		ResultSet generatedKey = s.getGeneratedKeys();
		generatedKey.next();
		u.setId(generatedKey.getLong(1));

		s.close();
		System.out.println("Saved user in DB");
	}

	@Override
	public void deleteUser(User u) throws SQLException {
		PreparedStatement s = null;
		try {
			s = connection.prepareStatement("DELETE FROM users WHERE id=?");
			s.setLong(1, u.getId());
			s.executeUpdate();
		} catch (Exception e) {
			throw new SQLException("Could not delete user.");
		} finally {
			s.close();
		}

		System.out.println("Deleted user from DB. ");
	}

	@Override
	public void updateUser(User u) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("UPDATE users SET username=?,"
					+ "password=?, email=?, age=? WHERE id=? ");
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getEmail());
			ps.setInt(4, u.getAge());
			ps.setLong(5, u.getId());

			ps.executeUpdate();
		} catch (Exception e) {
			throw new SQLException("Could not update user.");
		} finally {
			ps.close();
		}

		System.out.println("User updated.");
	}

	@Override
	public User getUserById(long id) throws SQLException {
		User user = null;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("SELECT username,password,"
					+ "email,age FROM users WHERE id=?");
			ps.setLong(1, id);
			ResultSet result = ps.executeQuery();
			result.next();
			user = new User(id, result.getString("username"), // get user`s username
					result.getString("password"), // get user`s password
					result.getString("email"), // get user`s email
					result.getInt("age"));// get user`s age
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("No user with id=" + id);
		} finally {
			ps.close();
		}
		return user;
	}

	@Override
	public User getUserByUsername(String username) throws SQLException {
		User user = null;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("SELECT id,password,"
					+ "email,age FROM users WHERE username=?");
			ps.setString(1, username);
			ResultSet result = ps.executeQuery();
			
			if(result.next()) {
			// create the user
			user = new User(result.getLong("id"),//get id
					username,
					result.getString("password"),//get password
					result.getString("email"),//get email
					result.getInt("age"));//get age
			}
		} catch (Exception e) {
			throw new SQLException("No user with username:" + username);
		} finally {
			ps.close();
		}
		return user;
	}

	@Override
	public boolean checkIfEmailExists(String email) throws SQLException {
		// checks if the email already exists in DB
		// returns number of affected rows from the query
		PreparedStatement ps = connection.prepareStatement("SELECT id FROM" + " users WHERE email=?");
		ps.setString(1, email);
		ResultSet rs= ps.executeQuery();
		if(rs.next()) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean validate(String username, String pass) throws SQLException {
		boolean status=false;
		PreparedStatement ps=null;
		try{  
			
			ps=connection.prepareStatement(  
			    "SELECT id FROM users WHERE username=? AND password=?");  
		
			ps.setString(1,username);  
			ps.setString(2, pass);  
			              
			ResultSet rs=ps.executeQuery();
		   
			status=rs.next();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception");
		}finally {
			ps.close();
		}
		  
		return status;
	}
	
	@Override
	public User getUserByUsernameAndPassword(String pass, String username) throws SQLException,
	InvalidDataException {
		String sql = "SELECT id, username, password, email, age FROM users WHERE username = ?";
				
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, username);
		
		ResultSet result = ps.executeQuery();
		if(result.next()) {
			String hashed=result.getString("password");
			if(BCrypt.checkpw(pass, hashed)) {
			return new User(result.getLong("id"),
					result.getString("username"),
					result.getString("password"),
					result.getString("email"),
					result.getInt("age"));
				}
			}
		return null;
	}
	
}
