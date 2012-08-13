package webdriver;

import litmus.webdriver.WebdriverTest;
import models.PlayerBuilder;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;

import webdriver.pages.HorsesLoginPage;

public class HorseMarketTest extends WebdriverTest {

	@Before
	public void setupUser() {
		Fixtures.deleteDatabase();
		PlayerBuilder.aPlayer()
			.withDisplayName("matti")
			.withPassword("matti")
			.withUserId("matti")
			.save();
	}
	
	@Test
	public void iShouldBeAbleToSeeMyCurrentStock() {
		new HorsesLoginPage()
				.open()
				.loginWith("matti", "matti")
				.clickOnFoodmarket()
				.fillInAmountOfPowerBiscuitsToBuy("2")
				.isTotalPriceForPowerBiscuits("24")
				.clickBuy()
				.clickOnFoodmarket()
				.isAmountOfPowerBiscuits("2");
	}

}
