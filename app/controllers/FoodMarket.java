package controllers;

import java.util.Collection;
import java.util.List;

import models.stock.Supply;
import play.mvc.Controller;
import play.mvc.With;
import controllers.securesocial.SecureSocial;

@With(SecureSocial.class)
public class FoodMarket extends Controller {

	public static void buyFood() {
		List<Supply> food = Supply.findAll();
		render(food);
	}
	
	public static void buy(List<Purchase> purchases) {
	}
	
}
