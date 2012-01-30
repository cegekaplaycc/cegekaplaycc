package models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang.NotImplementedException;

import play.db.jpa.Model;


@Entity
public class Race extends Model {
	
	public static final int MAX_AVAILABLE_SLOTS = 8;
	public static final int MIN_HORSES_ENTERED_TO_START_RACE = 2;
	public static final String MAX_AVAILABLE_SLOTS_EXCEEDED = "Cannot enter more than the maximum available slots";
	public static final String LESS_THAN_MIN_AMOUNT_HORSES_ENTERED_TO_START_RACE = "Less than minimum amount of horses entered to start race";

	@OneToMany
	public Set<Horse> horses = new HashSet<Horse>();
	public Horse winner;
	
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
		if (!readyToStart()) {
			throw new IllegalStateException(LESS_THAN_MIN_AMOUNT_HORSES_ENTERED_TO_START_RACE);
		}
		
		Random random = new Random(new Date().getTime());
		int randomIndex = random.nextInt(horses.size());
		winner = new ArrayList<Horse>(horses).get(randomIndex);
	}

	public boolean readyToStart() {
		return horses.size() >= MIN_HORSES_ENTERED_TO_START_RACE;
	}

}
