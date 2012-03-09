package models.randomizer;

import java.util.Date;
import java.util.Random;

public class RandomizerProviderImpl implements RandomizerProvider {

	@Override
	public int get(int max) {
		return new Random(new Date().getTime()).nextInt(max);
	}

}
