package controllers;

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
		Player player = PlayerUtil.getCurrentPlayer(renderArgs);
		List<Race> upcomingRaces = findUpcomingRaces(3);
		render(player, upcomingRaces);
	}

	public static void buildANewBox() {
		Player player = PlayerUtil.getCurrentPlayer(renderArgs);
		player.buildNewBox();

		redirect("/dashboard");
	}
}
