package jobs;

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

		new HorseBuilder().withName("Windy Zorro").build().save();
		new HorseBuilder().withName("Stormy Zorro").build().save();
		new HorseBuilder().withName("Windy Jumper").build().save();
		new HorseBuilder().withName("Cloudy Jumper").build().save();
		new HorseBuilder().withName("Stormy Jumper").build().save();
	}

	@Test
	public void getRandomHorse() {
		Horse randomHorse = RandomHorsesBreeder.createRandomHorse();
		Assertions.assertThat(randomHorse.getName()).isEqualTo("Cloudy Zorro");
	}

	@Test
	public void getRandomHorse_WhenAllHorsesAreExhausted_RandomHorseBreederThrowsException() {
		new HorseBuilder().withName("Cloudy Zorro").build().save();
		
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage("All horses exhausted. Couldn't breed new horse!");
		
		RandomHorsesBreeder.createRandomHorse();
	}

}
