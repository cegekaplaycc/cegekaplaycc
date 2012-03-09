package jobs;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import models.IntegrationTestCase;
import models.Race;
import models.RaceBuilder;

import org.fest.assertions.Assertions;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.Before;
import org.junit.Test;

import play.db.jpa.GenericModel.JPAQuery;

public class RaceSchedulingJobIntegrationTest extends IntegrationTestCase {

	private RaceSchedulingJob job;
	
	@Before
	public void setup() {
		job = new RaceSchedulingJob();
	}
	
	@Test
	public void testScheduleJob() throws Exception {
		job.doJob();

		Assertions.assertThat(Race.<Race>findAll()).hasSize(1);
	}

}
