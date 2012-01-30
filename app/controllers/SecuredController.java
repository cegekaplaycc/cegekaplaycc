package controllers;

import controllers.securesocial.SecureSocial;
import play.mvc.Controller;
import play.mvc.With;

@With( SecureSocial.class )
public class SecuredController extends Controller {


	public static void someSecretPage() {
		render();
	}


}
