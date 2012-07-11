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
		Player player = PlayerUtil.getCurrentPlayer(renderArgs);
		validate(player, purchases);
		player.buy(purchases);
		Dashboard.dashboard();
	}

	private static void validate(Player player, List<Purchase> purchases) {
		int purchaseTotal = 0;
		for (Purchase purchase : purchases) {
			if (purchase != null)
				purchaseTotal += purchase.getPrice();
		}
		
		if (player.cash < purchaseTotal) {
			flash.error("The purchases could not be bought: insufficient cash amount");
			params.flash();
			foodMarket();
		}
	}

}
