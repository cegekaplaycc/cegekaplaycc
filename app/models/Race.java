package models;

import static java.util.Collections.reverseOrder;
import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Entity
public class Race extends Model {

	public static final int MAX_AVAILABLE_SLOTS = 8;
	public static final String MAX_AVAILABLE_SLOTS_EXCEEDED = "Cannot enter more than the maximum available slots";

	@ManyToMany
	private Set<Horse> horses = new HashSet<Horse>();

	@OneToOne
	public Horse winner;

	@Required
	public Date startTime;

	@Required
	public Integer subscriptionFee;

	public Race(Date startTime, Integer subscriptionFee) {
		this.startTime = startTime;
		this.subscriptionFee = subscriptionFee;
	}

	public Race() {
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

	public void calculateWinner() {
		if (!horses.isEmpty()) {
			Map<Double, Horse> scoresPerHorse = createHorseResultScoresMap();
			ArrayList<Double> all = Lists.newArrayList(scoresPerHorse.keySet());
			sort(all, reverseOrder());
			winner = scoresPerHorse.get(all.get(0));
		}

	}

	private Map<Double, Horse> createHorseResultScoresMap() {
		Map<Double, Horse> scoresPerHorse = Maps.newHashMap();
		for (Horse horse : horses) {
			scoresPerHorse.put(horse.calculateRaceScore(), horse);
		}
		return scoresPerHorse;
	}

	public boolean startTimeInFuture() {
		return startTime.after(new Date());
	}

	public boolean hasRun() {
		return winner != null;
	}

	public Set<Horse> getEnteredHorses() {
		return Collections.unmodifiableSet(horses);
	}

	public static List<Race> findUpcomingRaces(int limit) {
		return Race.all().fetch(limit);
	}

    public static List<Race> findRacesThatHorseEntered(Horse horse) {
        return null;
    }

    public boolean horseEnteredRace(Horse horse) {
        return horses.contains(horse);
    }

    public boolean hasWon(Horse horse) {
        return winner != null && winner.equals(horse);
    }
}
