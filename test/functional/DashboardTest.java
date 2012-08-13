package functional;

import models.Horse;
import models.HorseBuilder;
import models.stable.Box;
import org.junit.Test;

import static models.RaceBuilder.aRace;
import static models.stable.BoxBuilder.aBox;

public class DashboardTest extends HoldYourHorsesFunctionalTest {

	@Test
	public void whenNotLoggedInICannotSeeTheDashboard() {
		assertThat(get("/dashboard")).isRedirectTo("/auth/login");
	}

	@Test
	public void iCanSeeTheNameOfSubscribedHorses() {
		Horse paardje = HorseBuilder.aHorse().withName("mijnPaardje").save();
		Box box = aBox().withHorse(paardje).save();

		aRace().withHorses(paardje).save();
		createPlayerBuilder("ben", "matti").withBoxes(box).save();

		login("ben", "matti");

		assertThat(getHtml("/dashboard").selectSingle("#upcomingRacesTable"))
				.containsText("mijnPaardje");
	}

}
