package models;

import org.fest.assertions.Assertions;
import org.joda.time.DateTime;
import org.junit.Test;

public class HorseGetPastEnteredRacesIntegrationTest extends IntegrationTestCase {

	@Test
	public void getPastEnteredRaces_ReturnsEmptySetWhenNoRacesEntered() {
		Horse horse = new HorseBuilder().build().save();

		Assertions.assertThat(horse.getPastEnteredRaces()).isEmpty();
	}

	@Test
	public void getPastEnteredRaces_ReturnsEmptySetWhenOnlyFutureRacesEntered() {
		Horse horse = new HorseBuilder().build().save();
		new RaceBuilder().withHorses(horse).withStartTime(new DateTime().plusYears(1).toDate()).build().save();

		Assertions.assertThat(horse.getPastEnteredRaces()).isEmpty();
	}

	@Test
	public void getPastEnteredRaces_ReturnsSetOfPastEnteredRaces() {
		Horse horse = new HorseBuilder().build().save();
		Race race1 = new RaceBuilder().withHorses(horse).withStartTime(new DateTime().minusYears(1).toDate()).build().save();
		Race race2 = new RaceBuilder().withHorses(horse).withStartTime(new DateTime().minusYears(2).toDate()).build().save();
		new RaceBuilder().withHorses(horse).withStartTime(new DateTime().plusYears(2).toDate()).build().save();

		Assertions.assertThat(horse.getPastEnteredRaces()).containsOnly(race1, race2);
	}

}
