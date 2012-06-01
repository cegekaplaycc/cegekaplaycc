package models;

import models.stock.Food;
import models.stock.FoodBuilder;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class HorseIntegrationTest extends IntegrationTest {

	@Test
	public void canBePersisted() {
		Food food = new FoodBuilder().withName("Power Biscuits").withPrice(12L).persist();
		Horse horse = new HorseBuilder()
				.withFood(food)
				.withName("joske")
				.withPrice(28)
				.withFitness(25)
				.withTraining(21)
				.persist();

		clearEntityContext();

		Horse refreshedHorse = Horse.findById(horse.getId());

		Assertions.assertThat(refreshedHorse.getFitness()).isEqualTo(25);
		Assertions.assertThat(refreshedHorse.getName()).isEqualTo("joske");
		Assertions.assertThat(refreshedHorse.getPrice()).isEqualTo(28);
		Assertions.assertThat(refreshedHorse.getTraining()).isEqualTo(21);
		Assertions.assertThat(refreshedHorse.food.name).isEqualTo("Power Biscuits");
		Assertions.assertThat(refreshedHorse.food.price).isEqualTo(12L);
	}
}
