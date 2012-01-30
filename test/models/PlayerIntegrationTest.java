package models;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class PlayerIntegrationTest extends IntegrationTestCase {

	@Test
	public void canBePersisted() {
		Player player = new Player("ID", "jos");
		player.save();

		clearEntityContext();
		Assertions.assertThat(Player.findAll()).hasSize(1);
		Player refreshed = (Player) Player.findAll().iterator().next();

		Assertions.assertThat(refreshed.getUserId()).isEqualTo("ID");
		Assertions.assertThat(refreshed.getName()).isEqualTo("jos");
	}

	@Test
	public void canHavePersistedHorses() {
		Player player = new Player("ID", "jos");
		player.save();

		Horse horse1 = new Horse("horse1");
		Horse horse2 = new Horse("horse2");
		horse1.save();
		horse2.save();

		player.addHorse(horse1);
		player.addHorse(horse2);

		clearEntityContext();
		Player refreshedPlayer = Player.findById(player.getId());
		Assertions.assertThat(refreshedPlayer.getHorses()).hasSize(2);
		Assertions.assertThat(refreshedPlayer.getId()).isEqualTo(player.getId());
	}

	@Test
	public void addHorse() {
		Player player = new Player("azerty");
		Horse horse1 = new Horse("horse1").save();
		Horse horse2 = new Horse("horse2").save();
		player.addHorse(horse1);
		player.addHorse(horse2);

		Assertions.assertThat(player.getHorses()).containsOnly(horse1, horse2);
	}
}
