package jobs;

import models.IntegrationTest;
import models.Race;
import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;

public class RaceSchedulingJobIntegrationTest extends IntegrationTest {

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
