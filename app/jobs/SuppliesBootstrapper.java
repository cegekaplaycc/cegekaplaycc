package jobs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.HorseNamePrefix;
import models.HorseNameSuffix;
import models.stock.Food;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class SuppliesBootstrapper extends Job {

	@Override
	public void doJob() {
		new Food("Power biscuits", 12).save();
		new Food("Hay", 5).save();
		new Food("Carrots", 6).save();
	}

}