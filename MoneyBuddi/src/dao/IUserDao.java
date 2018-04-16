package dao;

import java.sql.SQLException;

import model.User;

public interface IUserDao {

	void saveUser(User u) throws SQLException;
	void deleteUser(User u) throws SQLException;
	void updateUser(User u) throws SQLException;
	User getUserById(long id) throws SQLException;
	User getUserByUsername(String username) throws SQLException;
	int checkIfEmailExists(String email) throws SQLException;
}
