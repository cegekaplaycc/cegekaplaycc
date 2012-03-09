package jobs;
import java.util.List;

import models.Horse;
import models.Race;
import models.RaceBuilder;

import org.junit.Test;

import play.test.UnitTest;
import assertion.PlayAssertions;

public class RaceRunningJobIntegrationTest extends UnitTest {

	@Test
	public void doJobStartsAllRaces() {
		new RaceBuilder().withName("race 1").persist();
		new RaceBuilder().withName("race 2").persist();

		doJob();
		
		List<Race> allRaces = Race.findAll();
		for(Race race : allRaces) {
			PlayAssertions.assertThat(race).hasBeenStarted();
		}
	}
	
	@Test
	public void doJobDoesNotRestartRacesWhichAreAlreadyRun() {
		Horse winningHorse = new Horse("fast fury");
		Race race = new RaceBuilder()
			.withWinner(winningHorse)
			.withHorses(new Horse("losing horse 1"), new Horse("losing horse 2"))
			.persist();
		
		doJob();
		Race refreshedRace = Race.findById(race.getId());
		PlayAssertions.assertThat(refreshedRace.winner).isSameAs(winningHorse);
	}

	private void doJob() {
		try {
			new RaceRunningJob().doJob();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
