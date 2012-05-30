package models;

import litmus.Category;
import org.junit.Before;
import play.db.jpa.JPA;
import play.test.Fixtures;
import play.test.UnitTest;

@Category(value = "Integration Tests", priority = 10001)
public abstract class IntegrationTest extends UnitTest {

	@Before
	public void cleanDB() {
		Fixtures.deleteDatabase();
	}


	protected void clearEntityContext() {
		JPA.em().clear();
	}
}
