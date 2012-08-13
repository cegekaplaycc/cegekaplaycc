package models;

import org.joda.time.DateTime;
import org.junit.Test;

import java.util.List;

import static models.HorseBuilder.aHorse;
import static models.RaceBuilder.aRace;

public class RaceIntegrationTest extends IntegrationTest {

    @Test
    public void canBePersisted() {
        Horse horse1 = aHorse().withName("Horse 1").save();
        Horse horse2 = aHorse().withName("Horse 2").save();

        Race race = aRace()
                .withHorses(horse1, horse2)
                .withStarted()
                .save();

        clearEntityContext();

        List<Race> races = Race.findAll();

        assertThat(races).hasSize(1);
        Race savedRace = races.iterator().next();

        assertThat(race.winner).isNotNull();
        assertThat(savedRace.getEnteredHorses()).contains(horse1, horse2);
    }

    @Test
    public void findUpcomingRaces_returnsAllRacesIfLessThen3RacesScheduled() {
        aRace().withStartTimeInFuture().save();
        aRace().withStartTimeInFuture().save();
        aRace().withStartTimeInPast().save();

        assertThat(Race.findUpcomingRaces(3)).hasSize(2);
    }

    @Test
    public void findUpcomingRaces_returnsOnlyTheNearestInTheFuture() {
        aRace().withStartTimeInPast().save();
        aRace().withStartTimeInPast().save();
        aRace().withStartTime(new DateTime().plusDays(5).toDate()).save();
        Race race1 = aRace().withStartTime(new DateTime().plusDays(1).toDate()).save();
        Race race3 = aRace().withStartTime(new DateTime().plusDays(3).toDate()).save();
        Race race2 = aRace().withStartTime(new DateTime().plusDays(2).toDate()).save();
        aRace().withStartTime(new DateTime().plusDays(4).toDate()).save();
        aRace().withStartTimeInPast().save();
        aRace().withStartTimeInPast().save();
        aRace().withStartTimeInPast().save();

        assertThat(Race.findUpcomingRaces(3)).containsExactly(race1, race2, race3);
    }

}
