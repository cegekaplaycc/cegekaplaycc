package controllers;

import models.Horse;
import play.data.validation.Required;
import play.mvc.Controller;
import results.BadRequest;

public class HorseDetail extends Controller {

	public static void show(@Required Long id) {
		validate();
		Horse horse = Horse.findById(id);
		render(horse);
	}

	private static void validate() {
		if (validation.hasErrors()) {
			throw new BadRequest();
		}
	}

}
