package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import controller.manager.DBManager;
import model.Currency;
import model.Currency.CurrencyType;


public class CurrencyDAO implements ICurrencyDAO{
	
	private static CurrencyDAO instance;
	private Connection connection;

	public synchronized static CurrencyDAO getInstance() {
		if (instance == null) {
			instance = new CurrencyDAO();
		}
		return instance;
	}

	private CurrencyDAO() {
		connection = DBManager.getInstance().getConnection();
	}

	@Override
	public Currency getCurrencyById(long id) throws SQLException {
   
		try(PreparedStatement ps=connection.prepareStatement("SELECT id,type FROM currencies WHERE id=?")){
			ps.setLong(1, id);
			
			try(ResultSet rs=ps.executeQuery();){
				if(rs.next()) {//if there is such a row
					String currency_type=rs.getString("type");//String type from table currencies
					for(CurrencyType type:Currency.CurrencyType.values()) {
						if(type.toString().equals(currency_type)) {
											//checking if there is an enum which 
						                    //string value equals the type from the table in DB
						                    //because type in table is String whereas in class is enum
					   
						return new Currency(rs.getInt("id"), type);
						}
					}
					
				}
			}
		}
		return null;
		
	}

	@Override
	public Currency getCurrencyByType(CurrencyType type) throws SQLException {
		try(PreparedStatement ps=connection.prepareStatement("SELECT id,type FROM currencies WHERE type=?")){
			ps.setString(1, type.toString());
			
			try(ResultSet rs=ps.executeQuery();){
				if(rs.next()) {//if there is such a row

				  return new Currency(rs.getLong("id"),type);
						
				}
			}
		}
		return null;
		
	}

	@Override
	public Collection<Currency> getAllCurrencies() throws SQLException {
		Collection<Currency> currencies=new ArrayList();
		try(PreparedStatement ps=connection.prepareStatement("SELECT id,type FROM currencies ")){
		
			
			try(ResultSet rs=ps.executeQuery();){
				while(rs.next()) {
				   currencies.add(this.getCurrencyById(rs.getLong("id")));
			}
		}
		
		
	}
		return currencies;
	}
	
	
	

}
