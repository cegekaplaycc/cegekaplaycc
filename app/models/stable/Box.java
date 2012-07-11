package models.stable;

import static domainservices.ServiceLocator.randomHorsesBreeder;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.Horse;
import play.db.jpa.Model;

@Entity
public class Box extends Model {

	@OneToOne
	public Horse horse;

	public static Box buildNewBox() {
		return new Box().save();
	}

	public static Box buildNewBoxWithRandomHorse() {
		Box box = buildNewBox();
		box.horse = randomHorsesBreeder.createRandomHorse().save();
		box.save();
		return box;
	}

}
