package models;

import models.stock.Food;

public class Purchase {

	public Food foodId;
	public String amount;
	
	public int getPrice() {
		return Integer.parseInt(amount)* foodId.price;
	}
	
	
	
}
