package models;

import models.randomizer.RandomizerProvider;
import models.stock.Food;
import util.AbstractBuilder;

public class HorseBuilder extends AbstractBuilder<Horse> {

	private static final int DEFAULT_TRAINING = 20;
	private static final int DEFAULT_FITNESS = 20;

	private int fitness = DEFAULT_FITNESS;
	private int training = DEFAULT_TRAINING;
	private String name = "Jolly Jumper";
	private long price = 28;
	private Integer randomFactorForScoring;
	private Long id;
	private Food food = Food.HAY;

	private HorseBuilder() {

	}

	public static HorseBuilder aHorse() {
		return new HorseBuilder();
	}

	@Override
	public Horse build() {
		Horse horse = createHorse();
		horse.setFitness(fitness);
		horse.setTraining(training);
		horse.id = id;
		horse.food = food;
		return horse;
	}

	private Horse createHorse() {
		Horse horse = new Horse(name, price);
		setRandomFactorForScoringIfNeeded(horse);
		return horse;
	}

	private void setRandomFactorForScoringIfNeeded(Horse horse) {
		if (randomFactorForScoring != null) {
			horse.randomizerProvider = new RandomizerProvider() {

				@Override
				public int get(int max) {
					return randomFactorForScoring;
				}
			};
		}
	}

	public HorseBuilder withRandomFactorForScoring(int randomFactorForScoring) {
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

	public HorseBuilder withId(long id) {
		this.id = id;
		return this;
	}

	public HorseBuilder withFood(Food food) {
		this.food = food;
		return this;
	}

}
