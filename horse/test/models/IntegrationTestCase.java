package models;
import org.junit.Before;

import play.test.Fixtures;
import play.test.UnitTest;


public abstract class IntegrationTestCase extends UnitTest {

	@Before
	public void cleanDB() {
		Fixtures.deleteDatabase();
	}
}
