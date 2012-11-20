package controllers;

import static controllers.PlayerUtil.*;
import static models.Race.findUpcomingRaces;

import java.util.List;

import models.Player;
import models.Race;
import play.mvc.Controller;
import play.mvc.With;
import controllers.securesocial.SecureSocial;

@With(SecureSocial.class)
public class Dashboard extends Controller {

	public static void dashboard() {
		Player player = getCurrentPlayer(renderArgs);
		List<Race> upcomingRaces = findUpcomingRaces(5);
        List<Race> pastRaces = Race.findPastRaces(5);
		render(player, upcomingRaces, pastRaces);
	}

	public static void buildANewBox() {
		Player player = getCurrentPlayer(renderArgs);
		player.buildNewBox();
		redirect("/dashboard");
	}


}
