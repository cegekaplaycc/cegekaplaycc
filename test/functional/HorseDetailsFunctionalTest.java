package functional;

import litmus.functional.Html;
import litmus.functional.Request;
import models.Horse;
import models.HorseBuilder;
import models.stock.Food;
import org.junit.Test;

import static models.stock.Food.HAY;

public class HorseDetailsFunctionalTest extends HoldYourHorsesFunctionalTest {

    @Test
    public void statsAreShown() {
        Horse horse = new HorseBuilder()
                .withFitness(12)
                .withTraining(34)
                .withFood(HAY)
                .persist();

        createPlayerBuilder("joske", "vermeulen").withHorses(horse).persist();

        login("joske", "vermeulen");

        Html html = getHtml("/horse/" + horse.id);
        assertThat(html.selectSingle("#fitness")).containsText("12");
        assertThat(html.selectSingle("#training")).containsText("34");
    }

    @Test
    public void foodAndTrainingAreShown() {
        Horse horse = new HorseBuilder()
                .withFood(HAY)
                .persist();

        createPlayerBuilder("joske", "vermeulen")
                .withHorses(horse)
                .persist();

        Html html = getHtml("/horse/" + horse.id);
        assertThat(html.selectSingle("#food")).containsText("Hay");
    }
}
