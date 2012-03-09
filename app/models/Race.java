package models;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.reverseOrder;
import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.joda.time.DateTime;

import play.Play;
import play.data.validation.Required;
import play.db.jpa.Model;

import com.google.common.base.Function;
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

	public Date getStartTime() {
		return startTime;
	}

}
