package models;

import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Test;

import play.db.jpa.JPA;

public class HorseIntegrationTest extends IntegrationTestCase {

	@Test
	public void canBePersisted() {
		Horse horse = new HorseBuilder().withName("joske").withPrice(28).withFitness(25).withTraining(21).build().save();
		
		clearEntityContext();
		
		Horse refreshedHorse = Horse.findById(horse.getId());

		Assertions.assertThat(refreshedHorse.getFitness()).isEqualTo(25);
		Assertions.assertThat(refreshedHorse.getName()).isEqualTo("joske");
		Assertions.assertThat(refreshedHorse.getPrice()).isEqualTo(28);
		Assertions.assertThat(refreshedHorse.getTraining()).isEqualTo(21);
	}
}
