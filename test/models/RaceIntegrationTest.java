package models;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Test;

import play.db.jpa.JPA;
import play.db.jpa.JPABase;

public class RaceIntegrationTest extends IntegrationTestCase {

	@Test
	public void canBePersisted() {
		Horse horse1 = new HorseBuilder().withName("Horse 1").build();
		Horse horse2 = new HorseBuilder().withName("Horse 2").build();
		save(horse1, horse2);

		Race race = new RaceBuilder()
			.withName("race")
			.withHorses(horse1, horse2)
			.withStarted()
			.build();
		Boolean created = race.validateAndCreate();
		
		Assertions.assertThat(created).isTrue();
		
		JPA.em().clear();
		List<Race> races = Race.findAll();
		
		Assertions.assertThat(races).hasSize(1);
		Race savedRace = races.iterator().next();
		
		Assertions.assertThat(race.winner).isNotNull();
		Assertions.assertThat(savedRace.getEnteredHorses()).contains(horse1, horse2);
	}
	
}
