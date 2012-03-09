package jobs;
import models.Race;
import models.RaceFactory;
import play.jobs.Job;
import play.jobs.On;

@On("0 0 0 * * ?")
public class RaceSchedulingJob extends Job {

	private RaceFactory factory;

	public RaceSchedulingJob() {
		super();
		setFactory(new RaceFactory());
	}

	@Override
	public void doJob() throws Exception {
		factory.create().save();
	}
	
	public void setFactory(RaceFactory factory) {
		this.factory = factory;
	}
	
}
