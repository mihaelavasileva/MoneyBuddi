package dao;

import java.sql.SQLException;

import exceptions.InvalidDataException;
import model.Category;

public interface ICategoryDAO {
	
	Category getCategoryByID(int id) throws SQLException,InvalidDataException;

}
