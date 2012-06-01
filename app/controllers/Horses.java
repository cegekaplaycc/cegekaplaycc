package controllers;

import java.util.List;
import java.util.Set;

import controllers.securesocial.SecureSocial;

import models.Horse;
import models.Player;
import play.db.jpa.GenericModel.JPAQuery;
import play.db.jpa.JPABase;
import play.mvc.Controller;
import securesocial.provider.SocialUser;

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
		
		SocialUser currentUser = SecureSocial.getCurrentUser();
		Player player = Player.find("userId", currentUser.id.id).first();
		Set<Horse> ownHorses = player.getHorses();
		render(horses, ownHorses);
	}
	
	public static void buy(Long id) {
		//TODO getCurrentPlayer and addHorse() :-)
//		SocialUser currentUser = SecureSocial.getCurrentUser();
//		Player player = Player.find("userId", currentUser.id.id).first();
//		player.addHorse((Horse)Horse.findById(id));
		list();
	}

}
