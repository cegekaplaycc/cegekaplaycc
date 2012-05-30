package securesocial;

import litmus.unit.UnitTest;
import org.junit.Test;
import securesocial.provider.SocialUser;

import static models.PlayerBuilder.*;
import static securesocial.SocialUserFactory.create;

public class SocialUserFactoryTest extends UnitTest {

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
