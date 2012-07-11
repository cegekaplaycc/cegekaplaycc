package webdriver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class DashboardPage extends AbstractHorsesPage<DashboardPage> {

	@FindBy(id = "welcomeHeader")
	private WebElement header;

	public DashboardPage() {
		super("/dashboard");
	}

	@Override
	protected boolean arrivedAt() {
		waitForElementPresent("welcomeHeader");
		return header.isDisplayed();
	}
}
