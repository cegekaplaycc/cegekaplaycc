package controllers;

import static models.Race.findUpcomingRaces;
import models.Player;
import play.mvc.Controller;
import play.mvc.With;
import controllers.securesocial.SecureSocial;

@With(SecureSocial.class)
public class Dashboard extends Controller {

	public static void dashboard() {
		Player player = PlayerUtil.getCurrentPlayer(renderArgs);

		render(player, findUpcomingRaces(3));
	}

	public static void buildANewBox() {
		Player player = PlayerUtil.getCurrentPlayer(renderArgs);
		player.buildNewBox();

		redirect("/dashboard");
	}
}
