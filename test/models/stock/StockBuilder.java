package models.stock;

import java.util.HashMap;
import java.util.Map;

import util.AbstractBuilder;

public class StockBuilder extends AbstractBuilder<Stock> {

	private Map<Food, Integer> supplies = new HashMap<Food, Integer>();

	@Override
	public Stock build() {
		Stock stock = new Stock();
		for (Food supply : supplies.keySet()) {
			stock.items.add(new StockItem(supply, supplies.get(supply)));
		}
		return stock;
	}

	public StockBuilder withSupply(Food supply, int amount) {
		if (supplies.containsKey(supply)) {
			supplies.put(supply, supplies.get(supply) + amount);
		} else {
			supplies.put(supply, amount);
		}
		return this;
	}

	public StockBuilder withoutSupplies() {
		supplies.clear();
		return this;
	}

}
