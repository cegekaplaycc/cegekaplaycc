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
		race.enter(new Horse("horse 1"));
		
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
		Horse horse = new Horse("horse 1");
		race.enter(horse);
		
		assertThat(race.horses).containsOnly(horse);
	}
	
	@Test
	public void shouldNotBeAbleToEnterMoreHorsesThanTheMaximumAvailableSlots(){
		enterHorses(8);
		
		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage(Race.MAX_AVAILABLE_SLOTS_EXCEEDED);
		
		race.enter(new Horse("horse 9"));
	}

	@Test
	public void startRaceShouldBeAbleToDetermineAWinner() {
		assertThat(race.winner).isNull();
		
		enterHorses(3);
		race.start();
		
		assertThat(race.winner).isIn(race.horses);
	}
	
	@Test
	public void shouldNotBeAbleToStartRaceWhenLessThanMinimumAmountOfHorsesAreEntered(){
		enterHorses(Race.MIN_HORSES_ENTERED_TO_START_RACE - 1);
		
		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage(Race.LESS_THAN_MIN_AMOUNT_HORSES_ENTERED_TO_START_RACE);
		
		race.start();
	}
	
	@Test
	public void shouldBeAbleToDetermineIfRaceIsReadyToStart() {
		enterHorses(Race.MIN_HORSES_ENTERED_TO_START_RACE);
		
		assertThat(race.readyToStart()).isTrue();
	}
	
	@Test
	public void shouldBeAbleToDetermineIfRaceIsReadyToStart_lessThanTheAmountNeeded() {
		enterHorses(Race.MIN_HORSES_ENTERED_TO_START_RACE - 1);
		
		assertThat(race.readyToStart()).isFalse();
	}
	
	private void enterHorses(int count) {
		for (int i = 0; i < count; i++) {
			race.enter(new Horse("horse " + i + 1));
		}
	}
}

