package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.List;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang.builder.ToStringStyle.SIMPLE_STYLE;

@Entity
public class RaceWeights extends Model {

	private final double trainingLevelMod;
	private final double fitnessLevelMod;
	private final double randomFactorMod;

	private RaceWeights() {
		this(1, 0.3, 0.1);
	}

	private RaceWeights(double trainingLevelMod, double fitnessLevelMod, double randomFactorMod) {
		this.trainingLevelMod = trainingLevelMod;
		this.fitnessLevelMod = fitnessLevelMod;
		this.randomFactorMod = randomFactorMod;
	}

	public static synchronized RaceWeights get() {
		List<RaceWeights> weights = RaceWeights.findAll();
		if (weights.isEmpty()) {
			return reset();
		}
		return weights.iterator().next();
	}

	public static RaceWeights reset() {
		RaceWeights.deleteAll();
		return new RaceWeights().save();
	}

	public static RaceWeights set(double trainingLevelMod, double fitnessLevelMod, double randomFactorMod) {
		RaceWeights.deleteAll();
		return new RaceWeights(trainingLevelMod, fitnessLevelMod, randomFactorMod).save();
	}

	@Override
	public boolean equals(Object other) {
		return reflectionEquals(this, other);
	}

	@Override
	public int hashCode() {
		return reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return reflectionToString(this, SIMPLE_STYLE);
	}

	public double getTrainingLevelMod() {
		return trainingLevelMod;
	}

	public double getFitnessLevelMod() {
		return fitnessLevelMod;
	}

	public double getRandomFactorMod() {
		return randomFactorMod;
	}

}
