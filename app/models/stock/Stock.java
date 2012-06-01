package models.stock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.apache.commons.lang.NotImplementedException;

import controllers.Purchase;

import play.db.jpa.Model;

@Entity
public class Stock extends Model {

	@OneToMany(cascade = CascadeType.PERSIST)
	public Collection<StockItem> items = new ArrayList<StockItem>();
	
	public void add(Food supply) {
		add(supply, 1);
	}

	public void add(Food food, int amount) {
		StockItem stockItemForSupply = getStockItemForSupply(food);
		if(stockItemForSupply == null) {
			items.add(new StockItem(food, amount));
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

	public void buy(List<Purchase> purchases) {
		for(Purchase purchase : purchases) {
			if(purchase != null) {
				add(purchase.foodId, Integer.parseInt(purchase.amount));
			}
		}
	}
}
