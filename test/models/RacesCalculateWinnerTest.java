package models;

import assertion.HoldYourHorseAssertions;
import litmus.unit.UnitTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static models.RaceBuilder.aRace;

public class RacesCalculateWinnerTest extends UnitTest {

	@Before
	public void setupFixedRaceWeights() {
		RaceWeights.set(1, 1, 1);
	}
	
	@After
	public void resetRaceWeights() {
		RaceWeights.reset();
	}
	
	@Test
	public void winnerIsEmptyWhenNoHorsesInRace() {
		Race race = aRace().build();
		race.calculateWinner();
		
		HoldYourHorseAssertions.assertThat(race.winner).isNull();
	}
	
	@Test
	public void winnerIsOnlyHorseEnteringTheRace() {
		Horse horse = HorseBuilder.aHorse().build();
		Race race = aRace().withHorses(horse).build();
		race.calculateWinner();
		
		HoldYourHorseAssertions.assertThat(race.winner).isSameAs(horse);
	}
	
	@Test
	public void winnerIsHorseWithHighestScore() {
		Horse winningHorse = HorseBuilder.aHorse()
			.withFitness(10)
			.withRandomFactorForScoring(10)
			.withTraining(10)
			.withName("winner")
			.withId(1L)
			.build();
		Horse losingHorse1 = HorseBuilder.aHorse()
			.withFitness(5)
			.withRandomFactorForScoring(10)
			.withTraining(10)
			.withName("loser1")
			.withId(2L)
			.build();
		Horse losingHorse2 = HorseBuilder.aHorse()
			.withFitness(5)
			.withRandomFactorForScoring(5)
			.withTraining(5)
			.withName("loser2")
			.withId(3L)
			.build();
		
		Race race = aRace()
			.withHorses(winningHorse, losingHorse1, losingHorse2)
			.build();
		race.calculateWinner();
		
		HoldYourHorseAssertions.assertThat(race.winner).isEqualTo(winningHorse);
	}

}
