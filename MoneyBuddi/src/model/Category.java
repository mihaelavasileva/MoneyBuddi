package model;

import model.Transaction.TransactionType;

public class Category {
	
	
	
	private int id;
	private String  category;
	private TransactionType type;
	
	
	public Category(int id, String category, TransactionType type) {
	    this(category,type);
		this.setId(id);
		
	}
	
	public Category(String category, TransactionType type) {
		this.setCategory(category);
		this.setType(type);
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public void setType(TransactionType type) {
		this.type = type;
	}
	
	
	

}
