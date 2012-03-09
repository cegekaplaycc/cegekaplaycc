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

import org.fest.assertions.Assertions;
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

		Assertions.assertThat(actualPlayer.displayName).isEqualTo(
				expectedDisplayName);
	}

	@Test
	public void createANewPlayer_DisplayNameNull_ReturnsFalse() {
		Player player = aPlayer().withDisplayName(null).build();

		Assertions.assertThat(player.validateAndSave()).isFalse();
	}

	@Test
	public void createANewPlayer_DisplayNameEmpty_ReturnsFalse() {
		Player player = aPlayer().withDisplayName("").build();

		Assertions.assertThat(player.validateAndSave()).isFalse();
	}

	@Test
	public void createANewPlayer_UserIdNull_ReturnsFalse() {
		Player player = aPlayer().withUserId(null).build();

		Assertions.assertThat(player.validateAndSave()).isFalse();
	}

	@Test
	public void createANewPlayer_UserIdEmpty_ReturnsFalse() {
		Player player = aPlayer().withUserId("").build();

		Assertions.assertThat(player.validateAndSave()).isFalse();
	}

	@Test
	public void createANewPlayer_ProviderTypeNull_ReturnsFalse() {
		Player player = aPlayer().withProviderType(null).build();

		Assertions.assertThat(player.validateAndSave()).isFalse();
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

		Assertions.assertThat(actualPlayer).isNotNull();
		Assertions.assertThat(actualPlayer.userId).isEqualTo(id);
		Assertions.assertThat(actualPlayer.providerType)
				.isEqualTo(providerType);
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

		Assertions.assertThat(actualPlayer).isNull();
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

		Assertions.assertThat(actualPlayer).isNull();
	}

	@Test
	public void findByUserId_UserIdAndProviderTypeNotFound_ReturnsNull() {
		aPlayer().withUserId("myUserId").withProviderType(ProviderType.twitter)
				.buildAndPersist();

		UserId userId = new UserId();
		userId.id = "myOtherUserId";
		userId.provider = ProviderType.facebook;

		Player actualPlayer = Player.findByUserId(userId);

		Assertions.assertThat(actualPlayer).isNull();
	}

	@Test
	public void create_ReturnsPlayer() {
		SocialUser socialUser = SocialUserFactory.create(aPlayer().build());

		Player actualPlayer = Player.create(socialUser);

		Assertions.assertThat(actualPlayer.userId).isEqualTo(PLAYER_USER_ID);
		Assertions.assertThat(actualPlayer.providerType).isEqualTo(
				PLAYER_USER_PROVIDER_TYPE);
		Assertions.assertThat(actualPlayer.displayName).isEqualTo(
				PLAYER_DISPLAY_NAME);
		Assertions.assertThat(actualPlayer.email).isEqualTo(PLAYER_EMAIL);
		Assertions.assertThat(actualPlayer.avatarUrl).isEqualTo(
				PLAYER_AVATAR_URL);
		Assertions.assertThat(actualPlayer.lastAccess).isEqualTo(
				PLAYER_LAST_ACCESS);
		Assertions.assertThat(actualPlayer.authMethod).isEqualTo(
				PLAYER_AUTH_METHOD);
		Assertions.assertThat(actualPlayer.token).isEqualTo(PLAYER_TOKEN);
		Assertions.assertThat(actualPlayer.secret).isEqualTo(PLAYER_SECRET);
		Assertions.assertThat(actualPlayer.accessToken).isEqualTo(
				PLAYER_ACCESS_TOKEN);
		Assertions.assertThat(actualPlayer.password).isEqualTo(PLAYER_PASSWORD);
		Assertions.assertThat(actualPlayer.isEmailVerified).isEqualTo(
				PLAYER_EMAIL_VERIFIED);
	}

	@Test
	public void create_AddsOneHorseToSet() {
		SocialUser socialUser = SocialUserFactory.create(aPlayer().build());

		Player actualPlayer = Player.create(socialUser);

		Assertions.assertThat(actualPlayer.getHorses()).hasSize(1);
	}
}
