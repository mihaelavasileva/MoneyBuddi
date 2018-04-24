package dao;

import java.sql.SQLException;
import java.util.Collection;

import model.Currency;
import model.Currency.CurrencyType;


public interface ICurrencyDAO {
	
	Currency getCurrencyById(long id) throws SQLException;
	
	Currency getCurrencyByType(CurrencyType type) throws SQLException;
	
	Collection<Currency> getAllCurrencies() throws SQLException;
	

}
