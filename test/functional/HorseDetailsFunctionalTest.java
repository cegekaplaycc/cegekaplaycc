package functional;

import litmus.functional.Html;
import models.Horse;
import models.HorseBuilder;
import models.stock.Food;

import org.junit.Test;

public class HorseDetailsFunctionalTest extends HoldYourHorsesFunctionalTest {

	@Test
	public void statsAreShown() {
		Horse horse = new HorseBuilder()
				.withFitness(12)
				.withTraining(34)
				.withFood(Food.HAY)
				.persist();

		createPlayerBuilder("joske", "vermeulen")
				.withHorses(horse)
				.persist();

		login("joske", "vermeulen");

		Html html = getHtml("/horseDetail?id=" + horse.id);
		assertThat(html.selectSingle("#fitness")).containsText("12");
		assertThat(html.selectSingle("#training")).containsText("34");
	}

	@Test
	public void foodAndTrainingAreShown() {
		Horse horse = new HorseBuilder()
				.withFood(Food.HAY)
				.persist();

		createPlayerBuilder("joske", "vermeulen")
				.withHorses(horse)
				.persist();

		Html html = getHtml("/horseDetail?id=" + horse.id);
		assertThat(html.selectSingle("#food")).containsText("Hay");
	}
}
