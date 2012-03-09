package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.joda.time.DateTime;

import play.Play;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Race extends Model {

	public static final int MAX_AVAILABLE_SLOTS = 8;
	public static final int MIN_HORSES_ENTERED_TO_START_RACE = 2;
	public static final String MAX_AVAILABLE_SLOTS_EXCEEDED = "Cannot enter more than the maximum available slots";
	public static final String LESS_THAN_MIN_AMOUNT_HORSES_ENTERED_TO_START_RACE = "Less than minimum amount of horses entered to start race";

	@ManyToMany
	private Set<Horse> horses = new HashSet<Horse>();

	@OneToOne
	public Horse winner;

	@Required
	public String name;

	@Required
	public Date startTime;

	public Race() {
		super();
		initializeStartTime();
	}

	private void initializeStartTime() {
		Object o = Play.configuration.get("horse.race.starttime.offset.minutes");
		System.out.println(o.getClass());
		Integer startTimeOffset = Integer.parseInt(Play.configuration.getProperty("horse.race.starttime.offset.minutes"));
		this.startTime = new DateTime().plusMinutes(startTimeOffset).toDate();
	}

	public void enter(Horse horse) {
		if (!canEnterHorse()) {
			throw new IllegalStateException(MAX_AVAILABLE_SLOTS_EXCEEDED);
		}

		this.horses.add(horse);
	}

	public boolean canEnterHorse() {
		return getAvailableSlots() > 0;
	}

	public Horse getHorseOfPlayer(Player player) {
		for (Horse horse : player.getHorses()) {
			for (Horse enteredHorse : getEnteredHorses()) {
				if (horse.equals(enteredHorse)) {
					return horse;
				}
			}
		}

		return null;
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

	public boolean startTimeInFuture() {
		return startTime.after(new Date());
	}

	public boolean hasRun() {
		return winner != null;
	}

	public boolean readyToStart() {
		return horses.size() >= MIN_HORSES_ENTERED_TO_START_RACE;
	}

	public Set<Horse> getEnteredHorses() {
		return Collections.unmodifiableSet(horses);
	}

	public Date getStartTime() {
		return startTime;
	}

}
