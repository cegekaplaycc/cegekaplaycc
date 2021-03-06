package securesocial;

import litmus.unit.UnitTest;
import models.HorseNamePrefix;
import models.HorseNameSuffix;
import models.Player;
import models.PlayerBuilder;
import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import play.db.jpa.JPA;
import play.test.Fixtures;
import securesocial.provider.ProviderType;
import securesocial.provider.SocialUser;
import securesocial.provider.UserId;

import java.util.List;

import static models.PlayerBuilder.aPlayer;
import static securesocial.provider.ProviderType.facebook;

public class SecureSocialPlayerServiceTest extends UnitTest {

	private CustomSecureSocialPlayerService service;

	@Before
	public void setup() {
		Fixtures.deleteAllModels();
		service = new CustomSecureSocialPlayerService();
	}

	@Test
	public void find_PlayerFound_ReturnPlayer() {
		String id = "myUserId";
		ProviderType providerType = facebook;
		aPlayer().withUserId(id).withProviderType(providerType).save();

		UserId userId = new UserId();
		userId.id = id;
		userId.provider = providerType;

		SocialUser socialUser = service.find(userId);

		JPA.em().flush();
		JPA.em().clear();

		assertNotNull(socialUser);
	}

	@Test
	public void find_PlayerNotFound_ReturnNull() {
		aPlayer().withUserId("myUserId").withProviderType(facebook).save();

		UserId userId = new UserId();
		userId.id = "myOtherUserId";
		userId.provider = facebook;

		SocialUser socialUser = service.find(userId);

		JPA.em().flush();
		JPA.em().clear();

		assertNull(socialUser);
	}

	@Test
	public void save_PlayerDoesNotExist_PlayerSaved() {
        new HorseNamePrefix("Windy").save();
        new HorseNameSuffix("Winder").save();
		Player player = PlayerBuilder.aPlayer().build();
		SocialUser socialUser = SocialUserFactory.create(player);
		UserId userId = socialUser.id;

        Assertions.assertThat(Player.findAll()).isEmpty();

		service.save(socialUser);

		JPA.em().flush();
		JPA.em().clear();

		List<Player> players = Player.find("byUserIdAndProviderType", userId.id, userId.provider).fetch();
		assertEquals(1, players.size());
	}

	@Test
	public void save_PlayerDoesExist_PlayerNotSavedAgain() {
		Player player = aPlayer().save();
		SocialUser socialUser = SocialUserFactory.create(player);
		UserId userId = socialUser.id;

		List<Player> players = Player.find("byUserIdAndProviderType",
				userId.id, userId.provider).fetch();
		assertEquals(1, players.size());

		service.save(socialUser);

		JPA.em().flush();
		JPA.em().clear();

		players = Player.find("byUserIdAndProviderType", userId.id,
				userId.provider).fetch();
		assertEquals(1, players.size());
	}

	@Test
	public void createActivation_FindsPlayerAndAddsUUID_ReturnsUUID() {
		Player player = aPlayer().save();
		SocialUser socialUser = SocialUserFactory.create(player);

		String UUID = service.createActivation(socialUser);

		JPA.em().flush();
		JPA.em().clear();

		Player foundPlayer = Player.findByUserId(socialUser.id);

		assertNotNull(foundPlayer.UUID);
		assertEquals(UUID, foundPlayer.UUID);
	}

	@Test
	public void activate_FindsPlayerWithUUIDSetsEmailVerifiedTrue_ReturnsTrue() {
		String uuid = "myUUID";
		Player player = aPlayer().withEmailVerified(false).withUUID(uuid)
				.save();

		assertFalse(player.isEmailVerified);
		assertTrue(service.activate(uuid));

		JPA.em().flush();
		JPA.em().clear();

		Player foundPlayer = Player.findById(player.id);
		assertEquals(player, foundPlayer);
		assertTrue(foundPlayer.isEmailVerified);
		assertNull(foundPlayer.UUID);
	}

	@Test
	public void activate_FindsNoPlayer_ReturnsFalse() {
		assertFalse(service.activate("myUUID"));
	}

	@Test
	public void deletePendingActivations_DeletesAllPlayersWhereUUIDNotIsNull() {
		aPlayer().withUUID("UUID1").save();
		aPlayer().withUUID("UUID2").save();
		aPlayer().withUUID("UUID3").save();
		aPlayer().withUUID("UUID4").save();
		aPlayer().withUUID("UUID5").save();

		service.deletePendingActivations();

		JPA.em().flush();
		JPA.em().clear();

		List<Player> foundPlayers = Player.find("UUID != null").fetch();
		assertTrue(foundPlayers.isEmpty());
	}
}
