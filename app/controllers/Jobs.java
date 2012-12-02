package controllers;

import models.Race;
import play.jobs.Job;
import play.mvc.Controller;
import play.mvc.With;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@With(OnlyInDevMode.class)
public class Jobs extends Controller {


    public static void index() {
        List<String> jobs = getJobs();
        List<Race> races = Race.findUpcomingRaces();
        render(jobs, races);
    }

    public static void runRace(Long id) {
        Race race = Race.findById(id);
        race.calculateWinnerAndSave();
        index();
    }

    public static void initDb() {
        run("TestDataBootstrap");
        index();
    }

    private static List<String> getJobs() {
        File jobsDir = new File("app/jobs");
        List<File> list = newArrayList(jobsDir.listFiles());

        List<String> jobs = new ArrayList<String>();
        for (File file : list) {
            jobs.add(file.getName().replaceAll(".java", ""));
        }
        return jobs;
    }

    public static void run(String job) {
        try {
            Job<?> jobToRun = (Job) Class.forName("jobs." + job).newInstance();
            jobToRun.now();
            flash.success("Started job " + job + "!");
            index();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
