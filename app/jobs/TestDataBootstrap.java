package jobs;

import play.Play;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.libs.Crypto;
import securesocial.provider.AuthenticationMethod;
import securesocial.provider.ProviderType;
import securesocial.provider.SocialUser;
import securesocial.provider.UserId;
import securesocial.provider.UserService;

@OnApplicationStart
public class TestDataBootstrap extends Job {

	@Override
	public void doJob() throws Exception {
		if (Play.mode.isDev()) {
			SocialUser socialUser = new SocialUser();
			socialUser.displayName = "matti";
			socialUser.password = Crypto.passwordHash("matti");
			UserId userId = new UserId();
			userId.id = "matti";
			userId.provider = ProviderType.userpass;
			socialUser.id = userId;
			socialUser.authMethod = AuthenticationMethod.USER_PASSWORD;
			socialUser.isEmailVerified = true;
			UserService.save(socialUser);
		}
	}

}
