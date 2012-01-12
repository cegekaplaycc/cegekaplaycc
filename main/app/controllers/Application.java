package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
    public static Result grid() {
        return ok(grid.render("129129"));
    }
 
  
}
