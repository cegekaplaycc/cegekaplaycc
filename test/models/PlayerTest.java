package models;

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
import models.Player;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;
import securesocial.SocialUserFactory;
import securesocial.provider.ProviderType;
import securesocial.provider.SocialUser;
import securesocial.provider.UserId;

public class PlayerTest extends UnitTest {

	@Before
	public void setup() {
		Fixtures.deleteAllModels();
	}

	@Test
	public void createANewPlayer_DisplayNameAndUserIdAndProviderTypeRequired() {
		String expectedDisplayName = "myPlayersDisplayName";

		aPlayer().withDisplayName(expectedDisplayName).buildAndPersist();

		Player actualPlayer = Player.find("byDisplayName", expectedDisplayName)
				.first();

		assertEquals(expectedDisplayName, actualPlayer.displayName);
	}

	@Test
	public void createANewPlayer_DisplayNameNull_ReturnsFalse() {
		Player player = aPlayer().withDisplayName(null).build();

		assertFalse(player.validateAndSave());
	}

	@Test
	public void createANewPlayer_DisplayNameEmpty_ReturnsFalse() {
		Player player = aPlayer().withDisplayName("").build();

		assertFalse(player.validateAndSave());
	}

	@Test
	public void createANewPlayer_UserIdNull_ReturnsFalse() {
		Player player = aPlayer().withUserId(null).build();

		assertFalse(player.validateAndSave());
	}

	@Test
	public void createANewPlayer_UserIdEmpty_ReturnsFalse() {
		Player player = aPlayer().withUserId("").build();

		assertFalse(player.validateAndSave());
	}

	@Test
	public void createANewPlayer_ProviderTypeNull_ReturnsFalse() {
		Player player = aPlayer().withProviderType(null).build();

		assertFalse(player.validateAndSave());
	}

	@Test
	public void findByUserId() {
		String id = "myUserId";
		ProviderType providerType = ProviderType.twitter;

		aPlayer().withUserId(id).withProviderType(providerType)
				.buildAndPersist();

		UserId userId = new UserId();
		userId.id = id;
		userId.provider = providerType;

		Player actualPlayer = Player.findByUserId(userId);

		assertNotNull(actualPlayer);
		assertEquals(id, actualPlayer.userId);
		assertEquals(providerType, actualPlayer.providerType);
	}

	@Test
	public void findByUserId_UserIdNotFound_ReturnsNull() {
		ProviderType providerType = ProviderType.twitter;

		aPlayer().withUserId("myUserId").withProviderType(providerType)
				.buildAndPersist();

		UserId userId = new UserId();
		userId.id = "myOtherUserId";
		userId.provider = providerType;

		Player actualPlayer = Player.findByUserId(userId);

		assertNull(actualPlayer);
	}

	@Test
	public void findByUserId_ProviderTypeNotFound_ReturnsNull() {
		String id = "myUserId";
		aPlayer().withUserId(id).withProviderType(ProviderType.twitter)
				.buildAndPersist();

		UserId userId = new UserId();
		userId.id = id;
		userId.provider = ProviderType.facebook;

		Player actualPlayer = Player.findByUserId(userId);

		assertNull(actualPlayer);
	}

	@Test
	public void findByUserId_UserIdAndProviderTypeNotFound_ReturnsNull() {
		aPlayer().withUserId("myUserId").withProviderType(ProviderType.twitter)
				.buildAndPersist();

		UserId userId = new UserId();
		userId.id = "myOtherUserId";
		userId.provider = ProviderType.facebook;

		Player actualPlayer = Player.findByUserId(userId);

		assertNull(actualPlayer);
	}

	@Test
	public void create_ReturnsPlayer() {
		SocialUser socialUser = SocialUserFactory.create(aPlayer().build());

		Player actualPlayer = Player.create(socialUser);

		assertEquals(PLAYER_USER_ID, actualPlayer.userId);
		assertEquals(PLAYER_USER_PROVIDER_TYPE, actualPlayer.providerType);
		assertEquals(PLAYER_DISPLAY_NAME, actualPlayer.displayName);
		assertEquals(PLAYER_EMAIL, actualPlayer.email);
		assertEquals(PLAYER_AVATAR_URL, actualPlayer.avatarUrl);
		assertEquals(PLAYER_LAST_ACCESS, actualPlayer.lastAccess);
		assertEquals(PLAYER_AUTH_METHOD, actualPlayer.authMethod);
		assertEquals(PLAYER_TOKEN, actualPlayer.token);
		assertEquals(PLAYER_SECRET, actualPlayer.secret);
		assertEquals(PLAYER_ACCESS_TOKEN, actualPlayer.accessToken);
		assertEquals(PLAYER_PASSWORD, actualPlayer.password);
		assertEquals(PLAYER_EMAIL_VERIFIED, actualPlayer.isEmailVerified);
	}

}
