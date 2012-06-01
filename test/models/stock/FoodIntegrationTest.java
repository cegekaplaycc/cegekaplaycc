package models.stock;

import models.IntegrationTest;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class FoodIntegrationTest extends IntegrationTest {

	@Test
	public void canBePersisted() {
		Food food = new FoodBuilder().withName("Power Biscuits").withPrice(12L).build();
		food.save();

		clearEntityContext();

		Food retrievedFood = Food.findById(food.id);

		Assertions.assertThat(retrievedFood.name).isEqualTo("Power Biscuits");
		Assertions.assertThat(retrievedFood.price).isEqualTo(12L);
	}

}
