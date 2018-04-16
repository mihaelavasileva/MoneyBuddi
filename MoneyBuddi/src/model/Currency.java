package model;

public class Currency {
	
	
	public enum CurrencyType{
     //Euro,US dollar,Bulgarian lev,Pound,Russian ruble
		EUR,USD,BGN,GBP,RUB
	}
	
	private int id;
	private CurrencyType type;
	
	public Currency( CurrencyType type) {
		this.type = type;
	}
	
	
	public Currency(int id, CurrencyType type) {
		this.id = id;
		this.type = type;
	}
	
	
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public CurrencyType getType() {
		return type;
	}
	
	public void setType(CurrencyType type) {
		this.type = type;
	}
	
}
