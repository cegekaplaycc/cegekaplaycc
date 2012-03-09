package models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.Model;
import securesocial.provider.AuthenticationMethod;
import securesocial.provider.ProviderType;
import securesocial.provider.SocialUser;
import securesocial.provider.UserId;

@Entity
public class Player extends Model {

	@Required
	public String userId;

	@Required
	@Enumerated(EnumType.STRING)
	public ProviderType providerType;

	@Required
	public String displayName;

	@Email
	public String email;

	public String avatarUrl;

	public Date lastAccess;

	@Enumerated(EnumType.STRING)
	public AuthenticationMethod authMethod;

	public String token;

	public String secret;

	public String accessToken;

	public String password;

	public boolean isEmailVerified;

	public String UUID;

	@OneToMany
	private Set<Horse> horses = new HashSet<Horse>();

	public Player() {
		// horses.add(Horse.find)
	}

	public static Player findByUserId(UserId userId) {
		return find("byUserIdAndProviderType", userId.id, userId.provider)
				.first();
	}

	public static void deletePendingActivations() {
		Player.delete("UUID != null");
	}

	public static Player create(SocialUser user) {
		Player player = new Player();

		player.userId = user.id.id;
		player.providerType = user.id.provider;
		player.displayName = user.displayName;
		player.email = user.email;
		player.avatarUrl = user.avatarUrl;
		player.lastAccess = user.lastAccess;
		player.authMethod = user.authMethod;
		player.token = user.token;
		player.secret = user.secret;
		player.accessToken = user.accessToken;
		player.password = user.password;
		player.isEmailVerified = user.isEmailVerified;

		return player;
	}

	public Set<Horse> getHorses() {
		return horses;
	}

}
