package models.stable;

import jobs.RandomHorsesBreeder;
import models.Horse;
import play.db.jpa.Model;

public class Box extends Model {

	public Horse horse;

	public static Box buildNewBox() {
		Box box = new Box();
		return box;
	}

	public static Box buildNewBoxWithRandomHorse() {
		Box box = buildNewBox();
		box.horse = RandomHorsesBreeder.createRandomHorse().save();
		return box;
	}

}
