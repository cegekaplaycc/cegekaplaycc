package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Player extends Model {

	private String name;

    @Column(unique = true)
	private final String userId;

	@OneToMany
	public Set<Horse> horses = new HashSet<Horse>();

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

}
