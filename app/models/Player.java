package models;

import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Sets.newHashSet;
import static models.stable.Box.createBoxWithRandomHorse;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import models.stable.Box;
import models.stock.Stock;
import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.Model;
import securesocial.provider.AuthenticationMethod;
import securesocial.provider.ProviderType;
import securesocial.provider.SocialUser;
import securesocial.provider.UserId;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

@Entity
public class Player extends Model {

	@Required
	public String userId;

	@Required
	@Enumerated(EnumType.STRING)
	public ProviderType providerType;

	@OneToMany
	public Set<Box> boxes = newHashSet();

	@Required
	public String displayName;

	@Email
	public String email;

	public String avatarUrl;

	public Date lastAccess;

	@Enumerated(EnumType.STRING)
	public AuthenticationMethod authMethod;

	public String token;

	public String secret;

	public String accessToken;

	public String password;

	public boolean isEmailVerified;

	public int cash = 100;

	@OneToOne(cascade = CascadeType.PERSIST)
	public Stock stock = new Stock();

	public String UUID;

	public static Player findByUserId(UserId userId) {
		return find("byUserIdAndProviderType", userId.id, userId.provider)
				.first();
	}

	public static void deletePendingActivations() {
		Player.delete("UUID != null");
	}

	public static Player create(SocialUser user) {
		Player player = new Player();

		player.userId = user.id.id;
		player.providerType = user.id.provider;
		player.displayName = user.displayName;
		player.email = user.email;
		player.avatarUrl = user.avatarUrl;
		player.lastAccess = user.lastAccess;
		player.authMethod = user.authMethod;
		player.token = user.token;
		player.secret = user.secret;
		player.accessToken = user.accessToken;
		player.password = user.password;
		player.isEmailVerified = user.isEmailVerified;

		player.boxes.add(createBoxWithRandomHorse());

		return player;
	}

	public Set<Race> getPastEnteredRaces() {
		Set<Race> races = newHashSet();
		for (Horse horse : getHorses()) {
			races.addAll(horse.getPastEnteredRaces());
		}
		return races;
	}

	public void buy(List<Purchase> purchases) {
		stock.buy(purchases);
		payPurchases(purchases);
		this.save();
	}

	private void payPurchases(List<Purchase> purchases) {
		int amountToBePaid = 0;
		for (Purchase purchase : purchases) {
			if (purchase != null) {
    			amountToBePaid+= purchase.getPrice();
    		}
		}
		cash = cash - amountToBePaid;
	}

	public Set<Horse> getHorses() {
		return newHashSet(transform(filter(boxes, emptyBoxesFilter()),
				fromBoxToHorse()));
	}

	private Predicate<Box> emptyBoxesFilter() {
		return new Predicate<Box>() {

			@Override
			public boolean apply(Box box) {
				return box.horse != null;
			}
		};
	}

	private Function<Box, Horse> fromBoxToHorse() {
		return new Function<Box, Horse>() {

			@Override
			public Horse apply(Box box) {
				return box.horse;
			}
		};
	}

	public void buildNewBox() {
		boxes.add(Box.createBox());
		this.save();
	}
}
