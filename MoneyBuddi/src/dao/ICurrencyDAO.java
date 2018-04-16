package dao;

import java.sql.SQLException;

import model.Currency;


public interface ICurrencyDAO {
	
	Currency getCurrencyById(int id) throws SQLException;

}
