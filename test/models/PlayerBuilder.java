package models;

import static org.apache.commons.lang.time.DateUtils.addDays;
import static securesocial.provider.AuthenticationMethod.USER_PASSWORD;
import static securesocial.provider.ProviderType.userpass;

import java.util.Date;
import java.util.Set;

import models.stable.Box;

import org.mockito.internal.util.reflection.Whitebox;

import play.libs.Crypto;
import securesocial.provider.AuthenticationMethod;
import securesocial.provider.ProviderType;
import util.AbstractBuilder;

import com.google.common.collect.Sets;

public class PlayerBuilder extends AbstractBuilder<Player> {

	public static final String PLAYER_DISPLAY_NAME = "aPlayersDisplayName";
	public static final String PLAYER_USER_ID = "aPlayersUserId";
	public static final ProviderType PLAYER_USER_PROVIDER_TYPE = userpass;
	public static final String PLAYER_EMAIL = "aPlayers@Email.test";
	public static final String PLAYER_AVATAR_URL = "aPlayersAvatarURL";
	public static final Date PLAYER_LAST_ACCESS = addDays(new Date(), -1);
	public static final AuthenticationMethod PLAYER_AUTH_METHOD = USER_PASSWORD;
	public static final String PLAYER_TOKEN = "aPlayersToken";
	public static final String PLAYER_SECRET = "aPlayersSecret";
	public static final String PLAYER_ACCESS_TOKEN = "aPlayersAccessToken";
	public static final String PLAYER_PASSWORD = "aPlayersPassword";
	public static final String PLAYER_PASSWORD_HASHED = Crypto
			.passwordHash(PLAYER_PASSWORD);
	public static final boolean PLAYER_EMAIL_VERIFIED = true;
	public static final int PLAYER_CASH = 100;

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

	private Set<Box> boxes = Sets.newHashSet();
	private int cash = PLAYER_CASH;

	private PlayerBuilder() {

	}

	@Override
	public Player build() {
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
		player.password = Crypto.passwordHash(password);
		player.isEmailVerified = isEmailVerified;
		player.UUID = uuid;
		player.cash = cash;

		Whitebox.setInternalState(player, "boxes", boxes);
		return player;
	}

	public static PlayerBuilder aPlayer() {
		return new PlayerBuilder();
	}

	public PlayerBuilder withBoxes(Box... boxes) {
		this.boxes = Sets.newHashSet(boxes);
		return this;
	}

	public PlayerBuilder withDisplayName(String displayName) {
		this.displayName = displayName;
		return this;
	}

	public PlayerBuilder withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public PlayerBuilder withProviderType(ProviderType providerType) {
		this.providerType = providerType;
		return this;
	}

	public PlayerBuilder withUUID(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public PlayerBuilder withEmailVerified(boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
		return this;
	}

	public PlayerBuilder withPassword(String password) {
		this.password = password;
		return this;
	}

	public PlayerBuilder withCash(int cash) {
		this.cash = cash;
		return this;
	}

}
