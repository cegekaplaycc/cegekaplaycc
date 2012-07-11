package models;

import static models.stock.Food.HAY;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import models.randomizer.RandomizerProvider;
import models.randomizer.RandomizerProviderImpl;
import models.stock.Food;
import play.db.jpa.Model;

import com.google.common.collect.Sets;

@Entity
public class Horse extends Model {

	private String name;
	private long price;
	private int fitness;
	private int training;
    public int moneyForTrainer;
	@Enumerated(EnumType.STRING)
	public Food food = HAY;

	@Transient
	RandomizerProvider randomizerProvider;

	public Horse() {
		this.randomizerProvider = new RandomizerProviderImpl();
	}

	public Horse(String name) {
		this();
		this.name = name;
	}

	public Horse(String name, long price) {
		this(name);
		this.price = price;
	}

	private int getRandomFactorForScoring() {
		return randomizerProvider.get(100);
	}

	public double calculateRaceScore() {
		return fitnessScore() + trainingScore() + randomScore();
	}

	private double randomScore() {
		return getRandomFactorForScoring() * RaceWeights.get().getRandomFactorMod();
	}

	private double trainingScore() {
		return training * RaceWeights.get().getTrainingLevelMod();
	}

	private double fitnessScore() {
		return fitness * RaceWeights.get().getFitnessLevelMod();
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
		List<Race> races = Race.find("select race from Race race join race.horses horse where horse = ? and startTime < ?", this, new Date())
				.<Race> fetch();
		return Sets.newHashSet(races);
	}

	public int getFitness() {
		return fitness;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}

	public int getTraining() {
		return training;
	}

	public void setTraining(int training) {
		this.training = training;
	}

    public void setMoneyForTrainer(int moneyForTrainer) {
        this.moneyForTrainer = moneyForTrainer;
    }
}
