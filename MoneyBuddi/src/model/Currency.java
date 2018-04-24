package model;

public class Currency {
	
	
	public enum CurrencyType{
     //Euro,US dollar,Bulgarian lev,Pound,Russian ruble
		EUR,USD,BGN,GBP,RUB
	}
	
	private long id;
	private CurrencyType type;
	
	public Currency( CurrencyType type) {
		this.type = type;
	}
	
	
	public Currency(long id, CurrencyType type) {
		this.id = id;
		this.type = type;
	}
	
	
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	
	public CurrencyType getType() {
		return type;
	}
	
	public void setType(CurrencyType type) {
		this.type = type;
	}
	
}
