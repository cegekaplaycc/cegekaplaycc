package controllers;

import java.util.Collection;
import java.util.List;

import models.stock.Food;
import play.mvc.Controller;
import play.mvc.With;
import controllers.securesocial.SecureSocial;

@With(SecureSocial.class)
public class FoodMarket extends Controller {

	public static void buyFood() {
		List<Food> food = Food.findAll();
		render(food);
	}
	
	public static void buy(List<Purchase> purchases) {
		
	}
	
}
