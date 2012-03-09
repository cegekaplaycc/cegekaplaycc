package models;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.fest.assertions.Assertions;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import play.test.UnitTest;

public class RaceConstructorTest {
	
	@Before
	public void setup() {
		DateTimeUtils.setCurrentMillisFixed(new DateTime("2012-03-09T10:00:00").getMillis());
	}
	
	@Test
	public void shouldHaveAStartTimeWhenCreated() {
		Race race =  new Race();

		assertThat(race.getStartTime()).isEqualTo(new DateTime("2012-03-09T10:15:00").toDate());
	}
	
}

