package models;

import models.stock.Food;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class HorseIntegrationTest extends IntegrationTest {

	@Test
	public void canBePersisted() {
		Horse horse = new HorseBuilder()
				.withFood(Food.POWER_BISCUITS)
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
		Assertions.assertThat(refreshedHorse.food.label).isEqualTo("Power biscuits");
		Assertions.assertThat(refreshedHorse.food.price).isEqualTo(12L);
	}
}
