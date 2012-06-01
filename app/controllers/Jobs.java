package controllers;

import play.jobs.Job;
import play.mvc.Controller;
import play.mvc.With;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@With(OnlyInDevMode.class)
public class Jobs extends Controller {

	
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
