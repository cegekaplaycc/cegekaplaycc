package jobs;

import java.util.Date;
import java.util.List;
import java.util.Random;

import models.Horse;
import models.HorseNamePrefix;
import models.HorseNameSuffix;

public class RandomHorsesBreeder {

	private static List<HorseNamePrefix> horsePrefixes;
	private static List<HorseNameSuffix> horseSuffixes;

	static {
		horsePrefixes = HorseNamePrefix.findAll();
		horseSuffixes = HorseNameSuffix.findAll();
	}

	public static Horse createRandomHorse() {
		Random random = new Random(new Date().getTime());
		int randomPrefix = random.nextInt(horsePrefixes.size());
		int randomSuffix = random.nextInt(horseSuffixes.size());
		Horse horse = new Horse(horsePrefixes.get(randomPrefix).prefix + " "
				+ horseSuffixes.get(randomSuffix).suffix, random.nextInt(2000));

		long horseCount = Horse.count("byName", horse.getName());
		if (horseCount > 0) {
			return createRandomHorse();
		}
		return horse;
	}

}
