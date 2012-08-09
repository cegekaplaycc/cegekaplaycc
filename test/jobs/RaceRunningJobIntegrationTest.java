package jobs;

import assertion.HoldYourHorseAssertions;
import models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.db.jpa.JPA;

import java.util.Date;
import java.util.List;

import static org.joda.time.DateTimeUtils.setCurrentMillisFixed;
import static org.joda.time.DateTimeUtils.setCurrentMillisSystem;

public class RaceRunningJobIntegrationTest extends IntegrationTest {

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
				.withStartTimeInPast()
				.withHorses(HorseBuilder.aHorse().build())
				.persist();
		new RaceBuilder()
				.withStartTimeInPast()
				.withHorses(HorseBuilder.aHorse().build())
				.persist();

		doJob();
		List<Race> allRaces = Race.findAll();
		for (Race race : allRaces) {
			HoldYourHorseAssertions.assertThat(race).hasBeenStarted();
			assertNotNull(race.winner);
		}
	}

	@Test
	public void doJobDoesNotStartFutureRaces() {
		Race race = new RaceBuilder().withStartTimeInFuture().persist();
		doJob();
		Race refreshedRace = Race.findById(race.getId());
		HoldYourHorseAssertions.assertThat(refreshedRace).hasNotBeenStarted();
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
		HoldYourHorseAssertions.assertThat(refreshedRace.winner.getName()).isEqualTo(winningHorse.getName());
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
