package models;


import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class HorseIntegrationTest extends IntegrationTestCase {

	@Test
	public void canBePersisted() {
		new Horse("joske").save();
		List<Horse> horses = Horse.findAll();
		
		Assertions.assertThat(horses).hasSize(1);
		Horse refreshedHorse = horses.iterator().next();
		Assertions.assertThat(refreshedHorse.name).isEqualTo("joske");
	}
}
