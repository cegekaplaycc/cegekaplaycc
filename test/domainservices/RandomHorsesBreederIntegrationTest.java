package domainservices;

import models.*;
import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RandomHorsesBreederIntegrationTest extends IntegrationTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Before
	public void setUp() {
		new HorseNameSuffix("Zorro").save();
		new HorseNameSuffix("Jumper").save();
		
		new HorseNamePrefix("Windy").save();
		new HorseNamePrefix("Cloudy").save();
		new HorseNamePrefix("Stormy").save();

		HorseBuilder.aHorse().withName("Windy Zorro").save();
		HorseBuilder.aHorse().withName("Stormy Zorro").save();
		HorseBuilder.aHorse().withName("Windy Jumper").save();
		HorseBuilder.aHorse().withName("Cloudy Jumper").save();
		HorseBuilder.aHorse().withName("Stormy Jumper").save();
	}

	@Test
	public void getRandomHorse() {
		Horse randomHorse = new RandomHorsesBreeder().createRandomHorse();
		Assertions.assertThat(randomHorse.getName()).isEqualTo("Cloudy Zorro");
	}

	@Test
	public void getRandomHorse_WhenAllHorsesAreExhausted_RandomHorseBreederThrowsException() {
		HorseBuilder.aHorse().withName("Cloudy Zorro").save();
		
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage("All horses exhausted. Couldn't breed new horse!");
		
		new RandomHorsesBreeder().createRandomHorse();
	}

}
