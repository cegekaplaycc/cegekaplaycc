package controllers;

import models.Player;
import play.mvc.Controller;
import play.mvc.With;
import securesocial.provider.SocialUser;
import controllers.securesocial.SecureSocial;

@With(SecureSocial.class)
public class Dashboard extends Controller {

	public static void dashboard() {
		SocialUser user = (SocialUser) renderArgs.get("user");
		Player player = Player.findByUserId(user.id);
		render(player);
	}
}
