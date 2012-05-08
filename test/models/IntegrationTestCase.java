package models;

import org.junit.Before;
import play.db.jpa.JPA;
import play.test.Fixtures;
import play.test.UnitTest;

public abstract class IntegrationTestCase extends UnitTest {

	@Before
	public void cleanDB() {
		Fixtures.deleteDatabase();
	}


	protected void clearEntityContext() {
		JPA.em().clear();
	}
}
