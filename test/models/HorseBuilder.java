package models;

public class HorseBuilder {

	private static final int DEFAULT_TRAINING = 20;
	private static final int DEFAULT_FITNESS = 20;

	private int fitness = DEFAULT_FITNESS;
	private int training = DEFAULT_TRAINING;
	private String name = "Jolly Jumper";
	private long price = 28;
	private Long randomFactorForScoring;

	public Horse build() {
		Horse horse = createHorse();
		horse.setFitness(fitness);
		horse.setTraining(training);
		return horse;
	}

	private Horse createHorse() {
		Horse horse = null;
		if(randomFactorForScoring != null) {
			horse = new Horse(name, price) {
				@Override
				long getRandomFactorForScoring() {
					return randomFactorForScoring;
				}
			};
		} else {
			horse = new Horse(name, price);
		}
		return horse;
	}

	public HorseBuilder withRandomFactorForScoring(long randomFactorForScoring) {
		this.randomFactorForScoring = randomFactorForScoring;
		return this;
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
