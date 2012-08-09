package webdriver.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.fest.assertions.Assertions.assertThat;

public class FoodMarketPage extends AbstractHorsesPage<FoodMarketPage> {

    @FindBy(id = "foodmarketHeader")
    private WebElement header;

    @FindBy(id = "purchases_1")
    private WebElement purchasePowerBiscuitsInputField;

    @FindBy(id = "totalPrice_1")
    private WebElement totalPricePowerBiscuits;

    @FindBy(id = "amount_1")
    private WebElement stockAmountOfPowerBiscuits;

    @FindBy(id = "buyButton")
    private WebElement buyButton;


    public FoodMarketPage() {
        super("/foodMarket");
    }

    @Override
    protected boolean arrivedAt() {
        waitForElementPresent("foodmarketHeader");
        return header.isDisplayed();
    }

    public FoodMarketPage fillInAmountOfPowerBiscuitsToBuy(String amount) {
        purchasePowerBiscuitsInputField.clear();
        purchasePowerBiscuitsInputField.sendKeys(amount);
        purchasePowerBiscuitsInputField.sendKeys(Keys.TAB);
        return this;
    }

    public FoodMarketPage isTotalPriceForPowerBiscuits(String price) {
        assertThat(totalPricePowerBiscuits.getText()).isEqualTo(price);
        return this;
    }

    public DashboardPage clickBuy() {
        buyButton.click();
        return new DashboardPage().assertArrivedAt();
    }

    public FoodMarketPage isAmountOfPowerBiscuits(String amount) {
        assertThat(stockAmountOfPowerBiscuits.getText()).isEqualTo(amount);
        return this;
    }

}
