package controllers;

import controllers.securesocial.SecureSocial;
import models.Race;
import play.mvc.Controller;
import play.mvc.With;

import java.util.List;

@With(SecureSocial.class)
public class Races extends Controller {

    public static void showAllUpcoming(){
        List<Race> races = Race.findUpcomingRaces();
        render(races);
    }
}
