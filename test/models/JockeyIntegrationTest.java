package models;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class JockeyIntegrationTest extends IntegrationTestCase {

	@Test
	public void jockeyCanBePersisted() {
		Jockey savedJockey = new Jockey("Rappe Ronnie").save();

		clearEntityContext();
		Jockey loadedJockey = Jockey.findById(savedJockey.getId());
		Assertions.assertThat(loadedJockey.getName()).isEqualTo("Rappe Ronnie");
	}

}
