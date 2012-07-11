package models;

import static domainservices.ServiceLocator.randomHorsesBreeder;
import models.stock.Stock;
import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.Model;
import securesocial.provider.AuthenticationMethod;
import securesocial.provider.ProviderType;
import securesocial.provider.SocialUser;
import securesocial.provider.UserId;

import javax.persistence.*;

import com.google.common.collect.Sets;

import domainservices.RandomHorsesBreeder;
import domainservices.ServiceLocator;

import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
public class Player extends Model {

    @Required
    public String userId;

    @Required
    @Enumerated(EnumType.STRING)
    public ProviderType providerType;

    @OneToMany
    public Set<Horse> horses = Sets.newHashSet();

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

    @OneToOne(cascade=CascadeType.PERSIST)
	public Stock stock = new Stock();

	public String UUID;

    public static Player findByUserId(UserId userId) {
        return find("byUserIdAndProviderType", userId.id, userId.provider)
                .first();
    }

    public static void deletePendingActivations() {
        Player.delete("UUID != null");
    }

    public Set<Horse> getHorses() {
        return horses;
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

        Horse horse = randomHorsesBreeder.createRandomHorse().save();
        player.horses.add(horse);

        return player;
    }

    public Set<Race> getPastEnteredRaces() {
        Set<Race> races = Sets.newHashSet();
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
    		if(purchase != null) {
    			amountToBePaid+= Integer.parseInt(purchase.amount)*purchase.foodId.price;
    		}
		}
    	cash = cash - amountToBePaid;
	}
}
