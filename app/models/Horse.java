package models;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;

import play.db.jpa.Model;

import com.google.common.collect.Sets;

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
		List<Race> races = Race.find("select race from Race race join race.horses horse where horse = ? and startTime < ?", this, new Date()).<Race> fetch();
		return Sets.newHashSet(races);
	}
}
