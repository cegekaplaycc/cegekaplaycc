package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

public class RaceBuilder {

	private boolean withStarted;
	private List<Horse> horses = new ArrayList<Horse>();
	private Horse winningHorse;
	private String name = "race name";
	private Date startTime;

	public Race build() {
		Race race = new Race();
		race.name = name;
		if (startTime != null) {
			race.startTime = startTime;
		}

		enterHorsesCreateDefaultIfNeeded(race);
		if (withStarted) {
			race.start();
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
		this.horses = Arrays.asList(horses);
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

	private void enterHorsesCreateDefaultIfNeeded(Race race) {
		if (horses.isEmpty()) {
			for (int i = 0; i < Race.MIN_HORSES_ENTERED_TO_START_RACE; i++) {
				horses.add(new Horse("default horse #" + (i + 1)));
			}
		}
		for (Horse horse : horses) {
			race.enter(horse);
		}
	}

}
