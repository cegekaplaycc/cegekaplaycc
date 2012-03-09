package jobs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.HorseNamePrefix;
import models.HorseNameSuffix;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class HorseNameBootstrapper extends Job {

	private static List<String> horsePrefixes;
	private static List<String> horseSuffixes;

	static {
		horsePrefixes = new ArrayList<String>();
		horseSuffixes = new ArrayList<String>();

		horsePrefixes.addAll(Arrays.asList(new String[] { "windy", "shallow",
				"black", "freaky", "heavy", "amazing", "great", "superfast",
				"readily", "lazy", "stumbling", "zorro" }));
		horseSuffixes.addAll(Arrays.asList(new String[] { "traveller", "night",
				"shadow", "horse", "rider", "stumper", "humperdink", "legend",
				"nightmare", "rabbit", "cow", "beast" }));
	}

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