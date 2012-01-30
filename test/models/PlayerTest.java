package models;

import org.fest.assertions.Assertions;
import org.junit.Test;

import play.test.UnitTest;

public class PlayerTest extends UnitTest{
	
	@Test
	public void addHorse() {
		Player player = new Player("azerty");
		Horse horse1 = new Horse("horse1");
		Horse horse2 = new Horse("horse2");
		player.addHorse(horse1);
		player.addHorse(horse2);
		
		Assertions.assertThat(player.getHorses()).containsOnly(horse1, horse2);
	}

}
