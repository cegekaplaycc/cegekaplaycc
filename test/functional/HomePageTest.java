package functional;

import litmus.functional.FunctionalTest;
import org.junit.Test;

public class HomePageTest extends FunctionalTest {


    @Test
    public void homepageIsAccessible(){
        assertThat(get("/")).isHtml().isOk();
    }

}
