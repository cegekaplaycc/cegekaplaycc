package models;

import org.fest.assertions.Assertions;
import org.joda.time.DateTime;
import org.junit.Test;

import static models.RaceBuilder.aRace;

public class HorseGetPastEnteredRacesIntegrationTest extends IntegrationTest {

	@Test
	public void getPastEnteredRaces_ReturnsEmptySetWhenNoRacesEntered() {
		Horse horse = HorseBuilder.aHorse().save();

		assertThat(horse.getPastEnteredRaces()).isEmpty();
	}

	@Test
	public void getPastEnteredRaces_ReturnsEmptySetWhenOnlyFutureRacesEntered() {
		Horse horse = HorseBuilder.aHorse().save();
		aRace().withHorses(horse).withStartTime(new DateTime().plusYears(1).toDate()).save();

		Assertions.assertThat(horse.getPastEnteredRaces()).isEmpty();
	}

	@Test
	public void getPastEnteredRaces_ReturnsSetOfPastEnteredRaces() {
		Horse horse = HorseBuilder.aHorse().save();
		Race race1 = aRace().withHorses(horse).withStartTime(new DateTime().minusYears(1).toDate()).save();
		Race race2 = aRace().withHorses(horse).withStartTime(new DateTime().minusYears(2).toDate()).save();
		aRace().withHorses(horse).withStartTime(new DateTime().plusYears(2).toDate()).save();

		Assertions.assertThat(horse.getPastEnteredRaces()).containsOnly(race1, race2);
	}

}
