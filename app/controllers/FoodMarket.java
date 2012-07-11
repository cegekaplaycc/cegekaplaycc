package controllers;

import java.util.List;

import models.Player;
import models.Purchase;
import models.stock.Food;
import play.mvc.Controller;
import play.mvc.With;

import controllers.securesocial.SecureSocial;

import static com.google.common.collect.Lists.newArrayList;

@With(SecureSocial.class)
public class FoodMarket extends Controller {

	public static void foodMarket() {
		Player player = PlayerUtil.getCurrentPlayer(renderArgs);
		List<Food> food = newArrayList(Food.values());
		render(player, food);
	}

	public static void buy(List<Purchase> purchases) {
		PlayerUtil.getCurrentPlayer(renderArgs).buy(purchases);
		Dashboard.dashboard();
	}

}
