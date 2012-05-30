package functional;

import litmus.functional.FunctionalTest;
import org.junit.Test;

public class SecurityFunctionalTest extends FunctionalTest {


    @Test
    public void loginPageIsAccessible(){
        assertThat(get("/auth/login")).isHtml().isOk();
    }

}
