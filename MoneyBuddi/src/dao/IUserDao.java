package dao;

import java.sql.SQLException;

import model.User;

public interface IUserDao {

	void saveUser(User u) throws SQLException;
	void deleteUser(User u) throws SQLException;
	void updateUser(User u) throws SQLException;
	User getUserById(long id) throws SQLException;
	User getUserByUsername(String username) throws SQLException;
	boolean checkIfEmailExists(String email) throws SQLException;
	boolean validate(String email, String pass) throws SQLException;
	User getUserByUsernameAndPassword(String pass, String username) throws Exception;
}
