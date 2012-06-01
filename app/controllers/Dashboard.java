package controllers;

import controllers.securesocial.SecureSocial;
import models.Player;
import models.Race;
import play.mvc.Controller;
import play.mvc.With;
import securesocial.provider.SocialUser;

import java.util.List;

import static models.Race.findUpcomingRaces;

@With(SecureSocial.class)
public class Dashboard extends Controller {

	public static void dashboard() {
        Player player = Player.findByUserId(((SocialUser) renderArgs.get("user")).id);
        List<Race> upcomingRaces = findUpcomingRaces(3);
		render(player, upcomingRaces);
	}
}
