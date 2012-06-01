package models.stock;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.apache.commons.lang.NotImplementedException;

import play.db.jpa.Model;

@Entity
public class Stock extends Model {

	@OneToMany
	public Collection<StockItem> items = new ArrayList<StockItem>();
	
	public void add(Food supply) {
		add(supply, 1);
	}

	public void add(Food supply, int amount) {
		StockItem stockItemForSupply = getStockItemForSupply(supply);
		if(stockItemForSupply == null) {
			items.add(new StockItem(supply, amount));
		} else {
			stockItemForSupply.amount += amount;
		}
	}
	
	private StockItem getStockItemForSupply(Food supply) {
		for (StockItem item : items) {
			if(item.isSupply(supply)) {
				return item;
			}
		}
		return null;
	}
}
