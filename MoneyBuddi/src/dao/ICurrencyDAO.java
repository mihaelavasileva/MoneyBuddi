package dao;

import java.sql.SQLException;

import model.Currency;
import model.Currency.CurrencyType;


public interface ICurrencyDAO {
	
	Currency getCurrencyById(int id) throws SQLException;
	
	Currency getCurrencyByType(CurrencyType type) throws SQLException;

}
