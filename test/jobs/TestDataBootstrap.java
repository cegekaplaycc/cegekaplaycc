package jobs;

import static play.Play.mode;
import models.Horse;
import models.HorseBuilder;
import models.PlayerBuilder;
import models.RaceBuilder;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class TestDataBootstrap extends Job {

	@Override
	public void doJob() throws Exception {
		if (mode.isDev()) {
			Horse horse1 = new HorseBuilder()
					.withName("Tom")
					.withFitness(20)
					.withTraining(30)
					.persist();

			Horse horse2 = new HorseBuilder().withName("Matti").persist();

			new PlayerBuilder()
					.withDisplayName("matti")
					.withPassword("matti")
					.withUserId("matti")
					.withHorses(horse1, horse2)
					.persist();

			new RaceBuilder().withHorses(horse1).withStartTimeInPast().build().save();
			new RaceBuilder().withHorses(horse2).withStartTimeInPast().build().save();
		}
	}

}
