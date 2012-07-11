package webdriver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import litmus.webdriver.Page;

public abstract class AbstractHorsesPage<T extends AbstractHorsesPage<T>> extends Page<T> {

	public AbstractHorsesPage(String relativeUrl) {
		super(relativeUrl);
	}

	@FindBy(id="menuDashboardLink")
	private WebElement menuDashboardLink;

	@FindBy(id="menuFoodmarketLink")
	private WebElement menuFoodmarketLink;
	
	
	public DashboardPage clickOnDashboard() {
		menuDashboardLink.click();
		return new DashboardPage().assertArrivedAt();
	}

	public FoodMarketPage clickOnFoodmarket() {
		menuFoodmarketLink.click();
		return new FoodMarketPage().assertArrivedAt();
	}
	
	Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) {
		return new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		};
	}

	public void waitForElementPresent(String id) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(presenceOfElementLocated(By.id(id)));
	}
}
