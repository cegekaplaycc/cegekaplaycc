package models;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;

import play.test.UnitTest;

public class RaceTest {
	
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
		race.enter(new Horse());
		race.enter(new Horse());
		race.enter(new Horse());
		race.enter(new Horse());
		race.enter(new Horse());
		race.enter(new Horse());
		race.enter(new Horse());
		race.enter(new Horse());
		
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
	public void shouldNotBeAbleToEnterMoreHorsesThanTheMaximumAvailableHorses(){
		
	}
}
