package models;

import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class RaceIntegrationTest extends IntegrationTestCase {

	@Test
	public void canBePersisted() {
		Horse horse = new Horse("Horse 1");
		horse.save();

		Race race = new Race();
		race.name = "race";
		race.enter(horse);
		Boolean created = race.validateAndCreate();
		
		Assertions.assertThat(created).isTrue();
		
		List<Race> races = Race.findAll();
		
		Assertions.assertThat(races).hasSize(1);
		Race savedRace = races.iterator().next();
		
		Assertions.assertThat(savedRace.getEnteredHorses()).containsOnly(horse);
	}
	
}
