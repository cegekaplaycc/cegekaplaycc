package functional;

import models.Horse;
import models.HorseBuilder;
import models.PlayerBuilder;
import models.RaceBuilder;
import org.junit.Test;

public class DashboardTest extends HoldYourHorsesFunctionalTest {


    @Test
    public void whenNotLoggedInICannotSeeTheDashboard(){
        assertThat(get("/dashboard")).isRedirectTo("/auth/login");
    }

    @Test
    public void iCanSeeTheNameOfSubscribedHorses() {
        Horse paardje = new HorseBuilder().withName("mijnPaardje").build().save();
        new RaceBuilder().withHorses(paardje).persist();
        new PlayerBuilder()
                .withUserId("ben")
                .withPassword("matti")
                .withHorses(paardje)
                .build().save();

        login("ben", "matti");

        assertThat(getHtml("/dashboard").selectSingle("#upcomingRacesTable")).containsText("mijnPaardje");
    }


}
