package controllers;

import play.mvc.Controller;
import play.mvc.With;

@With(OnlyInDevMode.class)
public class Mockups extends Controller {

    public static void showMockup(String mockup){
        render("/Mockups/" + mockup + ".html");
    }
}
