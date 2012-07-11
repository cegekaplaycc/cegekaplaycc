package models.stable;

import models.HorseNamePrefix;
import models.HorseNameSuffix;
import models.IntegrationTest;

import org.junit.Before;
import org.junit.Test;

public class BoxIntegrationTest extends IntegrationTest {

	@Before
	public void initHorseNames() {
		new HorseNamePrefix("Windy").save();
		new HorseNameSuffix("City").save();
	}

	@Test
	public void buildNewBox_CreatesNewBoxWithoutHorse() {
		Box actual = Box.createBox();

		assertThat(actual).isNotNull();
		assertThat(actual.id).isNotNull();
		assertThat(actual.horse).isNull();
	}

	@Test
	public void buildNewBoxWithRandomHorse() {
		Box actual = Box.createBoxWithRandomHorse();

		assertThat(actual).isNotNull();
		assertThat(actual.id).isNotNull();
		assertThat(actual.horse).isNotNull();
		assertThat(actual.horse.id).isNotNull();
	}

}
