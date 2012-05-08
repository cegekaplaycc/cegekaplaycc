package assertion;
import models.Race;

import org.fest.assertions.Assertions;


public class PlayAssertions extends Assertions {

	public static RaceAssert assertThat(Race actual) {
		return new RaceAssert(actual);
	}
}
