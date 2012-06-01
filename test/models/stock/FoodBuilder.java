package models.stock;

import util.AbstractBuilder;

public class FoodBuilder extends AbstractBuilder<Food> {

	private String name;
	private long price;

	@Override
	public Food build() {
		Food food = new Food();

		food.name = name;
		food.price = price;

		return food;
	}

	public FoodBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public FoodBuilder withPrice(long price) {
		this.price = price;
		return this;
	}

}
