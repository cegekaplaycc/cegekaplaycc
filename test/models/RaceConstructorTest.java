package models;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.fest.assertions.Assertions;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import play.test.UnitTest;

public class RaceConstructorTest extends UnitTest {
	
	@Before
	public void setSystemTime() {
		DateTimeUtils.setCurrentMillisFixed(new DateTime("2012-03-09T10:00:00").getMillis());
	}
	
	@After
	public void resetSystemTime() {
		DateTimeUtils.setCurrentMillisSystem();
	}
	
	@Test
	public void shouldHaveAStartTimeWhenCreated() {
		Race race =  new Race();
		
		System.out.println(race.getStartTime());

		Assertions.assertThat(race.getStartTime()).isEqualTo(new DateTime("2012-03-09T10:10:00").toDate());
	}
	
}

