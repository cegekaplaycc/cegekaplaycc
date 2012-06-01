package jobs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.HorseNamePrefix;
import models.HorseNameSuffix;
import models.stock.Supply;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class SuppliesBootstrapper extends Job {

	@Override
	public void doJob() {
		new Supply("Power biscuits", 12).save();
		new Supply("Hay", 5).save();
		new Supply("Carrots", 6).save();
	}

}