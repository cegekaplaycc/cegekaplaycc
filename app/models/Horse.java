package models;

import java.util.Set;

import javax.persistence.Entity;

import org.apache.commons.lang.NotImplementedException;

import play.db.jpa.Model;

@Entity
public class Horse extends Model {

	private String name;
	private long price;

	public Horse() {
	}

	public Horse(String name) {
		this.name = name;
	}

	public Horse(String name, long price) {
		this(name);
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return name;
	}

	public Set<Race> getPastEnteredRaces() {
		throw new NotImplementedException();
	}
}
