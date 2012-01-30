package models;

import org.fest.assertions.Assertions;
import org.junit.Test;

import play.db.jpa.JPABase;

public class PlayerIntegrationTest extends IntegrationTestCase {

	@Test
	public void canBePersisted() {
		Player player = new Player("ID", "jos");
		player.save();
		
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
		
		
		Player refreshedPlayer = player.refresh();
		Assertions.assertThat(refreshedPlayer.getHorses()).hasSize(2);
		Assertions.assertThat(refreshedPlayer.getId()).isEqualTo(player.getId());
		
	}
}
