package controllers;

import java.util.List;

import models.stock.Food;
import play.mvc.Controller;
import play.mvc.With;

import com.google.common.collect.Lists;

import controllers.securesocial.SecureSocial;

@With(SecureSocial.class)
public class FoodMarket extends Controller {

	public static void buyFood() {
		List<Food> food = Lists.newArrayList(Food.values());

		render(food);
	}

	public static void buy(List<Purchase> purchases) {
	}

}
