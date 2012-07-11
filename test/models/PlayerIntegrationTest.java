package models;

import static models.PlayerBuilder.aPlayer;

import java.util.Arrays;
import java.util.List;

import models.stable.Box;
import models.stable.BoxBuilder;
import models.stock.Food;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;

import securesocial.provider.ProviderType;
import securesocial.provider.UserId;

public class PlayerIntegrationTest extends IntegrationTest {

	@Before
	public void initHorseNames() {
		new HorseNamePrefix("Windy").save();
		new HorseNameSuffix("City").save();
	}

	@Test
	public void createANewPlayer_DisplayNameAndUserIdAndProviderTypeRequired() {
		String expectedDisplayName = "myPlayersDisplayName";

		aPlayer().withDisplayName(expectedDisplayName).build()
				.validateAndSave();

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

		aPlayer().withUserId(id).withProviderType(providerType).build()
				.validateAndSave();

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

		aPlayer().withUserId("myUserId").withProviderType(providerType).build()
				.validateAndSave();

		UserId userId = new UserId();
		userId.id = "myOtherUserId";
		userId.provider = providerType;

		Player actualPlayer = Player.findByUserId(userId);

		Assertions.assertThat(actualPlayer).isNull();
	}

	@Test
	public void findByUserId_ProviderTypeNotFound_ReturnsNull() {
		String id = "myUserId";
		aPlayer().withUserId(id).withProviderType(ProviderType.twitter).build()
				.validateAndSave();

		UserId userId = new UserId();
		userId.id = id;
		userId.provider = ProviderType.facebook;

		Player actualPlayer = Player.findByUserId(userId);

		Assertions.assertThat(actualPlayer).isNull();
	}

	@Test
	public void findByUserId_UserIdAndProviderTypeNotFound_ReturnsNull() {
		aPlayer().withUserId("myUserId").withProviderType(ProviderType.twitter)
				.build().validateAndSave();

		UserId userId = new UserId();
		userId.id = "myOtherUserId";
		userId.provider = ProviderType.facebook;

		Player actualPlayer = Player.findByUserId(userId);

		Assertions.assertThat(actualPlayer).isNull();
	}

	@Test
	public void buyShouldAddItemsToTheStock() {
		Player player = new Player();
		List<Purchase> purchases = Arrays.asList(new PurchaseBuilder()
				.withAmount("14").withFood(Food.CARROTS).build());

		player.buy(purchases);

		assertThat(player.stock.items).hasSize(1);
		assertThat(player.stock.items.iterator().next().amount).isEqualTo(14);
	}

	@Test
	public void buyShouldLowerCash() {
		Player player = new Player();
		List<Purchase> purchases = Arrays.asList(new PurchaseBuilder()
				.withAmount("5").withFood(Food.CARROTS).build());

		player.buy(purchases);

		assertThat(player.cash).isEqualTo(70);
	}

	@Test
	public void buildNewBox() {
		Box box1 = BoxBuilder.aBox().persist();
		Box box2 = BoxBuilder.aBox().persist();
		Box box3 = BoxBuilder.aBox().persist();

		Player player = aPlayer().withBoxes(box1, box2, box3).persist();

		player.buildNewBox();

		assertThat(player.boxes).hasSize(4);

	}

}
