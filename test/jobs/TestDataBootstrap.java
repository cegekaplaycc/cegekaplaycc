package jobs;

import models.Horse;
import models.HorseBuilder;
import models.Player;
import models.RaceBuilder;

import org.powermock.reflect.Whitebox;

import play.Play;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.libs.Crypto;
import securesocial.provider.AuthenticationMethod;
import securesocial.provider.ProviderType;
import securesocial.provider.SocialUser;
import securesocial.provider.UserId;
import securesocial.provider.UserService;

import com.google.common.collect.Sets;

@OnApplicationStart
public class TestDataBootstrap extends Job {

	@Override
	public void doJob() throws Exception {
		if (Play.mode.isDev()) {
			Player player = createUser();
			Horse horse1 = new HorseBuilder().withName("Tom").build().save();
			Horse horse2 = new HorseBuilder().withName("Matti").build().save();
			Whitebox.setInternalState(player, "horses", Sets.newHashSet(horse1, horse2));
			player.save();
			new RaceBuilder().withHorses(horse1).withStartTimeInPast().build().save();
			new RaceBuilder().withHorses(horse2).withStartTimeInPast().build().save();
		}
	}

	private Player createUser() {
		SocialUser socialUser = new SocialUser();
		socialUser.displayName = "matti";
		socialUser.password = Crypto.passwordHash("matti");
		UserId userId = new UserId();
		userId.id = "matti";
		userId.provider = ProviderType.userpass;
		socialUser.id = userId;
		socialUser.authMethod = AuthenticationMethod.USER_PASSWORD;
		socialUser.isEmailVerified = true;
		UserService.save(socialUser);
		return Player.findByUserId(userId);
	}

}
