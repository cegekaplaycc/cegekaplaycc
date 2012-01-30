package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang.NotImplementedException;

import play.db.jpa.Model;


@Entity
public class Race extends Model {
	
	public static final int MAX_AVAILABLE_SLOTS = 8;

	public static final String MAX_AVAILABLE_SLOTS_EXCEEDED = "Cannot enter more than the maximum available slots";

	@OneToMany
	public Set<Horse> horses = new HashSet<Horse>();
	
	public void enter(Horse horse) {
		if (getAvailableSlots() == 0) {
			throw new IllegalStateException(MAX_AVAILABLE_SLOTS_EXCEEDED);
		}
		
		this.horses.add(horse);
	}

	public int getAvailableSlots() {
		return MAX_AVAILABLE_SLOTS - horses.size();
	}

	public void start() {
		throw new NotImplementedException();
	}
	
}
