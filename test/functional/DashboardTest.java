package functional;

import models.Horse;
import models.HorseBuilder;
import models.RaceBuilder;

import org.junit.Test;

public class DashboardTest extends HoldYourHorsesFunctionalTest {

	@Test
	public void whenNotLoggedInICannotSeeTheDashboard() {
		assertThat(get("/dashboard")).isRedirectTo("/auth/login");
	}

	@Test
	public void iCanSeeTheNameOfSubscribedHorses() {
		Horse paardje = new HorseBuilder().withName("mijnPaardje").persist();
		new RaceBuilder().withHorses(paardje).persist();
		createPlayerBuilder("ben", "matti")
				.withHorses(paardje)
				.persist();

		login("ben", "matti");

		assertThat(getHtml("/dashboard").selectSingle("#upcomingRacesTable")).containsText("mijnPaardje");
	}

}
