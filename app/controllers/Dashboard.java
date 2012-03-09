package controllers;

import play.mvc.Controller;

public class Dashboard extends Controller {

	public static void dashboard() {
		//		renderArgs.put("pastRaces", Player.getPastEnteredRaces());
		render();
	}
}
