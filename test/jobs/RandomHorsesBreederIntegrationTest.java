package jobs;

import models.*;
import models.IntegrationTest;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;

public class RandomHorsesBreederIntegrationTest extends IntegrationTest {

	@Before
	public void setUp() {
		new HorseNameSuffix("Zorro").save();
		new HorseNameSuffix("Jumper").save();
		
		new HorseNamePrefix("Windy").save();
		new HorseNamePrefix("Cloudy").save();
		new HorseNamePrefix("Stormy").save();

		new HorseBuilder().withName("Windy Zorro").build().save();
		new HorseBuilder().withName("Stormy Zorro").build().save();
		new HorseBuilder().withName("Windy Jumper").build().save();
		new HorseBuilder().withName("Cloudy Jumper").build().save();
		new HorseBuilder().withName("Stormy Jumper").build().save();
	}

	@Test
	public void getRandomHorse_() {
		Horse randomHorse = RandomHorsesBreeder.createRandomHorse();
		Assertions.assertThat(randomHorse.getName()).isEqualTo("Cloudy Zorro");
	}

}
