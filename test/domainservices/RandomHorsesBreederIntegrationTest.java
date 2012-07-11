package domainservices;

import models.*;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import domainservices.RandomHorsesBreeder;

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

		HorseBuilder.aHorse().withName("Windy Zorro").persist();
		HorseBuilder.aHorse().withName("Stormy Zorro").persist();
		HorseBuilder.aHorse().withName("Windy Jumper").persist();
		HorseBuilder.aHorse().withName("Cloudy Jumper").persist();
		HorseBuilder.aHorse().withName("Stormy Jumper").persist();
	}

	@Test
	public void getRandomHorse() {
		Horse randomHorse = new RandomHorsesBreeder().createRandomHorse();
		Assertions.assertThat(randomHorse.getName()).isEqualTo("Cloudy Zorro");
	}

	@Test
	public void getRandomHorse_WhenAllHorsesAreExhausted_RandomHorseBreederThrowsException() {
		HorseBuilder.aHorse().withName("Cloudy Zorro").persist();
		
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage("All horses exhausted. Couldn't breed new horse!");
		
		new RandomHorsesBreeder().createRandomHorse();
	}

}
