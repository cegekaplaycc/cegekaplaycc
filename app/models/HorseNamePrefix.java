package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class HorseNamePrefix extends Model {

	public String prefix;

	public HorseNamePrefix(String prefix) {
		this.prefix = prefix;
	}

}
