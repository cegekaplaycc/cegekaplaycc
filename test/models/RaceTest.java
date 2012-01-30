package models;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import play.test.UnitTest;

public class RaceTest {
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	private Race race;
	
	@Before
	public void setup() {
		race =  new Race();
	}
	
	@Test
	public void shouldBeAbleToDetermineAvailableSlots_whenNoHorsesEnteredTheRace() {
		int availableSlots = race.getAvailableSlots();
		
		assertThat(availableSlots).isEqualTo(Race.MAX_AVAILABLE_SLOTS);
	}
	
	@Test
	public void shouldBeAbleToDetermineAvailableSlots_whenHorsesEnteredTheRace() {
		race.enter(new Horse());
		
		int availableSlots = race.getAvailableSlots();
		
		assertThat(availableSlots).isEqualTo(7);
	}
	
	@Test
	public void shouldBeAbleToDetermineAvailableSlots_AllSlotsFull() {
		enterHorses(8);
		
		int availableSlots = race.getAvailableSlots();
		
		assertThat(availableSlots).isEqualTo(0);
	}

	@Test
	public void shouldBeAbleToEnterAHorse(){
		Horse horse = new Horse();
		race.enter(horse);
		
		assertThat(race.horses).containsOnly(horse);
	}
	
	@Test
	public void shouldNotBeAbleToEnterMoreHorsesThanTheMaximumAvailableSlots(){
		enterHorses(8);
		
		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage(Race.MAX_AVAILABLE_SLOTS_EXCEEDED);
		
		race.enter(new Horse());
	}

	private void enterHorses(int count) {
		for (int i = 0; i < count; i++) {
			race.enter(new Horse());
		}
	}
}

