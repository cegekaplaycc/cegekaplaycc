package models.stock;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class StockItem extends Model {

	public Supply supply;
	public int amount = 0;

	public StockItem(Supply supply) {
		this.supply = supply;
		this.amount++;
	}

	public StockItem(Supply supply, int amount) {
		this.supply = supply;
		this.amount += amount;
	}
	
	public boolean isSupply(Supply supply) {
		return this.supply.equals(supply);
	}
	
}
