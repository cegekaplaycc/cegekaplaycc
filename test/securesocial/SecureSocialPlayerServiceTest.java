package securesocial;

import static models.PlayerTestBuilder.aPlayer;
import static securesocial.provider.ProviderType.facebook;

import java.util.List;

import models.Player;

import org.junit.Before;
import org.junit.Test;

import play.db.jpa.JPA;
import play.test.Fixtures;
import play.test.UnitTest;
import securesocial.SecureSocialPlayerService;
import securesocial.SocialUserFactory;
import securesocial.provider.ProviderType;
import securesocial.provider.SocialUser;
import securesocial.provider.UserId;

public class SecureSocialPlayerServiceTest extends UnitTest {

	private SecureSocialPlayerService service;

	@Before
	public void setup() {
		Fixtures.deleteAllModels();
		service = new SecureSocialPlayerService();
	}

	@Test
	public void find_PlayerFound_ReturnPlayer() {
		String id = "myUserId";
		ProviderType providerType = facebook;
		aPlayer().withUserId(id).withProviderType(providerType)
				.buildAndPersist();

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
		aPlayer().withUserId("myUserId").withProviderType(facebook)
				.buildAndPersist();

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
		Player player = aPlayer().build();
		SocialUser socialUser = SocialUserFactory.create(player);
		UserId userId = socialUser.id;

		List<Player> players = Player.find("byUserIdAndProviderType",
				userId.id, userId.provider).fetch();
		assertTrue(players.isEmpty());

		service.save(socialUser);

		JPA.em().flush();
		JPA.em().clear();

		players = Player.find("byUserIdAndProviderType", userId.id,
				userId.provider).fetch();
		assertEquals(1, players.size());
	}

	@Test
	public void save_PlayerDoesExist_PlayerNotSavedAgain() {
		Player player = aPlayer().buildAndPersist();
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
		Player player = aPlayer().buildAndPersist();
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
				.buildAndPersist();

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
		aPlayer().withUUID("UUID").buildAndPersist();
		aPlayer().withUUID("UUID").buildAndPersist();
		aPlayer().withUUID("UUID").buildAndPersist();
		aPlayer().withUUID("UUID").buildAndPersist();
		aPlayer().withUUID("UUID").buildAndPersist();

		service.deletePendingActivations();

		JPA.em().flush();
		JPA.em().clear();

		List<Player> foundPlayers = Player.find("UUID != null").fetch();
		assertTrue(foundPlayers.isEmpty());
	}
}
