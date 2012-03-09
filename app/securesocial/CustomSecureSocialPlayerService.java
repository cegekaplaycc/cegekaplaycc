package securesocial;

import models.Player;
import play.libs.Codec;
import securesocial.provider.SocialUser;
import securesocial.provider.UserId;
import securesocial.provider.UserService;

public class CustomSecureSocialPlayerService implements UserService.Service {

	@Override
	public SocialUser find(UserId id) {
		Player player = Player.findByUserId(id);
		if (player != null) {
			return SocialUserFactory.create(player);
		}
		return null;
	}

	@Override
	public void save(SocialUser user) {
		if (find(user.id) == null) {
			Player player = Player.create(user);
			player.validateAndSave();
		}
	}

	@Override
	public String createActivation(SocialUser user) {
		String uuid = Codec.UUID();

		Player player = Player.findByUserId(user.id);
		player.UUID = uuid;
		player.validateAndSave();

		return uuid;
	}

	@Override
	public boolean activate(String uuid) {
		Player player = Player.find("byUUID", uuid).first();
		if (player != null) {
			player.isEmailVerified = true;
			player.UUID = null;
			player.validateAndSave();
			return true;
		}
		return false;
	}

	@Override
	public void deletePendingActivations() {
		Player.deletePendingActivations();
	}

}
