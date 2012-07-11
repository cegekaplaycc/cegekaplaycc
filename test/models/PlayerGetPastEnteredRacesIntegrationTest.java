package models;

import models.stable.Box;
import models.stable.BoxBuilder;

import org.fest.assertions.Assertions;
import org.joda.time.DateTime;
import org.junit.Test;

public class PlayerGetPastEnteredRacesIntegrationTest extends IntegrationTest {

	@Test
	public void getPastEnteredRaces_ReturnsEmptyListWhenNoRacesFound() {
		Player player = PlayerBuilder.aPlayer().persist();

		Assertions.assertThat(player.getPastEnteredRaces()).isEmpty();
	}

	@Test
	public void getPastEnteredRaces_ReturnsEmptyListWhenOnlyFutureEnteredRaces() {
		Horse horse = HorseBuilder.aHorse().persist();
		Box box = BoxBuilder.aBox().withHorse(horse).persist();

		Player player = PlayerBuilder.aPlayer().withBoxes(box).persist();
		new RaceBuilder().withHorses(horse)
				.withStartTime(new DateTime().plusYears(1).toDate()).build()
				.save();

		Assertions.assertThat(player.getPastEnteredRaces()).isEmpty();
	}

	@Test
	public void getPastEnteredRaces_ReturnsEmptyListWhenNoPastRacesEntered() {
		Player player = PlayerBuilder.aPlayer().persist();
		new RaceBuilder().withStartTime(new DateTime().minusYears(1).toDate())
				.persist();

		Assertions.assertThat(player.getPastEnteredRaces()).isEmpty();
	}

	@Test
	public void getPastEnteredRaces_ReturnsListOfRacesEnteredInThePast() {
		Horse horse = HorseBuilder.aHorse().persist();
		Box box = BoxBuilder.aBox().withHorse(horse).persist();

		Player player = PlayerBuilder.aPlayer().withBoxes(box).persist();
		Race race = new RaceBuilder().withHorses(horse)
				.withStartTime(new DateTime().minusDays(1).toDate()).build()
				.save();

		Assertions.assertThat(player.getPastEnteredRaces()).contains(race);
	}

}
