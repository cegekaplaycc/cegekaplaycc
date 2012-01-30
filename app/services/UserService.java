package services;

import models.Player;
import play.libs.Codec;
import securesocial.provider.SocialUser;
import securesocial.provider.UserId;

public class UserService implements securesocial.provider.UserService.Service {

    @Override
    public SocialUser find(UserId id) {
        Player player = Player.find("byUserId", id.id).first();
        if (player != null) {
            SocialUser user = new SocialUser();
            user.id = id;
            user.displayName = player.getName();
            return user;
        } else {
            return null;
        }
    }

    @Override
    public void save(SocialUser user) {
        if (find(user.id) == null) {
            Player player = new Player(user.id.id);
            player.setName(user.displayName);
            player.save();
        }
    }

    @Override
    public String createActivation(SocialUser user) {
        return Codec.UUID();
    }

    @Override
    public boolean activate(String uuid) {
        return true;
    }

    @Override
    public void deletePendingActivations() {
    }
}
