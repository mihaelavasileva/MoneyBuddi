package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Transaction.TransactionType;

public class FinanceOperationTypeDAO implements IFinanceOperationTypeDAO{
	
	
	private static FinanceOperationTypeDAO instance;
	private Connection connection;

	public synchronized static FinanceOperationTypeDAO getInstance() {
		if (instance == null) {
			instance = new FinanceOperationTypeDAO();
		}
		return instance;
	}

	private FinanceOperationTypeDAO() {
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

	@Override
	public int getIdByTranscationType(TransactionType t) throws SQLException {
		
		try(PreparedStatement ps=connection.prepareStatement("SELECT id FROM operation_types WHERE name=?")){
			ps.setString(1, t.toString());
			try(ResultSet rs=ps.executeQuery()){
				rs.next();
				if(rs.getInt(1)==1) {
					return rs.getInt("id");
				}
			}
		}
		return 0;
	}
	
	

}
