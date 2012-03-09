package models;

import static org.apache.commons.lang.builder.ToStringStyle.SIMPLE_STYLE;

import java.util.List;

import javax.persistence.Entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import play.db.jpa.JPABase;
import play.db.jpa.Model;

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

	public static RaceWeights get() {
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
		return EqualsBuilder.reflectionEquals(this, other);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, SIMPLE_STYLE);
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
