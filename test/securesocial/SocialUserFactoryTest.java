package securesocial;

import static models.PlayerTestBuilder.PLAYER_ACCESS_TOKEN;
import static models.PlayerTestBuilder.PLAYER_AUTH_METHOD;
import static models.PlayerTestBuilder.PLAYER_AVATAR_URL;
import static models.PlayerTestBuilder.PLAYER_DISPLAY_NAME;
import static models.PlayerTestBuilder.PLAYER_EMAIL;
import static models.PlayerTestBuilder.PLAYER_EMAIL_VERIFIED;
import static models.PlayerTestBuilder.PLAYER_LAST_ACCESS;
import static models.PlayerTestBuilder.PLAYER_PASSWORD;
import static models.PlayerTestBuilder.PLAYER_SECRET;
import static models.PlayerTestBuilder.PLAYER_TOKEN;
import static models.PlayerTestBuilder.PLAYER_USER_ID;
import static models.PlayerTestBuilder.PLAYER_USER_PROVIDER_TYPE;
import static models.PlayerTestBuilder.aPlayer;
import static org.junit.Assert.assertEquals;
import static securesocial.SocialUserFactory.create;

import org.junit.Test;

import securesocial.provider.SocialUser;

public class SocialUserFactoryTest {

	@Test
	public void create_returnsSocialUser() {
		SocialUser socialUser = create(aPlayer().build());

		assertEquals(PLAYER_USER_ID, socialUser.id.id);
		assertEquals(PLAYER_USER_PROVIDER_TYPE, socialUser.id.provider);
		assertEquals(PLAYER_DISPLAY_NAME, socialUser.displayName);
		assertEquals(PLAYER_EMAIL, socialUser.email);
		assertEquals(PLAYER_AVATAR_URL, socialUser.avatarUrl);
		assertEquals(PLAYER_LAST_ACCESS, socialUser.lastAccess);
		assertEquals(PLAYER_AUTH_METHOD, socialUser.authMethod);
		assertEquals(PLAYER_TOKEN, socialUser.token);
		assertEquals(PLAYER_SECRET, socialUser.secret);
		assertEquals(PLAYER_ACCESS_TOKEN, socialUser.accessToken);
		assertEquals(PLAYER_PASSWORD, socialUser.password);
		assertEquals(PLAYER_EMAIL_VERIFIED, socialUser.isEmailVerified);
	}

}
