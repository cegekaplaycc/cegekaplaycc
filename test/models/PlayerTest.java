package models;

import static com.google.common.collect.Iterables.getOnlyElement;
import static domainservices.ServiceLocator.randomHorsesBreeder;
import static models.PlayerBuilder.PLAYER_ACCESS_TOKEN;
import static models.PlayerBuilder.PLAYER_AUTH_METHOD;
import static models.PlayerBuilder.PLAYER_AVATAR_URL;
import static models.PlayerBuilder.PLAYER_DISPLAY_NAME;
import static models.PlayerBuilder.PLAYER_EMAIL;
import static models.PlayerBuilder.PLAYER_EMAIL_VERIFIED;
import static models.PlayerBuilder.PLAYER_LAST_ACCESS;
import static models.PlayerBuilder.PLAYER_PASSWORD_HASHED;
import static models.PlayerBuilder.PLAYER_SECRET;
import static models.PlayerBuilder.PLAYER_TOKEN;
import static models.PlayerBuilder.PLAYER_USER_ID;
import static models.PlayerBuilder.PLAYER_USER_PROVIDER_TYPE;
import static models.PlayerBuilder.aPlayer;
import static org.mockito.Mockito.when;

import com.google.common.collect.Iterables;
import litmus.unit.UnitTest;
import models.stable.Box;
import models.stable.BoxBuilder;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import securesocial.SocialUserFactory;
import securesocial.provider.SocialUser;
import domainservices.ServiceMocker;

public class PlayerTest extends UnitTest {

	@Rule
	public ServiceMocker serviceMocker = ServiceMocker.create();

	private Horse horse = HorseBuilder.aHorse().build();

	@Before
	public void setUp() {
		serviceMocker.mockRandomHorseBreeder();

		when(randomHorsesBreeder.createRandomHorse()).thenReturn(horse);
	}

	@Test
	public void create_ReturnsPlayer() {
		SocialUser socialUser = SocialUserFactory.create(aPlayer().build());

		Player actualPlayer = Player.create(socialUser);

		assertThat(actualPlayer.userId).isEqualTo(PLAYER_USER_ID);
		assertThat(actualPlayer.providerType).isEqualTo(
				PLAYER_USER_PROVIDER_TYPE);
		assertThat(actualPlayer.displayName).isEqualTo(PLAYER_DISPLAY_NAME);
		assertThat(actualPlayer.email).isEqualTo(PLAYER_EMAIL);
		assertThat(actualPlayer.avatarUrl).isEqualTo(PLAYER_AVATAR_URL);
		assertThat(actualPlayer.lastAccess).isEqualTo(PLAYER_LAST_ACCESS);
		assertThat(actualPlayer.authMethod).isEqualTo(PLAYER_AUTH_METHOD);
		assertThat(actualPlayer.token).isEqualTo(PLAYER_TOKEN);
		assertThat(actualPlayer.secret).isEqualTo(PLAYER_SECRET);
		assertThat(actualPlayer.accessToken).isEqualTo(PLAYER_ACCESS_TOKEN);
		assertThat(actualPlayer.password).isEqualTo(PLAYER_PASSWORD_HASHED);
		assertThat(actualPlayer.isEmailVerified).isEqualTo(
				PLAYER_EMAIL_VERIFIED);
	}

	@Test
	public void create_AddsOneBox() {
		SocialUser socialUser = SocialUserFactory.create(aPlayer().build());
		Player actualPlayer = Player.create(socialUser);
        assertThat(getOnlyElement(actualPlayer.boxes).horse).isNull();
	}

	@Test
	public void getHorses_returnsHorsesOfBoxes() {
		Horse horse1 = Mockito.mock(Horse.class);
		Horse horse2 = Mockito.mock(Horse.class);

		Box box1 = BoxBuilder.aBox().withHorse(horse1).build();
		Box box2 = BoxBuilder.aBox().withHorse(horse2).build();
		Box box3 = BoxBuilder.aBox().build();

		Player player = aPlayer().withBoxes(box1, box2, box3).build();

		assertThat(player.getHorses()).containsOnly(horse1, horse2);
	}

	@Test
	public void hasEnoughCash_true() {
		Player player = aPlayer().withCash(10).build();

		assertThat(player.hasEnoughCash(10)).isTrue();
	}

	@Test
	public void hasEnoughCash_false() {
		Player player = aPlayer().withCash(9).build();

		assertThat(player.hasEnoughCash(10)).isFalse();
	}

}
