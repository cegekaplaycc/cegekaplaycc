package controllers;

public class PurchaseBuilder {
	
	private String amount = "0";
	private String foodId = "0";
	
	public Purchase build() {
		Purchase purchase = new Purchase();
		purchase.amount = amount;
		purchase.foodId = foodId;
		return purchase;
	}

	public PurchaseBuilder withFoodId(String foodId) {
		this.foodId = foodId;
		return this;
	}

	public PurchaseBuilder withAmount(String amount) {
		this.amount = amount;
		return this;
	}

}
