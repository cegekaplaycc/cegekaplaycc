package functional;

import litmus.functional.FunctionalTest;
import litmus.functional.Request;
import litmus.functional.Response;
import org.junit.Test;

public class SignupFunctionalTest extends FunctionalTest {


    @Test
    public void signupPageIsAccessible(){
        assertThat(get("/auth/signup")).isHtml().isOk();
    }

    @Test
    public void userNameIsRequired() {
        Response response = new Request("/auth/signup").with("userName", "").post();
        assertThat(response)
                .isRedirect()
                .isHtml()
                .hasValidationError("userName", "securesocial.required");
    }

}
