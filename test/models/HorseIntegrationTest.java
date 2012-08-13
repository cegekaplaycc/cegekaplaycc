package models;

import models.stock.Food;
import org.fest.assertions.Assertions;
import org.junit.Test;

import static models.HorseBuilder.aHorse;
import static models.RaceBuilder.aRace;

public class HorseIntegrationTest extends IntegrationTest {

    @Test
    public void canBePersisted() {
        Horse horse = HorseBuilder.aHorse()
                .withFood(Food.POWER_BISCUITS)
                .withName("joske")
                .withPrice(28)
                .withFitness(25)
                .withTraining(21)
                .save();

        clearEntityContext();

        Horse refreshedHorse = Horse.findById(horse.getId());

        Assertions.assertThat(refreshedHorse.getFitness()).isEqualTo(25);
        Assertions.assertThat(refreshedHorse.getName()).isEqualTo("joske");
        Assertions.assertThat(refreshedHorse.getPrice()).isEqualTo(28);
        Assertions.assertThat(refreshedHorse.getTraining()).isEqualTo(21);
        Assertions.assertThat(refreshedHorse.food.label).isEqualTo("Power biscuits");
        Assertions.assertThat(refreshedHorse.food.price).isEqualTo(12);
    }

    @Test
    public void getEnteredRaces_ReturnsEmptyListWhenHorseEnteredNoRaces() {
        aRace().save();
        Horse horse = aHorse().save();
        clearEntityContext();

        Horse.findById(horse.id);
        assertThat(horse.getEnteredRaces()).isEmpty();
    }

    @Test
    public void getEnteredRaces_ReturnsListOfEnteredRaces() {
        Horse horse = aHorse().save();
        Race race1 = aRace().withHorses(horse).save();
        Race race2 = aRace().withHorses(aHorse().build(), horse).save();
        aRace().withoutHorses().save();
        aRace().withHorses(aHorse().save()).save();
        clearEntityContext();

        Horse.findById(horse.id);
        assertThat(horse.getEnteredRaces()).containsOnly(race1, race2);
    }
}
