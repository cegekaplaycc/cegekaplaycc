package models;

import org.fest.assertions.Assertions;
import org.junit.Test;

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
	
}
