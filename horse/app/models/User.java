package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class User extends Model {

	private String name;
	private final String userId;

	public User(String userId) {
		this.userId = userId;
	}

	public User(String userId, String name) {
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

}
