package models;

import java.util.Set;

import org.fest.assertions.Assertions;
import org.joda.time.DateTime;
import org.junit.Test;

public class PlayerGetPastEnteredRacesIntegrationTest extends IntegrationTestCase {

	@Test
	public void getPastEnteredRaces_ReturnsEmptyListWhenNoRacesFound() {
		Player player = new PlayerBuilder().build().save();

		Assertions.assertThat(player.getPastEnteredRaces()).isEmpty();
	}

	@Test
	public void getPastEnteredRaces_ReturnsEmptyListWhenOnlyFutureEnteredRaces() {
		Horse horse = new HorseBuilder().build().save();
		Player player = new PlayerBuilder().withHorses(horse).build().save();
		new RaceBuilder().withHorses(horse).withStartTime(new DateTime().plusYears(1).toDate()).build().save();

		Assertions.assertThat(player.getPastEnteredRaces()).isEmpty();
	}

	@Test
	public void getPastEnteredRaces_ReturnsEmptyListWhenNoPastRacesEntered() {
		Player player = new PlayerBuilder().build().save();
		new RaceBuilder().withStartTime(new DateTime().minusYears(1).toDate()).build().save();

		Assertions.assertThat(player.getPastEnteredRaces()).isEmpty();
	}

	@Test
	public void getPastEnteredRaces_ReturnsListOfRacesEnteredInThePast() {
		Horse horse = new HorseBuilder().build().save();
		Player player = new PlayerBuilder().withHorses(horse).build().save();
		Race race = new RaceBuilder().withHorses(horse).withStartTime(new DateTime().minusDays(1).toDate()).build().save();

		Assertions.assertThat(player.getPastEnteredRaces()).contains(race);
	}

}
