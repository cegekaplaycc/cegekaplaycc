package controllers;

import controllers.securesocial.SecureSocial;
import play.mvc.*;

@With(SecureSocial.class)
public class Players extends Controller {

    public static void index() {
        render();
    }

}
