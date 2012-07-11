package models;

import litmus.unit.UnitTest;
import org.fest.assertions.Assertions;
import org.junit.After;
import org.junit.Test;

public class HorseTest extends UnitTest {

	@After
	public void resetWeights() {
		RaceWeights.reset();
	}
	
	@Test
	public void calculateRaceScore_BasedOnTrainingAndFitness() {
		RaceWeights.set(0.5, 0.5, 0.5);
		Horse horse = HorseBuilder.aHorse()
			.withFitness(2)
			.withTraining(4)
			.withRandomFactorForScoring(3)
			.build();
		
		Assertions.assertThat(horse.calculateRaceScore()).isEqualTo(4.5);
	}
	
	@Test
	public void calculateRaceScore_DifferentWeightsForEachParameter() {
		RaceWeights.set(0.5, 1, 2);
		Horse horse = HorseBuilder.aHorse()
			.withFitness(2)
			.withTraining(4)
			.withRandomFactorForScoring(3)
			.build();
		
		Assertions.assertThat(horse.calculateRaceScore()).isEqualTo(10);
	}
	
	@Test
	public void calculateRaceScore_FitnessIsZero() {
		RaceWeights.set(0.5, 1, 2);
		Horse horse = HorseBuilder.aHorse()
			.withFitness(0)
			.withTraining(4)
			.withRandomFactorForScoring(3)
			.build();
		
		Assertions.assertThat(horse.calculateRaceScore()).isEqualTo(8);
	}
	
	@Test
	public void calculateRaceScore_TrainingIsZero() {
		RaceWeights.set(0.5, 1, 2);
		Horse horse = HorseBuilder.aHorse()
			.withFitness(2)
			.withTraining(0)
			.withRandomFactorForScoring(3)
			.build();
		
		Assertions.assertThat(horse.calculateRaceScore()).isEqualTo(8);
	}
	
	@Test
	public void calculateRaceScore_RandomFactorIsZero() {
		RaceWeights.set(0.5, 1, 2);
		Horse horse = HorseBuilder.aHorse()
			.withFitness(2)
			.withTraining(4)
			.withRandomFactorForScoring(0)
			.build();
		
		Assertions.assertThat(horse.calculateRaceScore()).isEqualTo(4);
	}
}
