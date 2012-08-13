package models;

import models.stable.Box;
import models.stable.BoxBuilder;
import org.fest.assertions.Assertions;
import org.joda.time.DateTime;
import org.junit.Test;

import static models.PlayerBuilder.aPlayer;
import static models.RaceBuilder.aRace;

public class PlayerGetPastEnteredRacesIntegrationTest extends IntegrationTest {

    @Test
    public void getPastEnteredRaces_ReturnsEmptyListWhenNoRacesFound() {
        Player player = aPlayer().save();

        Assertions.assertThat(player.getPastEnteredRaces()).isEmpty();
    }

    @Test
    public void getPastEnteredRaces_ReturnsEmptyListWhenOnlyFutureEnteredRaces() {
        Horse horse = HorseBuilder.aHorse().save();
        Box box = BoxBuilder.aBox().withHorse(horse).save();

        Player player = aPlayer().withBoxes(box).save();
        aRace().withHorses(horse)
                .withStartTime(new DateTime().plusYears(1).toDate())
                .save();

        assertThat(player.getPastEnteredRaces()).isEmpty();
    }

    @Test
    public void getPastEnteredRaces_ReturnsEmptyListWhenNoPastRacesEntered() {
        Player player = aPlayer().save();
        aRace().withStartTime(new DateTime().minusYears(1).toDate()).save();

        assertThat(player.getPastEnteredRaces()).isEmpty();
    }

    @Test
    public void getPastEnteredRaces_ReturnsListOfRacesEnteredInThePast() {
        Horse horse = HorseBuilder.aHorse().save();
        Box box = BoxBuilder.aBox().withHorse(horse).save();

        Player player = aPlayer().withBoxes(box).save();
        Race race = aRace()
                .withHorses(horse)
                .withStartTime(new DateTime().minusDays(1).toDate())
                .save();

        assertThat(player.getPastEnteredRaces()).contains(race);
    }

}
