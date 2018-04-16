package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		//connection = DBManager.getInstance().getConnection();
	}

	@Override
	public Currency getCurrencyById(int id) throws SQLException {
   
		try(PreparedStatement ps=connection.prepareStatement("SELECT id,type FROM currencies WHERE id=?");){
			ps.setInt(1, id);
			try(ResultSet rs=ps.executeQuery();){
				rs.next();
				if(rs.getInt(1)==1) {//if there is such a row
					String currency_type=rs.getString("type");//String type from table currencies
					for(CurrencyType type:Currency.CurrencyType.values()) {
						if(type.toString().equals(currency_type));
											//checking if there is an enum which 
						                    //string value equals the type from the table in DB
						                    //because type in table is String whereas in class is enum
						return new Currency(rs.getInt("id"), type);
					}
					
				}
			}
		}
		return null;
		
	}
	
	
	

}
