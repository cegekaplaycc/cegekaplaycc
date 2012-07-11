package functional;

import litmus.functional.Html;
import models.Horse;
import models.HorseBuilder;
import models.stable.Box;
import models.stable.BoxBuilder;
import models.stock.Food;

import org.junit.Test;

public class HorseDetailsFunctionalTest extends HoldYourHorsesFunctionalTest {

	@Test
	public void statsAreShown() {
		Horse horse = HorseBuilder.aHorse().withFitness(12).withTraining(34)
				.withFood(Food.HAY).persist();

		Box box = BoxBuilder.aBox().withHorse(horse).persist();

		createPlayerBuilder("joske", "vermeulen").withBoxes(box).persist();

		login("joske", "vermeulen");

        Html html = getHtml("/horse/" + horse.id);
		assertThat(html.selectSingle("#fitness")).containsText("12");
		assertThat(html.selectSingle("#training")).containsText("34");
	}

	@Test
	public void foodAndTrainingAreShown() {
		Horse horse = HorseBuilder.aHorse().withFood(Food.HAY).persist();

		Box box = BoxBuilder.aBox().withHorse(horse).persist();

		createPlayerBuilder("joske", "vermeulen").withBoxes(box).persist();

        Html html = getHtml("/horse/" + horse.id);
		assertThat(html.selectSingle("#food")).containsText("Hay");
	}
}
