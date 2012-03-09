import static org.fest.assertions.Assertions.assertThat;
import junit.framework.Assert;
import models.Race;

import org.fest.assertions.GenericAssert;


public class RaceAssert extends GenericAssert<RaceAssert, Race> {

	protected RaceAssert(Race actual) {
		super(RaceAssert.class, actual);
	}

	public RaceAssert hasBeenStarted() {
		Assert.assertTrue("race " + actual.name + " should have been run!", actual.hasRun());
		return this;
	}
	
}
