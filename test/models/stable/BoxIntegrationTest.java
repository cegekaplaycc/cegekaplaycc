package models.stable;

import models.IntegrationTest;

import org.junit.Test;

public class BoxIntegrationTest extends IntegrationTest {

	@Test
	public void buildNewBox_CreatesNewBoxWithoutHorse() {
		Box actual = Box.buildNewBox();

		assertThat(actual).isNotNull();
		assertThat(actual.id).isNotNull();
		assertThat(actual.horse).isNull();
	}

	@Test
	public void buildNewBoxWithRandomHorse() {
		Box actual = Box.buildNewBoxWithRandomHorse();

		assertThat(actual).isNotNull();
		assertThat(actual.id).isNotNull();
		assertThat(actual.horse).isNotNull();
	}

}
