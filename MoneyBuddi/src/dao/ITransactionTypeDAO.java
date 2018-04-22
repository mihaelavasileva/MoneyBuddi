package dao;

import java.sql.SQLException;
import java.util.List;

import model.Transaction.TransactionType;

public interface ITransactionTypeDAO {
	
	 TransactionType getTypeById(int id) throws SQLException;
	 
	 int getIdByTranscationType(TransactionType t) throws SQLException;
	 
	 List<TransactionType> getAllTransactionTypes()throws SQLException;

}
