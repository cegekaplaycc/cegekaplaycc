package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jobs.RaceRunningJob;
import play.Play;
import play.jobs.Job;
import play.mvc.Before;
import play.mvc.Controller;

public class Jobs extends Controller {
	
	@Before
    static void checkAuthentification() {
        if (Play.mode.isProd()) {
        	//throw new RuntimeException("Can't manually trigger jobs in PROD mode!");
        }
    }
	
	public static void index() {
		File jobsDir = new File("app/jobs");
		List<File> list = Arrays.asList(jobsDir.listFiles());
		
		List<String> jobs = new ArrayList<String>();
		for (File file : list) {
			jobs.add(file.getName().replaceAll(".java", ""));
		}
		
		render(jobs);
	}
	
	public static void run(String job) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Job<?> jobToRun = (Job) Class.forName("jobs." + job).newInstance();
		jobToRun.now();

		flash.success("Started job " + job + "!");
		index();
	}
	
}
