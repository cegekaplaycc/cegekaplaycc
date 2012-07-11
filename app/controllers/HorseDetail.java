package controllers;

import java.util.EnumSet;
import java.util.Set;

import models.Horse;
import models.stock.Food;
import play.data.validation.Required;
import play.mvc.Controller;
import results.BadRequest;

public class HorseDetail extends Controller {

    public static void changeMoneyForTrainer(@Required Long horseId, @Required int moneyForTrainer) {
        validate();
        Horse horse = Horse.<Horse> findById(horseId);
        horse.moneyForTrainer = moneyForTrainer;
        horse.save();
        ok();
    }

	public static void changeFood(@Required Long horseId, @Required Food food) {
		validate();
		Horse horse = Horse.<Horse> findById(horseId);
		horse.food = food;
		horse.save();
		ok();
	}

	public static void show(@Required Long id) {
		validate();
		Horse horse = Horse.findById(id);
		Set<Food> foods = EnumSet.allOf(Food.class);
		render(horse, foods);
	}

	private static void validate() {
		if (validation.hasErrors()) {
			throw new BadRequest();
		}
	}

}
