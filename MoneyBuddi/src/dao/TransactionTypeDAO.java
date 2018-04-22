package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.manager.DBManager;
import model.Transaction.TransactionType;

public class TransactionTypeDAO implements ITransactionTypeDAO{
	
	
	private static TransactionTypeDAO instance;
	private Connection connection;

	public synchronized static TransactionTypeDAO getInstance() {
		if (instance == null) {
			instance = new TransactionTypeDAO();
		}
		return instance;
	}

	private TransactionTypeDAO() {
		connection = DBManager.getInstance().getConnection();
	}
	

	@Override
	public TransactionType getTypeById(int id) throws SQLException {
		
		try(PreparedStatement ps=connection.prepareStatement("SELECT id,name FROM transaction_types WHERE id=?");){
			ps.setInt(1, id);
			try(ResultSet rs=ps.executeQuery()){
				
				if(rs.next()) {//if there is such a row
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
		String type=t.toString();
		try(PreparedStatement ps=connection.prepareStatement("SELECT id FROM transaction_types WHERE name=?")){
			ps.setString(1, type);
			
			try(ResultSet rs=ps.executeQuery()){
				if(rs.next()) {
					return rs.getInt("id");
				}
					
			}
		}
		return 0;
	}

	@Override
	public List<TransactionType> getAllTransactionTypes() throws SQLException {
         List<TransactionType> types=new ArrayList();
         try(PreparedStatement ps=connection.prepareStatement("SELECT name FROM transaction_types")){
        	 try(ResultSet rs=ps.executeQuery()){
        		 while(rs.next()) {
        			 String type_name=rs.getString("name");
 					for(TransactionType type:TransactionType.values()) {
 								
 						if(type_name.equals(type.toString())) {//in case there there is a name in DB that does not 
 							types.add(type);				   // have an enum representation
 						}
 					}
        			 
        		 }
        	 }
        	 	 
         }
       return types;
    }
	
	

}
