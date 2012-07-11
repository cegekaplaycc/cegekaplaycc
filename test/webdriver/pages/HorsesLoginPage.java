package webdriver.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import litmus.webdriver.Page;

public class HorsesLoginPage extends AbstractHorsesPage<HorsesLoginPage> {

	@FindBy(id = "userName")
	private WebElement username;
	 
	@FindBy(id = "password")
	private WebElement password;
	
	@FindBy(id = "loginButton")
	private WebElement loginButton;
	
	public HorsesLoginPage() {
		super("/auth/login");
	}

	public MainPage loginWith(String user, String password) {
		this.username.sendKeys(user);
		this.password.sendKeys(password);
		this.loginButton.click();
		return new MainPage().assertArrivedAt();
	}
	
	@Override
	protected boolean arrivedAt() {
		waitForElementPresent("userName");
		return username.isDisplayed();
	}

}
