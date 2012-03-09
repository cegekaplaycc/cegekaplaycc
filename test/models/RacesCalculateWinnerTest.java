package models;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import assertion.PlayAssertions;

import play.test.UnitTest;

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
		Race race = new RaceBuilder().build();
		race.calculateWinner();
		
		PlayAssertions.assertThat(race.winner).isNull();
	}
	
	@Test
	public void winnerIsOnlyHorseEnteringTheRace() {
		Horse horse = new HorseBuilder().build();
		Race race = new RaceBuilder().withHorses(horse).build();
		race.calculateWinner();
		
		PlayAssertions.assertThat(race.winner).isSameAs(horse);
	}
	
	@Test
	public void winnerIsHorseWithHighestScore() {
		Horse winningHorse = new HorseBuilder()
			.withFitness(10)
			.withRandomFactorForScoring(10)
			.withTraining(10)
			.withName("winner")
			.withId(1L)
			.build();
		Horse losingHorse1 = new HorseBuilder()
			.withFitness(5)
			.withRandomFactorForScoring(10)
			.withTraining(10)
			.withName("loser1")
			.withId(2L)
			.build();
		Horse losingHorse2 = new HorseBuilder()
			.withFitness(5)
			.withRandomFactorForScoring(5)
			.withTraining(5)
			.withName("loser2")
			.withId(3L)
			.build();
		
		Race race = new RaceBuilder()
			.withHorses(winningHorse, losingHorse1, losingHorse2)
			.build();
		race.calculateWinner();
		
		PlayAssertions.assertThat(race.winner).isEqualTo(winningHorse);
	}

}
