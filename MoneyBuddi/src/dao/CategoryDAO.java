package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import exceptions.InvalidDataException;
import model.Category;
import model.Transaction;
import model.Transaction.TransactionType;
import model.User;

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
															//could be done with join
		try(PreparedStatement ps=connection.prepareStatement("SELECT id,category,operation_type_id,user_id FROM categories "
														  + " WHERE id=?")){
			ps.setInt(1, id);
			try(ResultSet rs=ps.executeQuery()){
				rs.next();
				if(rs.getInt(1)==1) {//if there is such a row
					return new Category(rs.getInt(id),
							rs.getString("category"),								   //transaction type id
							FinanceOperationTypeDAO.getInstance().getTypeById(rs.getInt("operation_type_id")),
							rs.getInt("user_id")
							);
				}
			}
			
		}
		return null;
	}

	@Override
	public void addCategory(Category category) throws SQLException {
													  //could be done with join 		//transaction type id
		try(PreparedStatement ps=connection.prepareStatement("INSERT INTO categories (id,category,operation_type_id,user_id)"
															+"VALUES (?,?,?,?)");){
			ps.setInt(1, category.getId());
			ps.setString(2, category.getCategory());
			ps.setInt(3, FinanceOperationTypeDAO.getInstance().getIdByTranscationType(category.getType()));//
			int rows=ps.executeUpdate();
			if(rows==0) {
				throw new SQLException("Srry category cant be inserted ");
			}
		}
		
	}

	@Override
	public Collection<Category> getAllCategoriesByUser(User user) throws SQLException, InvalidDataException {
			 Collection<Category> categories=new ArrayList<>();	//JOIN maybe?//transaction type
		try(PreparedStatement ps=connection.prepareStatement("SELECT id,category,operation_type_id,user_id FROM categories"
				                                             +"WHERE user_id IS NULL or user_id=?" )){
			ps.setInt(1, (int) user.getId());//what if user id is null
			try(ResultSet rs=ps.executeQuery();){
				while(rs.next()) {
					categories.add(new Category(rs.getInt("id"),
												rs.getString("category"),
												FinanceOperationTypeDAO.getInstance().getTypeById(rs.getInt("operation_type_id")),
												rs.getInt("user_id")));
				}
			}
		}
		return categories;
	}

	@Override
	public Collection<Category> getAllCategoriesByUserAndType(User user, TransactionType type)
			throws SQLException, InvalidDataException {
		
		 Collection<Category> categories=new ArrayList<>();	
		 int id=FinanceOperationTypeDAO.getInstance().getIdByTranscationType(type);
		 
		 try(PreparedStatement ps=connection.prepareStatement("SELECT id,category,operation_type_id,user_id FROM categories"
                 +"WHERE (user_id IS NULL OR user_id=?) and operation_type_id=? " )){
			 ps.setInt(1, (int) user.getId());//what if user id is null
			 ps.setInt(2, id);
			 try(ResultSet rs=ps.executeQuery();){
				 while(rs.next()) {
					 categories.add(new Category(rs.getInt("id"),
							 rs.getString("category"),
							 type,
							 rs.getInt("user_id")));
				 }
			 }
		 }
		 return categories;
		
	}
	
	

}
