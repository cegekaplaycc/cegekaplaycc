package models;

public class HorseBuilder {

	
	
	private static final int DEFAULT_TRAINING = 20;
	private static final int DEFAULT_FITNESS = 20;

	private int fitness = DEFAULT_FITNESS;
	private int training = DEFAULT_TRAINING;
	private String name = "Jolly Jumper";
	private long price = 28;
	
	public Horse build() {
		Horse horse = new Horse(name, price);
		horse.setFitness(fitness);
		horse.setTraining(training);
		return horse;
	}
	
	public HorseBuilder withFitness(int fitness) {
		this.fitness = fitness;
		return this;
	}

	public HorseBuilder withTraining(int training) {
		this.training = training;
		return this;
	}

	public HorseBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public HorseBuilder withPrice(long price) {
		this.price = price;
		return this;
	}
	
}