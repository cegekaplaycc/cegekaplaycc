package jobs;

import models.Horse;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

import static models.HorseBuilder.aHorse;
import static models.PlayerBuilder.aPlayer;
import static models.RaceBuilder.aRace;
import static models.stable.BoxBuilder.aBox;
import static play.Play.mode;

@OnApplicationStart
public class TestDataBootstrap extends Job {

    @Override
    public void doJob() throws Exception {
        if (mode.isDev()) {

            Horse heavyStumper = aHorse().withName("Heavy Stumper").save();
            Horse windyLegend = aHorse().withName("Windy Legend").save();

            aPlayer().withDisplayName("Ben Verbeken")
                    .withUserId("bver")
                    .withPassword("bver")
                    .withCash(5000)
                    .withBoxes(
                            aBox().withHorse(heavyStumper).save(),
                            aBox().withHorse(windyLegend).save())
                    .save();


            aRace().withStartTimeInFuture().save();
            aRace().withStartTimeInFuture().save();
            aRace().withStartTimeInFuture().save();

        }
    }

}
