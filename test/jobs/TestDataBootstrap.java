package jobs;

import models.Horse;
import models.stable.Box;
import models.stable.BoxBuilder;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

import static models.HorseBuilder.aHorse;
import static models.PlayerBuilder.aPlayer;
import static models.RaceBuilder.aRace;
import static play.Play.mode;

@OnApplicationStart
public class TestDataBootstrap extends Job {

    @Override
    public void doJob() throws Exception {
        if (mode.isDev()) {
            Horse horse1 = aHorse().withName("Tom").withFitness(20).withTraining(30).save();
            Horse horse2 = aHorse().withName("Matti").save();

            Box box1 = BoxBuilder.aBox().withHorse(horse1).save();
            Box box2 = BoxBuilder.aBox().withHorse(horse2).save();

            aPlayer().withDisplayName("matti")
                    .withPassword("matti")
                    .withUserId("matti")
                    .withBoxes(box1, box2)
                    .save();

            aRace().withHorses(horse1).withStartTimeInPast().withWinner(horse1).save();
            aRace().withHorses(horse1, horse2).withStartTimeInPast().withWinner(horse2).save();
            aRace().withHorses(horse2).withStartTimeInPast().save();
        }
    }

}
