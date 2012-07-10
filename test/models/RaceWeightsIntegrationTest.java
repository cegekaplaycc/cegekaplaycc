package models;

import assertion.HoldYourHorseAssertions;
import org.junit.Test;
import play.db.jpa.JPA;

public class RaceWeightsIntegrationTest extends IntegrationTest {

	@Test
	public void getAutoSetsDefaultValuesInDB() {
		RaceWeights weights = RaceWeights.get();
		JPA.em().clear();
		
		HoldYourHorseAssertions.assertThat(RaceWeights.findAll()).containsOnly(weights);
	}
	
	@Test
	public void getAndSetOverwritesDefaultValues() {
		RaceWeights.get();
		RaceWeights.set(10, 20, 30);
		JPA.em().clear();
		
		RaceWeights refreshed = RaceWeights.get();
		JPA.em().clear();
		
		HoldYourHorseAssertions.assertThat(refreshed.getTrainingLevelMod()).isEqualTo(10);
		HoldYourHorseAssertions.assertThat(refreshed.getFitnessLevelMod()).isEqualTo(20);
		HoldYourHorseAssertions.assertThat(refreshed.getRandomFactorMod()).isEqualTo(30);
		HoldYourHorseAssertions.assertThat(RaceWeights.findAll()).containsOnly(refreshed);
	}
}
