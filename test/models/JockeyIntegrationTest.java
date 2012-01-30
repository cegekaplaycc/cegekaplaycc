package models;

import org.fest.assertions.Assertions;
import org.junit.Test;

import play.db.jpa.JPA;

public class JockeyIntegrationTest extends IntegrationTestCase {

	@Test
	public void jockeyCanBePersisted() {
		Jockey savedJockey = new Jockey("Rappe Ronnie").save();

		JPA.em().clear();
		Jockey loadedJockey = Jockey.findById(savedJockey.getId());
		Assertions.assertThat(loadedJockey.getName()).isEqualTo("Rappe Ronnie");
	}

}
