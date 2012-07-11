package webdriver.pages;

import litmus.webdriver.Page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.sun.java.swing.plaf.windows.WindowsBorders.DashedBorder;

public class MainPage extends AbstractHorsesPage<MainPage> {

	@FindBy(id = "welcomeHeader")
	private WebElement header;

	
	public MainPage() {
		super("/");
	}

	@Override
	protected boolean arrivedAt() {
		waitForElementPresent("welcomeHeader");
		return header.isDisplayed();
	}

}
