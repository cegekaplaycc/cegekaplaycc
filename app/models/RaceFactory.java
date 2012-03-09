package models;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.joda.time.DateTime;

import play.Play;


public class RaceFactory {

	static final List<Integer> SUBSCRIPTION_FEES = Arrays.asList(10, 25, 50, 100, 250, 500);
			
	public Race create() {
		Integer startTimeOffset = Integer.parseInt(Play.configuration.getProperty("horse.race.starttime.offset.minutes"));
		Date startTime = new DateTime().plusMinutes(startTimeOffset).toDate();
		
		int randomIndex = new Random().nextInt(SUBSCRIPTION_FEES.size());
		Integer randomSubscriptionFee = SUBSCRIPTION_FEES.get(randomIndex);
		
		return new Race(startTime, randomSubscriptionFee);
	}
	
}
