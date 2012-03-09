package jobs;
import java.util.List;

import models.Race;
import play.db.jpa.JPABase;
import play.jobs.Every;
import play.jobs.Job;
import play.jobs.On;

@On("0 0 0 * * ?")
public class RaceSchedulingJob extends Job {

	@Override
	public void doJob() throws Exception {
		new Race().save();
	}
	
}
