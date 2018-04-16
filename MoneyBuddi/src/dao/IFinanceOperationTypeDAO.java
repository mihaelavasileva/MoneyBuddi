package dao;

import java.sql.SQLException;

import model.Transaction.TransactionType;

public interface IFinanceOperationTypeDAO {
	
	 TransactionType getTypeById(int id) throws SQLException;

}
