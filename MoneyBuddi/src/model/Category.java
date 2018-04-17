package model;

import exceptions.InvalidDataException;
import model.Transaction.TransactionType;

public class Category {
	
	
	
	private int id;
	private String  category;
	private TransactionType type;
	private int userId;//not obligatory
	
	
	public Category(int id, String category, TransactionType type) throws InvalidDataException {
	    this(category,type);
		this.setId(id);
		
	}
	
	public Category(String category, TransactionType type) throws InvalidDataException {
		this.setCategory(category);
		this.setType(type);
	}
	
	public Category(String category, TransactionType type,int user_id) throws InvalidDataException {
		this(category,type);
		this.setUserId(user_id);
	}
	
	public Category(int id,String category, TransactionType type,int user_id) throws InvalidDataException {
		this(id,category,type);
		this.setUserId(user_id);
	}
	
	
	
	
	public int getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = (int)id;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public TransactionType getType() {
		return type;
	}
	
	public void setType(TransactionType type) throws InvalidDataException {
		if(type==null) {
			throw new InvalidDataException("Type cant be null");
		}
		this.type = type;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	
	

}
