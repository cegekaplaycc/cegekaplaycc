package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.jpa.Model;

@Entity
public class Horse extends Model {

	public String name;

	public Horse(String name) {
		this.name = name;
	}

	public Horse() {
	}

	@Override
	public String toString() {
		return name;
	}
}
