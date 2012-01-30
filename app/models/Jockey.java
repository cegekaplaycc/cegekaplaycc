package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Jockey extends Model {

	private String name;

	public Jockey(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
