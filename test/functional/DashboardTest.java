package functional;

import static models.stable.BoxBuilder.aBox;
import models.Horse;
import models.HorseBuilder;
import models.RaceBuilder;
import models.stable.Box;

import org.junit.Test;

public class DashboardTest extends HoldYourHorsesFunctionalTest {

	@Test
	public void whenNotLoggedInICannotSeeTheDashboard() {
		assertThat(get("/dashboard")).isRedirectTo("/auth/login");
	}

	@Test
	public void iCanSeeTheNameOfSubscribedHorses() {
		Horse paardje = HorseBuilder.aHorse().withName("mijnPaardje").persist();
		Box box = aBox().withHorse(paardje).persist();

		new RaceBuilder().withHorses(paardje).persist();
		createPlayerBuilder("ben", "matti").withBoxes(box).persist();

		login("ben", "matti");

		assertThat(getHtml("/dashboard").selectSingle("#upcomingRacesTable"))
				.containsText("mijnPaardje");
	}

}
