package jobs;

import static models.PlayerBuilder.aPlayer;
import static play.Play.mode;
import models.Horse;
import models.HorseBuilder;
import models.PlayerBuilder;
import models.RaceBuilder;
import models.stable.Box;
import models.stable.BoxBuilder;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class TestDataBootstrap extends Job {

	@Override
	public void doJob() throws Exception {
		if (mode.isDev()) {
			Horse horse1 = HorseBuilder.aHorse().withName("Tom")
					.withFitness(20).withTraining(30).persist();

			Horse horse2 = HorseBuilder.aHorse().withName("Matti").persist();

			Box box1 = BoxBuilder.aBox().withHorse(horse1).persist();
			Box box2 = BoxBuilder.aBox().withHorse(horse2).persist();

			aPlayer().withDisplayName("matti")
                    .withPassword("matti")
					.withUserId("matti")
                    .withBoxes(box1, box2).persist();

			new RaceBuilder().withHorses(horse1).withStartTimeInPast().withWinner(horse1).build().save();
			new RaceBuilder().withHorses(horse1, horse2).withStartTimeInPast().withWinner(horse2).build().save();
			new RaceBuilder().withHorses(horse2).withStartTimeInPast().build()
					.save();
		}
	}

}
