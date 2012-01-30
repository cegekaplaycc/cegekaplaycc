package controllers;

import com.ning.http.client.Response;
import play.Logger;
import play.api.WS;
import play.api.libs.concurrent.Promise;
import play.libs.F;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.login;

public class LoginController extends Controller {

	public static Result showLoginScreen() {
		// F.Promise<WS.HttpResponse> resultPromise = WS.url("http://www.google.com").get();

		// Logger.info(resultPromise.toString());
		return ok(login.render());
	}

}
