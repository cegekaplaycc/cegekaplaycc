package controllers;

import models.stock.Food;

public class PurchaseBuilder {
	
	private String amount = "0";
	private Food foodId = Food.HAY;
	
	public Purchase build() {
		Purchase purchase = new Purchase();
		purchase.amount = amount;
		purchase.foodId = foodId;
		return purchase;
	}

	public PurchaseBuilder withAmount(String amount) {
		this.amount = amount;
		return this;
	}

	public PurchaseBuilder withFood(Food food) {
		this.foodId = food;
		return this;
	}

}
