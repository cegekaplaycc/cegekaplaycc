package controllers;

import controllers.securesocial.SecureSocial;
import play.mvc.Controller;
import play.mvc.With;

@With(SecureSocial.class)
public class LoginController extends Controller {

    public static void login() {
        render();
    }

}
