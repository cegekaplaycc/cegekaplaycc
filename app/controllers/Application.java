package controllers;

import models.Horse;
import play.mvc.Controller;
import play.mvc.With;
import controllers.securesocial.SecureSocial;


public class Application extends Controller {

    public static void index() {
    	renderArgs.put("horses", Horse.findAll());
        render();
    }

}
