package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.jpa.Model;

@Entity
public class Horse extends Model {

	private String name;
	private long price;

<<<<<<< HEAD
	public Horse() {
	}

=======
>>>>>>> 9f926598a6db836b8fa4eec4c157231c3e71a389
	public Horse(String name) {
		this.name = name;
	}

<<<<<<< HEAD
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
=======
	public Horse() {
>>>>>>> 9f926598a6db836b8fa4eec4c157231c3e71a389
	}

	@Override
	public String toString() {
		return name;
	}
}
