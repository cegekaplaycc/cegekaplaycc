package securesocial;

import models.Player;
import securesocial.provider.ProviderType;
import securesocial.provider.SocialUser;
import securesocial.provider.UserId;

public class SocialUserFactory {

	public static SocialUser create(Player player) {
		SocialUser socialUser = new SocialUser();

		socialUser.id = createUserId(player.userId, player.providerType);
		socialUser.displayName = player.displayName;
		socialUser.email = player.email;
		socialUser.avatarUrl = player.avatarUrl;
		socialUser.lastAccess = player.lastAccess;
		socialUser.authMethod = player.authMethod;
		socialUser.token = player.token;
		socialUser.secret = player.secret;
		socialUser.accessToken = player.accessToken;
		socialUser.password = player.password;
		socialUser.isEmailVerified = player.isEmailVerified;

		return socialUser;
	}

	private static UserId createUserId(String id, ProviderType provider) {
		UserId userId = new UserId();

		userId.id = id;
		userId.provider = provider;

		return userId;
	}

}
