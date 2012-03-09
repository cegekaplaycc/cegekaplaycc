package jobs;
import java.util.List;

import models.Race;
import play.db.jpa.JPABase;
import play.jobs.Job;

public class RaceRunningJob extends Job<Void> {

	@Override
	public void doJob() throws Exception {
		List<Race> allRaces = Race.findAll();
		for (Race race : allRaces) {
			if (!race.hasRun() && !race.startTimeInFuture()) {
				race.start();
			}
		}
	}
}
