package models;

import com.google.common.collect.Sets;
import org.joda.time.DateTime;
import org.powermock.reflect.Whitebox;

import java.util.Date;
import java.util.Set;

public class RaceBuilder {

	private boolean withStarted;
	private Set<Horse> horses = Sets.newHashSet();
	private Horse winningHorse;
	private String name = "race name";
	private Date startTime = new Date();

	public Race build() {
		Race race = new Race();
		race.name = name;
		if (startTime != null) {
			race.startTime = startTime;
		}

		Whitebox.setInternalState(race, "horses", horses);
		if (withStarted) {
			race.calculateWinner();
		}
		if (winningHorse != null) {
			race.winner = winningHorse;
		}

		return race;
	}

	public Race persist() {
		Race race = build();
		for (Horse enteredHorses : race.getEnteredHorses()) {
			if (enteredHorses.getId() == null) {
				enteredHorses.save();
			}
		}
		race.save();
		return race;
	}

	public RaceBuilder withStarted() {
		this.withStarted = true;
		return this;
	}

	public RaceBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public RaceBuilder withHorses(Horse... horses) {
		this.horses = Sets.newHashSet(horses);
		return this;
	}

	public RaceBuilder withWinner(Horse winningHorse) {
		this.winningHorse = winningHorse;
		return this;
	}

	public RaceBuilder withStartTime(Date startTime) {
		this.startTime = startTime;
		return this;
	}

    public RaceBuilder withStartTimeInPast() {
        return withStartTime(new DateTime().minusMinutes(15).toDate());
    }

    public RaceBuilder withStartTimeInFuture() {
        return withStartTime(new DateTime().plusMinutes(15).toDate());
    }

}
