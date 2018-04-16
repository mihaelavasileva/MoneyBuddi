package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Transaction.TransactionType;

public class FinanceOperationtTypeDAO implements IFinanceOperationTypeDAO{
	
	
	private static FinanceOperationtTypeDAO instance;
	private Connection connection;

	public synchronized static FinanceOperationtTypeDAO getInstance() {
		if (instance == null) {
			instance = new FinanceOperationtTypeDAO();
		}
		return instance;
	}

	private FinanceOperationtTypeDAO() {
		//connection = DBManager.getInstance().getConnection();
	}
	

	@Override
	public TransactionType getTypeById(int id) throws SQLException {
		
		try(PreparedStatement ps=connection.prepareStatement("SELECT id,name FROM finance_operation_types WHERE id=?");){
			ps.setInt(1, id);
			try(ResultSet rs=ps.executeQuery()){
				rs.next();
				if(rs.getInt(1)==1) {//if there is such a row
					String type_name=rs.getString("name");
					for(TransactionType type:TransactionType.values()) {
								//checking if there is an enum which 
                        		//string value equals the type from the table in DB
								//because type in table is String whereas in class is enum
						if(type_name.equals(type.toString())) {
							return type;
						}
					}
				}
			}
		}
		return null;
	}
	
	

}
