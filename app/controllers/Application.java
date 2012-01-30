package controllers;

import controllers.securesocial.SecureSocial;
import models.Horse;
import play.mvc.Controller;

public class Application extends Controller {
    public static void index() {
        renderArgs.put("horses", Horse.findAll());
        renderArgs.put("user", SecureSocial.getCurrentUser());
        render();
    }
    
    public static void logout() {
        render();
    }

}
