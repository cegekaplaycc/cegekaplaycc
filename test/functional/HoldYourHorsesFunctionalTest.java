package functional;

import litmus.functional.FunctionalTest;
import litmus.functional.Request;
import litmus.functional.Response;
import models.PlayerBuilder;

public abstract class HoldYourHorsesFunctionalTest extends FunctionalTest {

    @Override
    protected Response login(String username, String password) {
        return new Request(String.format("/auth/userpass?userName=%s&password=%s", username, password)).get();

    }

	protected PlayerBuilder createPlayerBuilder(String userId, String password) {
		return new PlayerBuilder()
	            .withUserId(userId)
	            .withPassword(password);
	}



}
