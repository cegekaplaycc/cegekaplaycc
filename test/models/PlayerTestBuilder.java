package models;

import static org.apache.commons.lang.time.DateUtils.addDays;
import static securesocial.provider.AuthenticationMethod.OAUTH2;
import static securesocial.provider.ProviderType.twitter;

import java.util.Date;

import models.Player;
import securesocial.provider.AuthenticationMethod;
import securesocial.provider.ProviderType;

public class PlayerTestBuilder extends AbstractTestBuilder<Player> {

	public static final String PLAYER_DISPLAY_NAME = "aPlayersDisplayName";
	public static final String PLAYER_USER_ID = "aPlayersUserId";
	public static final ProviderType PLAYER_USER_PROVIDER_TYPE = twitter;
	public static final String PLAYER_EMAIL = "aPlayers@Email.test";
	public static final String PLAYER_AVATAR_URL = "aPlayersAvatarURL";
	public static final Date PLAYER_LAST_ACCESS = addDays(new Date(), -1);
	public static final AuthenticationMethod PLAYER_AUTH_METHOD = OAUTH2;
	public static final String PLAYER_TOKEN = "aPlayersToken";
	public static final String PLAYER_SECRET = "aPlayersSecret";
	public static final String PLAYER_ACCESS_TOKEN = "aPlayersAccessToken";
	public static final String PLAYER_PASSWORD = "aPlayersPassword";
	public static final boolean PLAYER_EMAIL_VERIFIED = true;

	private String userId = PLAYER_USER_ID;
	private ProviderType providerType = PLAYER_USER_PROVIDER_TYPE;
	private String displayName = PLAYER_DISPLAY_NAME;
	private String email = PLAYER_EMAIL;
	private String avatarUrl = PLAYER_AVATAR_URL;
	private Date lastAccess = PLAYER_LAST_ACCESS;
	private AuthenticationMethod authMethod = PLAYER_AUTH_METHOD;
	private String token = PLAYER_TOKEN;
	private String secret = PLAYER_SECRET;
	private String accessToken = PLAYER_ACCESS_TOKEN;
	private String password = PLAYER_PASSWORD;
	private boolean isEmailVerified = PLAYER_EMAIL_VERIFIED;
	private String uuid;

	@Override
	protected Player buildObject() {
		Player player = new Player();

		player.userId = userId;
		player.providerType = providerType;
		player.displayName = displayName;
		player.email = email;
		player.avatarUrl = avatarUrl;
		player.lastAccess = lastAccess;
		player.authMethod = authMethod;
		player.token = token;
		player.secret = secret;
		player.accessToken = accessToken;
		player.password = password;
		player.isEmailVerified = isEmailVerified;
		player.UUID = uuid;

		return player;
	}

	public static PlayerTestBuilder aPlayer() {
		return new PlayerTestBuilder();
	}

	public PlayerTestBuilder withDisplayName(String displayName) {
		this.displayName = displayName;
		return this;
	}

	public PlayerTestBuilder withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public PlayerTestBuilder withProviderType(ProviderType providerType) {
		this.providerType = providerType;
		return this;
	}

	public PlayerTestBuilder withUUID(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public PlayerTestBuilder withEmailVerified(boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
		return this;
	}
}
