package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.devTools.grid;

public class DevTools extends Controller {

    public static Result showCssGrid(){
        return ok(grid.render("333", "100px"));
    }
}
