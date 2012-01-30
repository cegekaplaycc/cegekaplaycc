package controllers;

import java.util.List;

import models.Horse;
import models.Player;
import play.db.jpa.JPABase;
import play.mvc.Controller;

public class Horses extends Controller {

	public static void blank() {
		Horse horse = new Horse();
		render(horse);
	}

	public static void create(String name) {
		Horse horse = new Horse(name);
		horse.save();
		list();
	}

	public static void list() {
		List<Horse> horses = Horse.findAll();
		render(horses);
	}
	
	public static void buy(Long id) {
		//TODO getCurrentPlayer and addHorse() :-)
		Horse.findById(id);
		list();
	}

}
