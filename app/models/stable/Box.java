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

	public static Box createBox() {
		return new Box().save();
	}

}
