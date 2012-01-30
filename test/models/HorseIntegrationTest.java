package models;

import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class HorseIntegrationTest extends IntegrationTestCase {

	@Test
	public void canBePersisted() {
		Horse horse = new Horse("joske");
		horse.setPrice(12654L);
		horse.save();
		List<Horse> horses = Horse.findAll();

		clearEntityContext();
		Assertions.assertThat(horses).hasSize(1);
		Horse refreshedHorse = horses.iterator().next();
		Assertions.assertThat(refreshedHorse.getName()).isEqualTo("joske");
		Assertions.assertThat(refreshedHorse.getPrice()).isEqualTo(12654L);
	}
}
