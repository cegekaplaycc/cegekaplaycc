package models;

import org.junit.Before;

import play.db.jpa.JPA;
import play.db.jpa.Model;
import play.test.Fixtures;
import play.test.UnitTest;

public abstract class IntegrationTestCase extends UnitTest {

	@Before
	public void cleanDB() {
		Fixtures.deleteDatabase();
	}

	protected void save(Model... models) {
		for(Model model : models) {
			model.save();
		}
	}
	
	protected void clearEntityContext() {
		JPA.em().clear();
	}
}
