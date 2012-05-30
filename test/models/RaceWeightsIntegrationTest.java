package models;

import assertion.PlayAssertions;
import org.junit.Test;
import play.db.jpa.JPA;

public class RaceWeightsIntegrationTest extends IntegrationTest {

	@Test
	public void getAutoSetsDefaultValuesInDB() {
		RaceWeights weights = RaceWeights.get();
		JPA.em().clear();
		
		PlayAssertions.assertThat(RaceWeights.findAll()).containsOnly(weights);
	}
	
	@Test
	public void getAndSetOverwritesDefaultValues() {
		RaceWeights.get();
		RaceWeights.set(10, 20, 30);
		JPA.em().clear();
		
		RaceWeights refreshed = RaceWeights.get();
		JPA.em().clear();
		
		PlayAssertions.assertThat(refreshed.getTrainingLevelMod()).isEqualTo(10);
		PlayAssertions.assertThat(refreshed.getFitnessLevelMod()).isEqualTo(20);
		PlayAssertions.assertThat(refreshed.getRandomFactorMod()).isEqualTo(30);
		PlayAssertions.assertThat(RaceWeights.findAll()).containsOnly(refreshed);
	}
}
