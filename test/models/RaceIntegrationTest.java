package models;

import org.fest.assertions.Assertions;
import org.junit.Test;

import java.util.List;

public class RaceIntegrationTest extends IntegrationTest {

    @Test
    public void canBePersisted() {
        Horse horse1 = new HorseBuilder().withName("Horse 1").build().save();
        Horse horse2 = new HorseBuilder().withName("Horse 2").build().save();

        Race race = new RaceBuilder()
                .withName("race")
                .withHorses(horse1, horse2)
                .withStarted()
                .build()
                .save();

        clearEntityContext();

        List<Race> races = Race.findAll();

        Assertions.assertThat(races).hasSize(1);
        Race savedRace = races.iterator().next();

        Assertions.assertThat(race.winner).isNotNull();
        Assertions.assertThat(savedRace.getEnteredHorses()).contains(horse1, horse2);
    }

}
