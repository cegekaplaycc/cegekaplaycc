package controllers;

import controllers.securesocial.SecureSocial;
import play.mvc.Scope.RenderArgs;
import play.mvc.With;
import securesocial.provider.SocialUser;
import models.Player;

@With(SecureSocial.class)
public class PlayerUtil {
	
	public static Player getCurrentPlayer(RenderArgs renderArgs) {
		return Player.findByUserId(((SocialUser) renderArgs.get("user")).id);
	}

}
