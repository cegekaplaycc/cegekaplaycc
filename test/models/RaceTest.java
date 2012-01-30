package models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import play.test.UnitTest;

public class RaceTest extends UnitTest {
	
	private Race race;
	
	@Before
	public void setup() {
		race =  new Race();
	}
	
	@Test
	public void shouldBeAbleToDetermineAvailableSlots_whenNoHorsesEnteredTheRace() {
		int availableSlots = race.getAvailableSlots();
		
		assertEquals(Race.MAX_AVAILABLE_SLOTS, availableSlots);
	}
	
	@Test
	public void shouldBeAbleToDetermineAvailableSlots_whenHorsesEnteredTheRace() {
		race.enter(new Horse());
		
		int availableSlots = race.getAvailableSlots();
		
		assertEquals(7, availableSlots);
	}
}
