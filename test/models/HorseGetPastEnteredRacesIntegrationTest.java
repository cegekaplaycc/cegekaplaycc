package models;

import org.fest.assertions.Assertions;
import org.joda.time.DateTime;
import org.junit.Test;

public class HorseGetPastEnteredRacesIntegrationTest extends IntegrationTest {

	@Test
	public void getPastEnteredRaces_ReturnsEmptySetWhenNoRacesEntered() {
		Horse horse = HorseBuilder.aHorse().persist();

		Assertions.assertThat(horse.getPastEnteredRaces()).isEmpty();
	}

	@Test
	public void getPastEnteredRaces_ReturnsEmptySetWhenOnlyFutureRacesEntered() {
		Horse horse = HorseBuilder.aHorse().persist();
		new RaceBuilder().withHorses(horse).withStartTime(new DateTime().plusYears(1).toDate()).persist();

		Assertions.assertThat(horse.getPastEnteredRaces()).isEmpty();
	}

	@Test
	public void getPastEnteredRaces_ReturnsSetOfPastEnteredRaces() {
		Horse horse = HorseBuilder.aHorse().persist();
		Race race1 = new RaceBuilder().withHorses(horse).withStartTime(new DateTime().minusYears(1).toDate()).persist();
		Race race2 = new RaceBuilder().withHorses(horse).withStartTime(new DateTime().minusYears(2).toDate()).persist();
		new RaceBuilder().withHorses(horse).withStartTime(new DateTime().plusYears(2).toDate()).persist();

		Assertions.assertThat(horse.getPastEnteredRaces()).containsOnly(race1, race2);
	}

}
