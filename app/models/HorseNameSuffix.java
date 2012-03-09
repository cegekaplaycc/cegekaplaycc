package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class HorseNameSuffix extends Model {

	public HorseNameSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String suffix;

}
