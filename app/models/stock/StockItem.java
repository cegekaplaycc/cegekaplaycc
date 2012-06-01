package models.stock;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class StockItem extends Model {

	public Food supply;
	public int amount = 0;

	public StockItem(Food supply) {
		this.supply = supply;
		this.amount++;
	}

	public StockItem(Food supply, int amount) {
		this.supply = supply;
		this.amount += amount;
	}
	
	public boolean isSupply(Food supply) {
		return this.supply.equals(supply);
	}
	
}
