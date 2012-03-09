package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.jpa.Model;

@Entity
public class Horse extends Model {

	private String name;
	private long price;
	private int fitness;
	private int training;

	public Horse() {
	}

	public Horse(String name) {
		this.name = name;
	}

	public Horse(String name, long price) {
		this(name);
		this.price = price;
	}

	long getRandomFactorForScoring() {
		return 0;
	}
	
	public double calculateRaceScore() {
		throw new UnsupportedOperationException();
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
	
}
