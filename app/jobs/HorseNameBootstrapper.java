package jobs;

import models.HorseNamePrefix;
import models.HorseNameSuffix;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@OnApplicationStart
public class HorseNameBootstrapper extends Job {

    private static List<String> horsePrefixes = newArrayList("windy", "shallow",
            "black", "freaky", "heavy", "amazing", "great", "superfast",
            "readily", "lazy", "stumbling", "zorro");
    private static List<String> horseSuffixes = newArrayList("traveller", "night",
            "shadow", "horse", "rider", "stumper", "humperdink", "legend",
            "nightmare", "rabbit", "cow", "beast");


    @Override
    public void doJob() {
        for (String prefix : horsePrefixes) {
            new HorseNamePrefix(prefix).save();
        }
        for (String suffix : horseSuffixes) {
            new HorseNameSuffix(suffix).save();
        }
    }

}