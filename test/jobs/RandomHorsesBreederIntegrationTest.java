package jobs;

import models.Horse;
import models.HorseBuilder;
import models.HorseNamePrefix;
import models.HorseNameSuffix;
import models.IntegrationTestCase;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;

public class RandomHorsesBreederIntegrationTest extends IntegrationTestCase {

	@Before
	public void setUp() {
		HorseNameSuffix suffix = new HorseNameSuffix();
		suffix.suffix = "Zorro";
		suffix.save();

		HorseNamePrefix prefix = new HorseNamePrefix();
		prefix.prefix = "Windy";
		prefix.save();

		HorseNamePrefix prefix2 = new HorseNamePrefix();
		prefix2.prefix = "Cloudy";
		prefix2.save();

		new HorseBuilder().withName("Cloudy Zorro").build().save();
	}

	@Test
	public void getRandomHorse_() {
		Horse randomHorse = RandomHorsesBreeder.createRandomHorse();
		Assertions.assertThat(randomHorse.getName()).isEqualTo("Windy Zorro");

	}

}
