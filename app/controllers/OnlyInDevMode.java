package controllers;

import play.Play;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.results.NotFound;

public class OnlyInDevMode extends Controller {

    @Before
    public static void checkIfInDevMode() {
        if(!Play.mode.isDev()) {
            throw new NotFound("Page not found");
        }
    }
}
