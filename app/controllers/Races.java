package controllers;

import java.util.List;

import models.Horse;
import models.Race;
import play.db.jpa.JPABase;
import play.mvc.Controller;

public class Races extends Controller {

	public static void list() {
		List<Race> races = Race.findAll();
		render(races);
	}
	
	public static void enter() {
		List<Horse> horses = Horse.findAll();
		
		render(horses);
	}
	
	public static void detail(Long id) {
		Race race = Race.findById(id);
		
		render(race);
	}
	
	public static void newRace() {
		render();
	}
	
	public static void create(String name) {
		Race race = new Race();
		race.name = name;
		race.validateAndCreate();
		list();
	}
	
}
