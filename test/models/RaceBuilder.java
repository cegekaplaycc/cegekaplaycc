package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.powermock.reflect.Whitebox;

public class RaceBuilder {

	private boolean withStarted;
	private List<Horse> horses = new ArrayList<Horse>();
	private Horse winningHorse;
	private String name = "race name";
	private DateTime startTime;

	public Race build() {
		Race race = new Race();
		race.name = name;
		enterHorsesCreateDefaultIfNeeded(race);
		if (withStarted) {
			race.start();
		}
		if (winningHorse != null) {
			race.winner = winningHorse;
		}
		Whitebox.setInternalState(race, "startTime", startTime);
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

	public RaceBuilder withStartTime(DateTime startTime) {
		this.startTime = startTime;
		return this;
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
