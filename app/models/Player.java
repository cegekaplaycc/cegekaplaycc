package models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

import com.google.common.collect.Sets;

@Entity
public class Player extends Model {

	private String name;

	@Column(unique = true)
	private final String userId;

	@OneToMany
	public Set<Horse> horses = new HashSet<Horse>();

	private String avatarUrl;

	private String email;

	private String authMethod;

	private Date lastAccess;

	public Player(String userId) {
		this.userId = userId;
	}

	public Player(String userId, String name) {
		this(userId);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public Set<Horse> getHorses() {
		return horses;
	}

	public void addHorse(Horse horse) {
		this.horses.add(horse);
		this.save();
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthMethod() {
		return authMethod;
	}

	public void setAuthMethod(String authMethod) {
		this.authMethod = authMethod;
	}

	public Date getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}

	public Set<Race> getPastEnteredRaces() {
		Set<Race> races = Sets.newHashSet();
		for (Horse horse : getHorses()) {
			races.addAll(horse.getPastEnteredRaces());
		}
		return races;
	}
}
