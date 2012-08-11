package controllers;

import controllers.securesocial.SecureSocial;
import models.Race;
import play.mvc.Controller;
import play.mvc.With;

@With(SecureSocial.class)
public class Races extends Controller {

    public static void showAllUpcoming(){
        render();
    }
}
