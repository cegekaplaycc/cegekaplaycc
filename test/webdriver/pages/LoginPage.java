package webdriver.pages;

import litmus.webdriver.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page<LoginPage> {

    @FindBy(id = "userName")
    private WebElement usernameInput;

    public LoginPage() {
        super("/auth/login");
    }

    @Override
    protected boolean arrivedAt() {
        return usernameInput.isDisplayed();
    }
}
