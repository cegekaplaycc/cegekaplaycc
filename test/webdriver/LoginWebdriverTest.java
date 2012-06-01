package webdriver;

import litmus.webdriver.WebdriverTest;
import org.junit.Test;
import webdriver.pages.LoginPage;

public class LoginWebdriverTest extends WebdriverTest {

    @Test
    public void iCanLogIn() {
        new LoginPage().open();
    }
}
