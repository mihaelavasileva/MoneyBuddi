package dao;

import java.sql.SQLException;

import model.Transaction.TransactionType;

public interface ITransactionTypeDAO {
	
	 TransactionType getTypeById(int id) throws SQLException;
	 
	 int getIdByTranscationType(TransactionType t) throws SQLException;

}
