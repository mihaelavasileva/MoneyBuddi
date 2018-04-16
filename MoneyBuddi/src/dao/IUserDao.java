package dao;

import model.User;

public interface IUserDao {

	void saveUser(User u) throws Exception;
	void deleteUser(User u) throws Exception;
	void updateUser(User u) throws Exception;
	User getUserById(int id) throws Exception;
	User getUserByUsername(String username) throws Exception;
}
