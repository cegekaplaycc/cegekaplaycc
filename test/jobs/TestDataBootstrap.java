package jobs;

import models.Horse;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

import static models.HorseBuilder.aHorse;
import static models.PlayerBuilder.aPlayer;
import static models.RaceBuilder.aRace;
import static models.stable.BoxBuilder.aBox;
import static play.Play.mode;
import static play.test.Fixtures.deleteAllModels;

@OnApplicationStart
public class TestDataBootstrap extends Job {

    @Override
    public void doJob() throws Exception {
        if (mode.isDev()) {

            deleteAllModels();

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

            createARaceInThePast(windyLegend);
            createARaceInTheFuture(heavyStumper);
            createAnotherRaceInTheFutureWithoutHorse();
        }
    }

    private void createAnotherRaceInTheFutureWithoutHorse() {
        aRace().withStartTime(2013, 1, 1, 10, 0)
                .withSubscriptionFee(500)
                .withHorses(
                        aHorse().withName("Amazing Waltz").save(),
                        aHorse().withName("The Great Radetsky").save(),
                        aHorse().withName("Blue Danube").save())
                .save();
    }

    private void createARaceInTheFuture(Horse heavyStumper) {
        aRace().withStartTime(2012, 12, 24, 20, 0)
                .withSubscriptionFee(250)
                .withHorses(
                        heavyStumper,
                        aHorse().withName("Holy Night")
                                .withFitness(50)
                                .withTraining(80)
                                .save(),
                        aHorse().withName("Made In Heaven").save(),
                        aHorse().withName("Golden Balthazar").save())
                .save();
    }

    private void createARaceInThePast(Horse windyLegend) {
        aRace().withStartTime(2012, 12, 1, 20, 0)
                .withHorses(windyLegend)
                .save();
    }

}
