package models;

import org.junit.Test;

import java.util.List;

import static models.HorseBuilder.aHorse;

public class RaceIntegrationTest extends IntegrationTest {

    @Test
    public void canBePersisted() {
        Horse horse1 = aHorse().withName("Horse 1").persist();
        Horse horse2 = aHorse().withName("Horse 2").persist();

        Race race = new RaceBuilder()
                .withName("race")
                .withHorses(horse1, horse2)
                .withStarted()
                .build()
                .save();

        clearEntityContext();

        List<Race> races = Race.findAll();

        assertThat(races).hasSize(1);
        Race savedRace = races.iterator().next();

        assertThat(race.winner).isNotNull();
        assertThat(savedRace.getEnteredHorses()).contains(horse1, horse2);
    }

}
