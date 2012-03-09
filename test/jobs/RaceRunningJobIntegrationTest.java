package jobs;
import static org.joda.time.DateTimeUtils.setCurrentMillisFixed;
import static org.joda.time.DateTimeUtils.setCurrentMillisSystem;

import java.util.Date;
import java.util.List;

import models.Horse;
import models.Race;
import models.RaceBuilder;

import org.h2.util.DateTimeUtils;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import play.db.jpa.JPA;
import play.test.UnitTest;
import assertion.PlayAssertions;

public class RaceRunningJobIntegrationTest extends UnitTest {

	@Before
	public void freezeTime() {
		setCurrentMillisFixed(new Date().getTime());
	}
	
	@After
	public void resetTime() {
		setCurrentMillisSystem();
	}
	
	@Test
	public void doJobStartsAllRacesFromPast() {
		new RaceBuilder()
			.withName("race 1")
			.withStartTimeInPast()
			.persist();
		new RaceBuilder()
			.withName("race 2")
			.withStartTimeInPast()
			.persist();

		doJob();
		List<Race> allRaces = Race.findAll();
		for(Race race : allRaces) {
			PlayAssertions.assertThat(race).hasBeenStarted();
			assertNotNull(race.winner);
		}
	}
	
	@Test
	public void doJobDoesNotStartFutureRaces() {
		Race race = new RaceBuilder().persist();
		doJob();
		Race refreshedRace = Race.findById(race.getId());
		PlayAssertions.assertThat(refreshedRace).hasNotBeenStarted();
	}
	
	@Test
	public void doJobDoesNotRestartRacesWhichAreAlreadyRun() {
		Horse winningHorse = new Horse("fast fury").save();
		Race race = new RaceBuilder()
			.withWinner(winningHorse)
			.withHorses(new Horse("losing horse 1"), new Horse("losing horse 2"))
			.persist();
		
		doJob();
		Race refreshedRace = Race.findById(race.getId());
		PlayAssertions.assertThat(refreshedRace.winner.getName()).isEqualTo(winningHorse.getName());
	}

	private void doJob() {
		try {
			new RaceRunningJob().doJob();
			JPA.em().clear();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
