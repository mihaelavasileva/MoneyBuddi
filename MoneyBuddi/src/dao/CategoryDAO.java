package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.InvalidDataException;
import model.Category;
import model.Transaction;

public class CategoryDAO implements ICategoryDAO{
	
	
	private static CategoryDAO instance;
	private Connection connection;

	public synchronized static CategoryDAO getInstance() {
		if (instance == null) {
			instance = new CategoryDAO();
		}
		return instance;
	}

	private CategoryDAO() {
		//connection = DBManager.getInstance().getConnection();
	}

	@Override
	public Category getCategoryByID(int id) throws SQLException, InvalidDataException {
		FinanceOperationtTypeDAO.getInstance().getTypeById(id);
		try(PreparedStatement ps=connection.prepareStatement("SELECT id,category,operation_type_id WHERE id=?");){
			ps.setInt(1, id);
			try(ResultSet rs=ps.executeQuery()){
				rs.next();
				if(rs.getInt(1)==1) {//if there is such a row
					return new Category(rs.getInt(id),
							rs.getString("category"),
							FinanceOperationtTypeDAO.getInstance().getTypeById(rs.getInt("operation_type_id"))
							);
				}
			}
			
		}
		return null;
	}
	
	

}
